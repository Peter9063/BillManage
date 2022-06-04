Ext.define('manageApp.base.view.role.RoleFormWin',{
    extend: 'Ext.window.Window',
    alias:'widget.baseViewRoleFormWin',
    layout:'fit',
    autoShow:true,
    constrainHeader:true,
    isSave:true,

    initComponent:function(){
        var me = this ;
        // me.PCodeStore=Ext.create('manageApp.base.store.PCodes');
        // me.PCodeStore.load();
        var formPanel=Ext.create('Ext.form.Panel',{
            autoScroll:true,
            height: 200,
            width: 300,
            layout:'form',
            fieldDefaults: {
                labelAlign:'right',
                width: 280,
                labelWidth: 80,
                allowBlank: true
            },
            items:[
                // {
                //     layout : 'hbox',
                //     defaultType : 'textfield',
                //     border : false,
                //     padding : '5,0,0,0',
                //     items : [
                //         {
                //             xtype : 'hiddenfield',
                //             name : 'id'
                //         }
                //         , {
                //             fieldLabel : '角色id',
                //             name : 'roleId',
                //             allowBlank: false,
                //             flex : 2
                //         }]
                // },
                {
                    layout:'hbox',
                    defaultType: 'textfield',
                    border: false,
                    padding:'5,0,0,0',
                    items:[
                        {
                            fieldLabel: '角色名称',
                            name: 'name',
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
                //             xtype:'combo',
                //             fieldLabel: '菜单父编号',
                //             name: 'pCode',
                //             queryMode: 'local',
                //             displayField: 'name',
                //             valueField: 'value',
                //             value:'0',
                //             store:me.PCodeStore,
                //             // store:this.states,
                //             // store:manageApp.utils.Model.getConstantStore('UserRole'),
                //             flex: 3
                //         }
                //     ]
                // },
                {
                    layout:'hbox',
                    defaultType: 'textfield',
                    border: false,
                    padding:'5,0,0,0',
                    items:[
                        {
                            fieldLabel: '备注',
                            name: 'description',
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
                            fieldLabel: '序号',
                            name: 'sort',
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
                            fieldLabel: '角色状态',
                            name: 'status',
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
                //             fieldLabel: '图标',
                //             name: 'ioc',
                //             flex: 3
                //         }
                //     ]
                // },
                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             xtype:'combo',
                //             fieldLabel: '角色',
                //             name: 'role',
                //             queryMode: 'local',
                //             displayField: 'name',
                //             valueField: 'value',
                //             value:'0',
                //             store:me.PCodeStore,
                //             // store:this.states,
                //             // store:manageApp.utils.Model.getConstantStore('UserRole'),
                //             flex: 3
                //         }
                //     ]
                // },
                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             xtype:'datefield',
                //             fieldLabel: '创建时间',
                //             name: 'createTime',
                //             value:new Date(),
                //             format:'Y-m-d',
                //             flex: 3
                //         }
                //     ]
                // },
                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             fieldLabel: '创建人',
                //             name: 'createUser',
                //             flex: 3
                //         }
                //     ]
                // },
                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             xtype:'datefield',
                //             fieldLabel: '修改时间',
                //             name: 'updateTime',
                //             value:new Date(),
                //             format:'Y-m-d',
                //             flex: 3
                //         }
                //     ]
                // },
                // {
                //     layout:'hbox',
                //     defaultType: 'textfield',
                //     border: false,
                //     padding:'5,0,0,0',
                //     items:[
                //         {
                //             fieldLabel: '修改人',
                //             name: 'updateUser',
                //             flex: 3
                //         }
                //     ]
                // }
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
            // formPanel.getForm().findField('modifTime').setValue(new Date());
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