Ext.define('manageApp.base.store.Roles',
    {
        extend: 'Ext.data.Store',
        model: 'manageApp.base.model.Role',
        autoLoad: false,
        remoteSort:true,
        pageSize:50
    });