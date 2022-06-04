Ext.define('manageApp.base.view.role.RoleAndUserDistribution',{
    extend: 'Ext.panel.Panel',
    alias:'widget.baseViewRoleAndUserDistribution',
    layout: 'border',


    initComponent: function(){

        var me=this;
        if(this.params==null){
            this.params={};
        }
        this.params.type=2;//已分配用户
        // //独立Store，按业务类型单独创建。
        var storeId="menuTreeStore"+this.params.type;

        me.mainStore= Ext.data.StoreManager.lookup(storeId);
        if(me.mainStore==null){
           me.mainStore=Ext.create('manageApp.base.store.RoleAndUserDistributions',{storeId: storeId});
        }

        var grid=Ext.create('Ext.grid.Panel',{
            name:'grid',
            region:'center',
            collapsible: true,
            title:this.params.roleName+":   已分配用户",
            store:me.mainStore,
            columns: [
                {xtype:'rownumberer', width:'3%'},
                { dataIndex: 'userName', text: '用户名 ' ,width:'30%'},//用户名
                { dataIndex: 'alias', text: '用户别名 ',width:'30%'},//用户别名
                { dataIndex: 'depart', text: '部门',width:'30%'},//部门
            ],
            selModel: Ext.create('Ext.selection.CheckboxModel', {mode: 'MULTI'}),
            tbar:[{
                xtype : 'button',
                name:'butAdd',
                text : '添加',
                width : 60
            },{
                xtype : 'button',
                name:'butRemove',
                text : '移除',
                width : 60
            }],
            bbar: Ext.create('Ext.PagingToolbar', {
                store: me.mainStore,
                displayInfo: true
            })
        });

        var searchPanel=Ext.create('Ext.form.Panel',{
            region:'east',
            name:'searchBar',
            width:260,
            split: true,
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
                    fieldLabel: '用户名',
                    name: 'userName',
                    padding:'5 5 5 5',
                },{
                    fieldLabel: '别名',
                    name: 'alias',
                    padding:'5 5 5 5',
                },{
                    fieldLabel: '部门',
                    name: 'depart',
                    padding:'5 5 5 5',
                }]
        });

        this.items=[searchPanel,grid];
        this.callParent(arguments);
    }
});