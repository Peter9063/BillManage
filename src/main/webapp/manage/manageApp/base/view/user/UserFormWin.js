Ext.define('manageApp.base.view.user.UserFormWin',{
	extend: 'Ext.window.Window',
	alias:'widget.baseViewUserFormWin',
	layout:'fit',
	autoShow:true,
	constrainHeader:true,
	isSave:true,

	initComponent:function(){
		console.log(this.sysType);

		var me = this ;
		me.roleStore=Ext.create('manageApp.base.store.Roles');
		me.roleStore.load();

		var formPanel=Ext.create('Ext.form.Panel',{
			autoScroll:true,
			height:500,
			width:600,
			layout:'form',
			fieldDefaults: {
				labelAlign:'right',
				width: 580,
				labelWidth: 80,
				allowBlank: true
			},
			items:[
				{
					layout : 'hbox',
					defaultType : 'textfield',
					border : false,
					padding : '5,0,0,0',
					items : [{
						xtype : 'hiddenfield',
						name : 'id'
					}
						, {
							fieldLabel : '用户名',
							name : 'userName',
							allowBlank: false,
							flex : 2
						}]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '用户别名',
							name: 'alias',
							allowBlank: false,
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '密码',
							name: 'passWord',
							allowBlank: false,
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '邮箱',
							name: 'email',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '性别',
							name: 'sex',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '部门',
							name: 'depart',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							xtype:'combo',
							multiSelect: true,//多选
							fieldLabel: '角色',
							name: 'roles',
							queryMode: 'local',
							displayField: 'name',
							// valueField: 'value',
							valueField: 'roleId',
							value:'-1',
							store:me.roleStore,
							// store:manageApp.utils.Model.getConstantStore('UserRole'),
							// store: '',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							xtype:'combo',
							fieldLabel: '系统类型',
							name: 'sysType',
							queryMode: 'local',
							displayField: 'name',
							valueField: 'value',
							value:'0',
							store:manageApp.utils.Model.getConstantStore('SysType'),
							flex: 3
						}]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							xtype:'datefield',
							fieldLabel: '创建时间',
							name: 'createTime',
							value:new Date(),
							format:'Y-m-d',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '创建人',
							name: 'createUser',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							xtype:'datefield',
							fieldLabel: '修改时间',
							name: 'modifTime',
							value:new Date(),
							format:'Y-m-d',
							flex: 3
						}
					]
				},
				{
					layout:'hbox',
					defaultType: 'textfield',
					border: false,
					padding:'5,0,0,0',
					items:[
						{
							fieldLabel: '修改人',
							name: 'modifUser',
							flex: 3
						}
					]
				}
			]
		});
		this.items=[formPanel];
		this.formPanel=formPanel;

		if(this.isSave==true){
			var sysTypeValue=1;
			if(this.sysType!=null && this.sysType>1){
				sysTypeValue=this.sysType;
				this.formPanel.getForm().findField("sysType").readOnly=true;
				this.formPanel.getForm().findField("roles").readOnly=true;
			}
			formPanel.getForm().findField('sysType').setValue(sysTypeValue);
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
			formPanel.getForm().findField('modifTime').setValue(new Date());
			if(this.sysType!=null && this.sysType>1){
				this.formPanel.getForm().findField("sysType").readOnly=true;
				this.formPanel.getForm().findField("roles").readOnly=true;
			}
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