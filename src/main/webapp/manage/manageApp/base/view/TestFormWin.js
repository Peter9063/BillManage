Ext.define('manageApp.base.view.TestFormWin',{
	extend: 'Ext.window.Window',
	alias:'widget.baseviewTestFormWin',
	layout:'fit',
	autoShow:true,
	constrainHeader:true,
	isSave:true,
	
	initComponent:function(){
		
		var statusStore = Ext.create('Ext.data.Store', {
			fields : [
				{
					type : 'string',
					name : 'name'
				}, {
					type : 'int',
					name : 'value'
				}
			],
			data : [
		        {
					"name" : '新增',//新增
					"value" : "1"
				}, {
					"name" : '启用',//启用
					"value" : "2"
				}, {
					"name" : '维护',//
					"value" : "3"
				}, {
					"name" : '停用',//
					"value" : "4"
				}, {
					"name" : '报废',//
					"value" : "5"
				}
			]
		});
		
//		var meterModelStore = Ext.create('Ext.data.Store', {
//			fields : [
//				{
//					type : 'string',
//					name : 'name'
//				}, {
//					type : 'int',
//					name : 'value'
//				}
//			],
//			autoLoad: true,
//			proxy: {
//				url: 'civilianmeter/customerInfoManager/findMeterModel.do',
//				type: 'ajax'
//			}
//		})
		
//		var eqpModelStore = Ext.create('Ext.data.Store', {
//			fields : [
//				{
//					type : 'string',
//					name : 'cemName'
//				}, {
//					type : 'int',
//					name : 'cemCode'
//				}
//			],
//			autoLoad: true,
//			proxy: {
//				url: 'civilianmeter/customerInfoManager/getEqpModelByEqpType.do',
//				type: 'ajax',
//				extraParams: {
//					eqpType: 2
//				}
//			},
//			listeners: {
//				'load': function(store, records, successful, operation, eOpts) {
//					cemArr = [];
//					if(records != null && records != undefined && records != '' && records.length > 0) {
//						for(var i = 0; i < records.length; i++) {
//							cemArr.push(records[i].data['cemCode']+','+records[i].data['cemName'])
//						}
//					}
//				}
//			}
//		});
		
		
		
		var formPanel=Ext.create('Ext.form.Panel',{
			autoScroll:true,
			height:475,
			width:650,
			layout:'fit',
		    fieldDefaults: {  
		    	labelAlign:'right',
		        width: 250,
		        labelWidth: 80,
		        allowBlank: false
		    },
			items:[
			
			 Ext.create('Ext.form.Panel', {
				items: [
					{
						xtype: 'fieldset',
			        	title: '客户信息',//客户信息
			        	margin: '5 5 12 5',
			        	height: 120,
			        	layout: 'vbox',
			        	items: [
			        		{
			        			xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			layout: 'hbox',
			        			width: 660,
			        			items: [
				        			{
				        				xtype: 'textfield',
				        				fieldLabel: '户号',//
				        				width: 260,
				        				name: 'station.cstNum',
				        				labelWidth: 90,
				        				maxLength: 20,
				        				margin: '0 30 0 5',
				        				allowBlank: false
				        			}, {
				        				xtype: 'textfield',
				        				fieldLabel: '客户名称',//
				        				name: 'station.cstName',
				        				width: 260,
				        				maxLength: 50,
				        				labelWidth: 100,
				        				margin: '0 0 0 10',
				        				allowBlank: false
				        				
				        			}
			        			]
			        		}, {
			        			xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
				        			{
				        				xtype: 'combobox',
				        				fieldLabel: '客户状态',//
				        				name: 'station.cstStatus',
				        				width: 260,
				        				labelWidth: 90,
				        				margin: '0 30 0 5',
				        				triggerAction: 'all',
										editable: false,
										allowBlank: true,
										queryMode: 'local',
										displayField: 'name',
										valueField: 'value',
										forceSelection: true, 
										store : statusStore,
//										typeAhead : true,
										listeners: {
											select: function(combo, records, eOpts) {
												var date = this.up().down('[name=station.cstStartDate]');
												if(records[0].data['value'] == 2) {
													date.setVisible(true);
													date.setDisabled(false);
												} else {
													date.setVisible(false);
													date.setDisabled(true);
												}
											}
										}
				        				
				        			}, {
				        				xtype: 'datefield',
				        				fieldLabel: '启用日期',//
				        				name: 'station.cstStartDate',
				        				margin: '0 0 0 10',
				        				labelWidth: 100,
				        				width: 260,
				        				format: 'Y-m-d',
				        				maxValue: new Date(),
										editable: false,
										allowBlank: false,
										listeners: {
											render: function(view) {
												if(view.getValue() == '' || view.getValue() == null) {
													view.setValue(new Date());
												}
											}
										}
				        			}
			        			]
			        		}, {
			        			xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
				        			{
				        				xtype: 'textfield',
				        				fieldLabel:'客户地址',//
				        				name: 'station.cstAddr',
				        				margin: '0 30 0 5',
				        				width: 560,
				        				maxLength: 50,
				        				labelWidth: 90,
				        				allowBlank: false
				        			}
			        			]
			        		}
			        	]
					}, {
						xtype: 'fieldset',
			        	title: '水表信息',//'',
			        	margin: '2 5 12 5',
			        	height: 120,
			        	layout: 'vbox',
			        	items: [
				        	{
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
				        			{
				        				xtype: 'textfield',
				        				fieldLabel: '表身号',//
				        				name: 'meter.cmcBodyNum',
				        				margin: '0 30 0 5',
				        				width: 260,
				        				maxLength: 20,
				        				labelWidth: 90,
				        				allowBlank: false
			        				}, {
				        				xtype: 'textfield',
				        				fieldLabel: '编号',//
				        				maxLength: 20,
				        				name: 'meter.cmcNum',
				        				margin: '0 0 0 10',
				        				width: 260,
				        				labelWidth: 100,
				        				allowBlank: false
			        				}
			        			]
				        	}, {
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
			        				{
				        				xtype: 'combobox',
				        				fieldLabel: '水表型号',//'',
				        				name: 'meter.cmmId',
				        				width: 260,
				        				labelWidth: 90,
				        				margin: '0 30 0 5',
				        				triggerAction: 'all',
										editable: false,
										allowBlank: true,
										queryMode: 'local',
										displayField: 'name',
										valueField: 'value',
										forceSelection: true
//										store : meterModelStore,
//										typeAhead : true
				        			},{ 			        			
				        				xtype: 'textfield',
				        				fieldLabel: '初始读数',//
				        				name: 'meter.cmcInitReading',
				        				margin: '0 0 0 10',
				        				width: 260,
				        				labelWidth: 100,
				        				allowBlank: false,
				        				regex: /^\d{1,8}(\.\d{1,3})?$/,
										regexText:'请输入八位以内的整数，三位以内的小数'//
			        				}
			        			]
				        	}, {
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
										{
											xtype: 'combobox',
											fieldLabel: '水表状态',//'',
											name: 'meter.cmcStatus',
											width: 260,
											labelWidth: 90,
											margin: '0 30 0 5',
											triggerAction: 'all',
											editable: false,
											allowBlank: true,
											queryMode: 'local',
											displayField: 'name',
											valueField: 'value',
											value: '2',
											forceSelection: true, 
											store : statusStore,
//											typeAhead : true,
											listeners: {
												select: function(combo, records, eOpts) {
													var date = this.up().down('[name=meter.cmcStartDate]');
													if(records[0].data['value'] == 2) {
														date.setVisible(true);
														date.setDisabled(false);
													} else {
														date.setVisible(false);	
														date.setDisabled(true);
													}
												}
											}
			        				  }, {
				        				xtype: 'datefield',
				        				fieldLabel: '启用日期',//
				        				name: 'meter.cmcStartDate',
				        				format: 'Y-m-d',
				        				width: 260,
				        				labelWidth: 100,
				        				margin: '0 0 0 10',
										editable: false,
										allowBlank: false,
										listeners: {
											render: function(view) {
												if(view.getValue() == '' || view.getValue() == null) {
													view.setValue(new Date());
												}
											}
										}
			        				}
			        			]
				        	}
			        	]
					}, {
						xtype: 'fieldset',
			        	title: '终端信息',//
			        	margin: '2 5 2 5',
			        	height: 120,
			        	layout: 'vbox',
			        	items: [
			        	{
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
		        				{
			        				xtype: 'textfield',
			        				fieldLabel: '设备标识',//设备标识
			        				name: 'moduleStation.mesConcentratorId',
			        				margin: '0 30 0 5',
			        				width: 260,
			        				maxLength: 20,
			        				labelWidth: 90,
			        				allowBlank: false,
			        				listeners: {
			        					change: function(text, newValue, oldValue) {
			        					}
			        				}
		        				}, {
			        				xtype: 'textfield',
			        				fieldLabel: '通道号',//通讯号
			        				name: 'moduleStation.mesBurnedId',
			        				width: 260,
			        				labelWidth: 100,
			        				maxLength: 50,
			        				margin: '0 0 0 10',
									allowBlank: false
			        			}]
				        	},
			        		{
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
		        				{
			        				xtype: 'textfield',
			        				fieldLabel: '无线地址',//
			        				name: 'moduleStation.mesRadioAddr',
			        				id: 'insertWinRadioAddr',
			        				margin: '0 30 0 5',
			        				width: 260,
			        				maxLength: 20,
			        				labelWidth: 90,
			        				allowBlank: false,
			        				listeners: {
			        					change: function(text, newValue, oldValue) {
			        					}
			        				}
		        				}, {
			        				xtype: 'textfield',
			        				fieldLabel: '设备名称',//
			        				name: 'moduleStation.mesName',
			        				width: 260,
			        				labelWidth: 100,
			        				maxLength: 50,
			        				margin: '0 0 0 10',
									allowBlank: false
			        			}]
				        	}, {
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
		        				{
			        				xtype: 'combobox',
			        				fieldLabel: '设备型号',//
			        				name: 'moduleStation.mesType',
			        				margin: '0 30 0 5',
			        				width: 260,
			        				labelWidth: 90,
			        				triggerAction: 'all',
									editable: false,
									allowBlank: true,
									displayField: 'cemName',
									valueField: 'cemCode',
									forceSelection: true
//									store : eqpModelStore,
//									typeAhead : true
		        				}, {
			        				xtype: 'textfield',
			        				fieldLabel: '软件版本',//
			        				name: 'moduleStation.mesVersion',
			        				width: 260,
			        				labelWidth: 100,
			        				margin: '0 0 0 10',
									allowBlank: false,
									regex: /^\d{1,2}(\.\d{1})?$/,
									regexText: '请输入一位有效数字的小数',//
									listeners: {
										render: function(view) {
											if(view.getValue() == '' || view.getValue() == null) {
												view.setValue('1.0');
											}
										}
									}
			        			}]
				        	},{
				        		xtype: 'panel',
								border: false,
			        			margin: '2 0 2 0',
			        			width: 660,
			        			layout: 'hbox',
			        			items: [
		        				{
									xtype: 'combobox',
									fieldLabel: '设备状态',//
			        				name: 'moduleStation.mesStatus',
									width: 260,
									labelWidth: 90,
									margin: '0 30 0 5',
									triggerAction: 'all',
									editable: false,
									allowBlank: true,
									queryMode: 'local',
									displayField: 'name',
									valueField: 'value',
									value: '2',
									forceSelection: true, 
									store : statusStore,
//									typeAhead : true,
									listeners: {
										select: function(combo, records, eOpts) {
											var date = this.up().down('[name=moduleStation.mesStartDate]');
											if(records[0].data['value'] == 2) {
												date.setVisible(true);
												date.setDisabled(false);
											} else {
												date.setVisible(false);	
												date.setDisabled(true);
											}
										}
									}
		        				}, 	{		        				
		        						xtype: 'datefield',
				        				fieldLabel: '启用日期',//
				        				name: 'moduleStation.mesStartDate',
				        				format: 'Y-m-d',
				        				width: 260,
				        				labelWidth: 100,
				        				margin: '0 0 0 10',
										editable: false,
										allowBlank: false,
										listeners: {
											render: function(view) {
												if(view.getValue() == '' || view.getValue() == null) {
													view.setValue(new Date());
												}
											}
										}
				        		}]
				        	}, {
				        		xtype: 'hiddenfield',
				        		name: 'station.cstId'
				        	}, {
				        		xtype: 'hiddenfield',
				        		name: 'stationItem.msiId'
				        	}, {
				        		xtype: 'hiddenfield',
				        		name: 'ccr.meqId'
				        	}, {
				        		xtype: 'hiddenfield',
				        		name: 'moduleStation.mesId'
				        	}, {
				        		xtype: 'hiddenfield',
				        		name: 'meter.cmcId'
				        	},{
				        		xtype: 'hiddenfield',
				        		name: 'station.caiId'
				        	}
			        	]
					}
					
					]})
			
			]
		});
		this.items=[formPanel];
		this.formPanel=formPanel;
	
		if(this.isSave==true){
			this.buttons=[
	  			{
	  				text:'保存',
	  				name:'btnWinFormSave'
	  			},
	  			{
	  				text:'关闭',
	  				scope:this,
	  				handler:this.close
	  			}
	  		];
		}
		else{
			formPanel.getForm().loadRecord(this.record);
			this.buttons=[
	  			{
	  				text:'保存(修改)',
	  				name:'btnWinFormModif'
	  			},
	  			{
	  				text:'关闭',
	  				scope:this,
	  				handler:this.close
	  			}
	  		];
		}

		this.callParent(arguments);
	}

});