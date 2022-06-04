Ext.define('manageApp.base.view.role.Role',{
    extend: 'Ext.panel.Panel',
    alias:'widget.baseViewRole',
    layout: 'border',
    initComponent: function(){

        var me=this;
        if(this.params==null){
            this.params={};
            this.params.sysType=0;
        }
        //独立Store，按业务类型单独创建。
        var storeId="roleStore"+this.params.sysType;
        me.roleStore= Ext.data.StoreManager.lookup(storeId);
        if(me.roleStore==null){
            me.roleStore=Ext.create('manageApp.base.store.Roles',{storeId: storeId,});
        }

        var gridPanel=Ext.create('Ext.grid.Panel', {
            region:'center',
            name:'grid',
            store: 'manageApp.base.store.Roles',
            // store:me.roleStore,
            columns: [
                {xtype:'rownumberer', width:'3%'},
                { dataIndex: 'roleId', text: '角色id ' ,width:'10%'},//
                { dataIndex: 'name', text: '角色名称 ',width:'10%'},//
                { dataIndex: 'description', text: '备注 ',width:'10%'},//
                { dataIndex: 'sort', text: '排序 ',width:'7%'},//
                { dataIndex: 'status', text: '角色状态',width:'10%'},//
                {dataIndex: "createUser",text: "创建人",width:"10%"},
                { dataIndex: 'updateUser' , text: '修改人', width:'10%'},
                { text: '修改时间',  dataIndex: 'updateTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' },
                { text: '创建时间',  dataIndex: 'createTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' }
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
                },'-',{
                    xtype:"button",
                    name:'butPermission',//permissions
                    text: '资源分配',
                    width: 60
                }, '-',{
                    xtype:"button",
                    name:'butUserDistribution',//permissions
                    text: '用户分配',
                    width: 60
                }
            ],
            bbar: Ext.create('Ext.PagingToolbar', {
                store: 'manageApp.base.store.Roles',
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
                    fieldLabel: '角色名称',
                    name: 'name',
                    padding:'5 5 5 5'
                }
            ]
        });

        this.items=[gridPanel,searchPanel];
        this.callParent(arguments);
    }
});