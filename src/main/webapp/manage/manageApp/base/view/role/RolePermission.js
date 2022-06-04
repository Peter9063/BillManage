Ext.define('manageApp.base.view.role.RolePermission',{
    extend: 'Ext.panel.Panel',
    alias:'widget.baseViewRolePermission',
    layout: 'border',
    initComponent: function(){

        var me=this;
        if(this.params==null){
            this.params={};
            this.params.sysType=0;
        }
        // console.log(this.params);
        // console.log("this,param")
        //独立Store，按业务类型单独创建。
        var storeId="roleTreeStore"+this.params.roleId;

        me.roleTreeStore= Ext.data.StoreManager.lookup(storeId);
        if(me.roleTreeStore==null){
           me.roleTreeStore=Ext.create('manageApp.base.store.RolePermissions',{storeId: storeId});
        }

        // var   store = Ext.create('Ext.data.TreeStore', {
        //     root: {
        //         expanded: true,
        //         children: [
        //                 { children: [
        //                     { text: "book report", leaf: true },
        //                     { text: "algebra", leaf: true}
        //                 ] }
        //         ]
        //     }
        // });
            var treePanel=Ext.create('Ext.tree.Panel',{
            name:'roleTree',
            region:'center',
            title: this.params.roleName,
            width: 200,
            height: 150,
            // store: 'manageApp.base.store.RolePermissions',
            store:me.roleTreeStore,
            rootVisible: true, //根节点root显示
            // listeners:{
            //     "checkchange": function(node, state) {
            //         console.log(node)
            //         console.log(state);
            //         console.log("chexkchange")
            //         node.cascade(function(node){    //cascade是有瀑布之意
            //             // node.attributes.checked = state;
            //             // node.ui.checkbox.checked = state;
            //             return true;
            //         });
            //     },
            //     'itemclick':function () {
            //       console.log("view； item click")
            //     },
            //     'click': function(node, e) {
            //
            //         if (node.isLeaf()) {
            //             console.log("lesf click ")
            //         }
            //     }
            // },
            tbar:[{
                            xtype : 'button',
                            name:'butSave',
                            text : '保存',
                            width : 60
                        }]
        });
        // var gridPanel=Ext.create('Ext.grid.Panel', {
        //     region:'center',
        //     name:'grid',
        //     store: 'manageApp.base.store.RolePermissions',
        //     // store:me.roleStore,
        //     columns: [
        //         {xtype:'rownumberer', width:'3%'},
        //         { dataIndex: 'roleId', text: '角色id ' ,width:'10%'},//
        //         { dataIndex: 'name', text: '角色名称 ',width:'10%'},//
        //         { dataIndex: 'description', text: '备注 ',width:'10%'},//
        //         { dataIndex: 'sort', text: '排序 ',width:'7%'},//
        //         { dataIndex: 'status', text: '角色状态',width:'10%'},//
        //         {text: "创建人",dataIndex: "createUser",width:"10%"},
        //         { text: '修改人',  dataIndex: 'updateUser' , width:'10%'},
        //         { text: '修改时间',  dataIndex: 'updateTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' },
        //         { text: '创建时间',  dataIndex: 'createTime' , width:'10%',xtype: 'datecolumn',   format:'Y-m-d h:i:s' }
        //     ],
        //     selModel: Ext.create('Ext.selection.CheckboxModel', {mode: 'MULTI'}),//MULTI SINGLE
        //     viewConfig: {
        //         stripeRows: true,
        //         enableTextSelection: true
        //     },
        //     tbar:[
        //         {
        //             xtype: 'button',
        //             name:'butAdd',
        //             text: '添加',
        //             width: 60
        //         },'-',
        //         {
        //             xtype : 'button',
        //             name:'butEdit',
        //             text : '修改',
        //             width : 60
        //         },'-',{
        //             xtype: 'button',
        //             name:'butDel',
        //             text: '删除',
        //             width: 60
        //         },'-',{
        //             xtype:"button",
        //             name:'butPermission',//permissions
        //             text: '权限',
        //             width: 60
        //         }
        //     ],
        //     bbar: Ext.create('Ext.PagingToolbar', {
        //         store: 'manageApp.base.store.RolePermissions',
        //         displayInfo: true
        //     })
        // });

        var searchPanel=Ext.create('Ext.form.Panel',{
            region:'east',
            title:'查询条件',
            name:'searchBar',
            hidden: true,
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


      /*  treepanel 菜单树 测试

      Ext.define('Task', {
            extend: 'Ext.data.Model',
            fields: [
                {name: 'task',     type: 'string'},
                {name: 'user',     type: 'string'},
                {name: 'duration', type: 'string'},
                // {name: 'done',     type: 'boolean'}
            ]
        });
        var store = Ext.create('Ext.data.TreeStore', {
            model: 'Task',
            proxy: {
                type: 'ajax',
                //the store will get the content from the .json file
                url: './resources/test.json'
            },
            folderSort: true
        });
            var treepanelMenu=Ext.create('Ext.tree.Panel',{
            region:'west',
            title: 'Core Team Projects',
            width:500,
            // renderTo: Ext.getBody(),
            collapsible: true,
            useArrows: true,
            rootVisible: false,
            store: store,
            multiSelect: true,
            columns: [
                {xtype:'rownumberer', width:20},{
                xtype: 'treecolumn', //this is so we know which column will show the tree
                text: 'Task',
                width: 200,
                sortable: true,
                dataIndex: 'task',
                locked: true
            }, {
                text: 'Assigned To',
                width: 150,
                dataIndex: 'user',
                sortable: true
            }, {
                    text: 'duration',
                    width: 150,
                    dataIndex: 'duration',
                    sortable: true
                }],
            selModel: Ext.create('Ext.selection.CheckboxModel', {mode: 'MULTI'}),
        });*/

        // this.items=[treepanelMenu];
        this.items=[treePanel,searchPanel];
        this.callParent(arguments);
    }
});