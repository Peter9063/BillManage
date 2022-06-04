Ext.define('manageApp.base.view.role.RoleAndUserDistributionWin', {
    extend: 'Ext.window.Window',
    alias: 'widget.baseViewRoleAndUserDistributionWin',
    layout: 'fit',
    autoShow:true,
    constrainHeader:true,

    initComponent: function () {

        var me=this;

        if(this.params==null){
            this.params={};
        }
        this.params.type=1;//待分配类型 type = 1 ？ 待分配 ：已分配（2）
        // console.log(this.params);
        // console.log("this.params");
        var storeId="menuTreeStore"+this.params.type;
        me.mainStore= Ext.data.StoreManager.lookup(storeId);
        if(me.mainStore==null){
            me.mainStore=Ext.create('manageApp.base.store.RoleAndUserDistributions',{storeId: storeId});
        }
        var grid=Ext.create('Ext.grid.Panel',{
            name:'grid',
            title: this.params.roleName+": 待分配用户",
            width: 500,
            height:500,
            store:me.mainStore,
            columns: [
                {xtype:'rownumberer', width:'7%'},
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
            }],
            bbar: Ext.create('Ext.PagingToolbar', {
                store: me.mainStore,
                displayInfo: true
            })
        });


        var searchPanel=Ext.create('Ext.form.Panel', {
            region: 'north',
            // title:'查询条件',
            name: 'searchBar',
            width: 500,
            frame : true,
            split: true,
            margin: '0,0,0,0',
            // collapsible: true,
            defaultType: 'textfield',
            tbar: [
                {
                    xtype: 'button',
                    name: 'butFind',
                    text: '查询',
                    width: 60
                }, '-',
                {
                    xtype: 'button',
                    name: 'butReset',
                    text: '重置',
                    width: 60
                }
            ],

            items: [
                {
                    xtype:'panel',
                    layout: 'column',
                    frame: true,
                    // width:300,
                    // height:100,
                    margin: '0,0,0,20',
                    defaultType: 'textfield',
                    items: [
                        {    padding: '5 5 5 5',
                            labelWidth : 50,
                            columnWidth: 0.33,
                            name: 'userName',
                            fieldLabel: '用户名',
                            // flex: 1

                        },{    padding: '5 5 5 5',
                            labelWidth : 50,
                            columnWidth: 0.33,
                            name: 'alias',
                            fieldLabel: '用户别名',
                            // flex: 1
                        }, {    padding: '5 5 5 5',
                            labelWidth : 50,
                            columnWidth: 0.33,
                            name: 'depart',
                            fieldLabel: '部门',
                            // flex: 1

                    }]
                },
                // {
                //     fieldLabel: '用户名',
                //     name: 'userName',
                //     padding: '5 5 5 5',
                // },{
                //     fieldLabel: '别名',
                //     name: 'alias',
                //     padding:'5 5 5 5',
                // },{
                //     fieldLabel: '部门',
                //     name: 'depart',
                //     padding:'5 5 5 5',
                // }
            ]
        });

        this.items=[searchPanel,grid];
        this.callParent(arguments);


    }
});