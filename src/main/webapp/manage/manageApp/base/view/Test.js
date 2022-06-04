Ext.define('manageApp.base.view.Test', {
	extend: 'Ext.Panel',
	alias : 'widget.baseviewTest',
	closable : true,
	layout : {
		type : 'border',
		padding : -1
	},
	defaults : {
		collapsible : false,
		autoScroll : true,
		closable : false
	},
	initComponent : function() {
		
		console.log(this.params);
		
		var gridPanel=Ext.create('Ext.grid.Panel', {
			region:'center',
			name:'grid',
		    store: 'manageApp.base.store.GasArchives',
		    columns: [
		    	{xtype: 'rownumberer'},
		    	{ text: '户号',  dataIndex: 'station.cstNum'},
		        { text: '客户名称',  dataIndex: 'station.cstName'},
		        { text: '客户地址',  dataIndex: 'station.cstAddr'},
		        { text: '客户状态',  dataIndex: 'station.cstStatus'},
		        { text: '设备名称',  dataIndex: 'mes.mesName'},
		        { text: '表身号',  dataIndex: 'meter.cmcBodyNum'},
		        { text: '无线地址',  dataIndex: 'mes.mesRadioAddr'},
		        { text: '表型号',  dataIndex: 'meter.cmmId'},
		        { text: '设备型号',  dataIndex: 'mes.mesType'},
		        { text: '设备版本',  dataIndex: 'mes.mesVersion'},
		        { text: '设备状态',  dataIndex: 'mes.mesStatus'},
		        { text: '已组网',  dataIndex: 'mes.mesNetGroup'},
		        { text: '通道号',  dataIndex: 'mes.mesBurnedId'},
		        { text: '已烧入',  dataIndex: 'mes.mesHasBurned'},
		        { text: '表状态',  dataIndex: 'meter.cmcStatus'},
		        { text: '初始读数',  dataIndex: 'meter.cmcInitReading'},
		        { text: '区域',  dataIndex: 'station.caiId'}
		    ],
			selModel: Ext.create('Ext.selection.CheckboxModel', {mode: 'MULTI'}),//MULTI SINGLE
		    viewConfig: {
		        stripeRows: true,
		        enableTextSelection: true
		    },
		    tbar:[
				{ 
					xtype: 'button',
					name:'butAdd',
					text: '添加',
					width: 60
				 },'-',
	 			{
					xtype : 'button',
					name:'butEdit',
					text : '修改',
					width : 60
				},'-',{ 
					xtype: 'button',
					name:'butDel',
					text: '删除',
					width: 60
				 }
		    ],
		    bbar: Ext.create('Ext.PagingToolbar', {
	            store: 'manageApp.base.store.GasArchives',
	            displayInfo: true
			})
		});
				
		var searchPanel=Ext.create('Ext.form.Panel',{
			region:'east',
			title:'查询条件',
			name:'searchBar',
			width:260,
			split: true,
			collapsible: true,
			defaultType: 'textfield',
			tbar:[
				{ 
					xtype: 'button',
					name:'butFind',
					text: '查询',
					width: 60
				 },'-',
	 			{
					xtype : 'button',
					name:'butReset',
					text : '重置',
					width : 60
				}
			],
			items:[
				{
					fieldLabel: '分类名称',
					name: 'name',
					padding:'5 5 5 5'
				},
				{
					xtype: 'datefield',
					fieldLabel: '创建时间',
					name: 'createTime',
					padding:'5 5 5 5',
					format: 'Y-m-d',
					value: new Date()
				}
			]
		});
		
		this.items=[gridPanel,searchPanel];
		
		this.callParent(arguments);
	}
})
