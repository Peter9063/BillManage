Ext.define('manageApp.base.store.RolePermissions',
    {
        extend: 'Ext.data.TreeStore',
        model: 'manageApp.base.model.RolePermission',
        autoLoad: false,

            root:{
                id : 0 ,
                text: "管理系统",
                // expanded: true//不能加，会有重复行数据出现
            }

    });
