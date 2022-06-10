Ext.define('manageApp.base.store.PCodes',
    {
        extend: 'Ext.data.Store',
        model: 'manageApp.base.model.PCode',
        // autoLoad: false,
        // remoteSort:true,
        pageSize:5000

            // fields: ['value', 'name'],
            // data : [
            //         {"value":"AL", "name":"Alabama"},
            //         {"value":"AK", "name":"Alaska"},
            //         {"value":"AZ", "name":"Arizona"}
            //         //...
            // ]
    });