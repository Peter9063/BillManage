Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.RoleAndUserDistribution',
    {
        extend: 'Ext.data.Model',
        idProperty: 'id',
        fields: [

            {name: 'id', type: 'int'},//角色id
            {name: 'userName', type: 'String'}, //用户名
            {name: 'alias', type: "String"}, //别名
            {name: 'depart', type: 'String'}
        ],

        proxy:
            {
                type: 'ajax',
                reader:
                    {
                        type: 'json',
                        root: 'data',
                        totalProperty: 'total'
                    },
                api:
                    {
                        read: '../forum/user/findAllByRoleIds.do',//load
                        // create : '../base/role/insertRole',//save
                        // update : '../base/role/updateRole',//update
                        // destroy : '../base/role/deleteRole'//remove
                    },
                actionMethods:
                    {
                        read: 'GET',
                        create: 'POST',
                        update: 'POST',
                        destroy: 'POST'
                    },
                writer: {
                    type: 'json',
                    allowSingle: false
                }
            }
    });