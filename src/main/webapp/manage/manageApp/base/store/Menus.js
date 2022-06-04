 Ext.define('manageApp.base.store.Menus',
    {
        extend: 'Ext.data.TreeStore',
        model: 'manageApp.base.model.Menu',
        folderSort: true,
        autoLoad: false,
        root: {
            id: 0,
            name: "管理系统",
            // expanded: true
        },

    });