Ext.define('manageApp.base.view.menu.Menu',{
    extend: 'Ext.panel.Panel',
    alias:'widget.baseViewMenu',
    layout: 'border',
    initComponent: function(){

        // var gridPanel=Ext.create('Ext.grid.Panel', {
        /**
         * 测试菜单树
         */
        var gridPanel=Ext.create('Ext.tree.Panel', {
            region:'center',
            name:'grid',
            store: 'manageApp.base.store.Menus',
                // rootVisible: false,
            columns: [
                {xtype:'rownumberer', width:'3%'},
                { xtype: 'treecolumn',dataIndex: 'name', text: '菜单名称 ',width:'20%'},//菜单名称
                { dataIndex: 'menuId', text: '菜单id ' ,width:'7%'},//菜单名
                { dataIndex: 'code', text: '菜单编号 ',width:'7%'},//菜单编号
                { dataIndex: 'pId', text: '菜单父id ',width:'7%'},//菜单父编号
                { dataIndex: 'url', text: 'url地址',width:'10%'},//url地址
                { dataIndex: 'sort', text: '菜单排序号',width:'10%'},//菜单排序号
		        { dataIndex: 'levels', text: '菜单层级',width:'10%'},//菜单层级
                { dataIndex: 'status', text: '菜单状态',width:'10%'},//菜单状态
                { text: '是否菜单',   dataIndex: 'menuFlag' , width:'10%' ,renderer:manageApp.utils.Model.convertMenuTypeValue},

                {text: "创建人",dataIndex: "createUser",width:"10%"},
                { text: '修改人',  dataIndex: 'updateUser' , width:'10%'},
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
                }
            ],
            bbar: Ext.create('Ext.PagingToolbar', {
                store: 'manageApp.base.store.Menus',
                displayInfo: true,
                hidden:true
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
                    fieldLabel: '菜单名称',
                    name: 'name',
                    padding:'5 5 5 5'
                }
                // ,
                // {
                //     fieldLabel: "菜单编号",
                //     name: "code",
                //     padding: "5 5 5 5"
                // }
            ]
        });

        this.items=[gridPanel,searchPanel];
        this.callParent(arguments);
    }
});