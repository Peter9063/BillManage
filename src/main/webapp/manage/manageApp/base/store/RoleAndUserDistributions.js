Ext.define('manageApp.base.store.RoleAndUserDistributions',
    {
        extend: 'Ext.data.Store',
        model: 'manageApp.base.model.RoleAndUserDistribution',
        autoLoad: false,
        // remoteSort:true,
        pageSize:5000
        ,
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
                        read : '../forum/user/findAllByRoleIds.do',//load
                        // create : '../base/role/insertRole',//save
                        // update : '../base/role/updateRole',//update
                        destroy : '../base/roleUserRelation/delete'//remove
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