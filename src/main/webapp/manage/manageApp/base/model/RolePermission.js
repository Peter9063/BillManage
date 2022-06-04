Ext.require('manageApp.utils.Model');
Ext.define('manageApp.base.model.RolePermission',
    {
        extend: 'Ext.data.TreeModel',
        idProperty: 'id',
        // autoLoad: false,
        fields: [

            {name:'text',type:'string'}//资源名称
            // {name:'disabled',type:'String'},
            // {name:'children',type:'json'}
            // { name: 'menuId', type: 'int' },//菜单id
            // { name: 'name', type: 'string' },//菜单名称

            /**
             * 角色id
             * 资源id
             * 资源名称
             *
             */

        ],

        proxy :
            {
                type : 'ajax',
                reader :
                    {
                        type : 'json',
                        root:''
                    },

                api :
                    {
                        // read : './resources/RolePermission.json',//load
                        read: '../base/menu/getMenuTreeListByRoleIds',
                        create : '../base/relation/insertRelation',//save
                        update : '../base/update/modelRolePer',//update
                        destroy : '../base/destroy'//remove
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