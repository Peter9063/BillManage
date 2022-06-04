Ext.define('manageApp.base.view.user.User',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewUser',
	layout: 'border',
	initComponent: function(){
		var me=this;

		if(this.params==null){
			this.params={};
			this.params.sysType=0;
		}
		//独立Store，按业务类型单独创建。
		var storeId="userStore"+this.params.sysType;
		me.mainStore= Ext.data.StoreManager.lookup(storeId);
		if(me.mainStore==null){
			me.mainStore=Ext.create('manageApp.base.store.Users',{storeId: storeId,});
		}
		me.roleStore=Ext.create('manageApp.base.store.Roles');



		var gridPanel=Ext.create('Ext.grid.Panel', {
			region:'center',
			name:'grid',
			// store: 'manageApp.base.store.Users',
			store:me.mainStore,
			columns: [
				{xtype:'rownumberer', width:'3%'},
				{ dataIndex: 'userName', text: '用户名 ' ,width:'10%'},//用户名
				{ dataIndex: 'alias', text: '用户别名 ',width:'10%'},//用户别名
//		        { dataIndex: 'passWord', text: '密码',width:'10%'},//密码
				{ dataIndex: 'email', text: '邮箱 ',width:'10%'},//邮箱
				{ dataIndex: 'sex', text: '性别 ',width:'7%'},//性别
				{ dataIndex: 'depart', text: '部门',width:'10%'},//部门
				// { dataIndex: 'role', text: '角色',width:'10%' ,renderer:manageApp.utils.Model.convertUserRole},//角色
				{ dataIndex: 'roles', text: '角色',width:'10%' ,renderer:function renderSex(value) {
						var values = [] ;
						value=value.replace("[","")
						value=value.replace("]","");
						values=value.split(",");
						var returnValue = "";
						var k = 0;
						for(var i=0 ; i< me.roleStore.getCount();i++){
							if(values.length>1) {
								for (var j = 0; j < values.length; j++) {
									if (values[j] == me.roleStore.data.items[i].data.roleId) {
										k++;
										returnValue = returnValue + me.roleStore.data.items[i].data.name+",";
									}
								}
								if (k ==values.length) {
									return returnValue;
								}
							}else{
								if(values[0] == me.roleStore.data.items[i].data.roleId){
									return	me.roleStore.data.items[i].data.name
								}else {
								}
							}
						}}},//角色
//		        { dataIndex: 'state', text: '状态',width:'10%'},//状态
				{ text: '创建时间',   dataIndex: 'createTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' },
				{ text: '创建人',  dataIndex: 'createUser' , width:'10%'},
				{ text: '修改时间',  dataIndex: 'modifTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' },
				{ text: '修改人',  dataIndex: 'modifUser' , width:'10%'}
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
				// store: 'manageApp.base.store.Users',
				store:me.mainStore,
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
					xtype:'hiddenfield',
					name: 'sysType',
					value:(this.params.sysType==0)?'-1':this.params.sysType
				},
				{
					fieldLabel: '用户名',
					name: 'userName',
					padding:'5 5 5 5'
				}
			]
		});

		this.items=[gridPanel,searchPanel];
		this.callParent(arguments);
	}
});