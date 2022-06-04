Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.Role',
    {
        extend: 'Ext.data.Model',
        idProperty: 'id',
        fields: [

            { name: 'roleId', type: 'int' },//角色id
            { name: 'name', type: 'string' },//角色名称
            { name: 'description', type: 'string' },//备注
            { name: 'sort', type: 'int' },//序号
            { name: 'status', type: 'string' },//角色状态
            { name: 'createTime', type: 'date' },//创建时间
            { name: 'updateTime', type: 'date' },//修改时间
            { name: 'createUser', type: 'String' },//创建人
            { name: 'updateUser', type: 'String' }//修改人

        ],

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
                        read : '../base/role/findRole',//load
                        create : '../base/role/insertRole',//save
                        update : '../base/role/updateRole',//update
                        destroy : '../base/role/deleteRole'//remove
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