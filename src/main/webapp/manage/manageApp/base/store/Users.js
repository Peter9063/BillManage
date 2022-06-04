Ext.define('manageApp.base.store.Users',
    {
        extend: 'Ext.data.Store',
        model: 'manageApp.base.model.User',
        autoLoad: false,
        remoteSort:true,
        pageSize:50,
        proxy :
            {
                type : 'ajax',
                reader :
                    {
                        type : 'json',
                        root:'data',
                        totalProperty:'total'
                    },
                api :
                    {
                        read : '../base/user/findPage.do',//load
                        create : '../base/user/save.do',//save
                        update : '../base/user/save.do',//update
                        destroy : '../base/user/delete.do'//remove
                    },
                actionMethods :
                    {
                        read : 'GET',
                        create : 'POST',
                        update : 'POST',
                        destroy : 'POST'
                    },
                writer:{
                    type:'json',
                    allowSingle:false
                }
            }
    });