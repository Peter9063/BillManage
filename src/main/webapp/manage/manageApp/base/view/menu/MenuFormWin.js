Ext.define('manageApp.base.view.menu.MenuFormWin',{
    extend: 'Ext.window.Window',
    alias:'widget.baseViewMenuFormWin',
    layout:'fit',
    autoShow:true,
    constrainHeader:true,
    isSave:true,

    initComponent:function(){
        var me = this ;
            me.PCodeStore=Ext.create('manageApp.base.store.PCodes');
            me.PCodeStore.load();

        var formPanel=Ext.create('Ext.form.Panel',{
            autoScroll:true,
            height: 260,
            width: 300,
            layout:'form',
            fieldDefaults: {
                labelAlign:'right',
                width: 280,
                labelWidth: 80,
                allowBlank: true
            },
            items:[

                {
                    layout:'hbox',
                    defaultType: 'textfield',
                    border: false,
                    padding:'5,0,0,0',
                    items:[
                        {
                            xtype:'combo',//
                            fieldLabel: '菜单父id',
                            name: 'pId',
                            queryMode: 'local',
                            displayField: 'name',
                            valueField: 'menuId',
                            value:'0',
                            store:me.PCodeStore,
                            // store:this.states,
                            // store:manageApp.utils.Model.getConstantStore('UserRole'),
                            flex: 3
                        }
                    ]
                },
                {
                    layout : 'hbox',
                    defaultType : 'textfield',
                    border : false,
                    padding : '5,0,0,0',
                    items : [
                        {
                            fieldLabel : '菜单名称',
                            name : 'name',
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
                            fieldLabel: '菜单编号',
                            name: 'code',
                            allowBlank: false,
                            flex: 3
                        }
                    ]
                },

                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             fieldLabel: '是否菜单',
                //             name: 'menuFlag',
                //             allowBlank: false,
                //             flex: 3
                //         }
                //     ]
                // },
                {
                    xtype:'combo',
                    fieldLabel: '是否菜单',
                    name: 'menuFlag',
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'value',
                    store:manageApp.utils.Model.getConstantStore('menuType'),
                    padding:'5 5 5 5'
                },
                {
                    layout:'hbox',
                    defaultType: 'textfield',
                    border: false,
                    padding:'5,0,0,0',
                    items:[
                        {
                            fieldLabel: 'url',
                            name: 'url',
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
                            fieldLabel: '排序',
                            name: 'sort',
                            flex: 3
                        }
                    ]
                }
            ]
        });



        this.items=[formPanel];
        this.formPanel=formPanel;

        if(this.isSave==true){
            var value=this.record.data.name;
            var id=this.record.data.menuId;
            this.formPanel.getForm().findField("pId").setValue(id);

            console.log("issave");

            // this.formPanel.getForm().findField("pId")
            this.formPanel.getForm().findField("pId").readOnly=true;
            // this.formPanel.items.items[0].setDisabled(true);

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
            // formPanel.getForm().get
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