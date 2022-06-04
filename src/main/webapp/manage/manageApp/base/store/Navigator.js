Ext.define('manageApp.base.store.Navigator', {
    extend: 'Ext.data.TreeStore',
    
//	model: 'manageApp.base.model.Navigator',
	
    clearOnLoad: true,
    nodeParam: 'parentId',
    lazyFill: true,
    root: {
    	id: 0,
    	expanded: true,
    	loaded: true,
    	text: 'root'
    },
	proxy: {
		type: 'ajax',
		actionMethod: 'GET',
		url:'menu.do',
		// url: 'resources/Navigator.json',
		extraParams: {
		},
		reader: {
			type: 'json',
			successProperty: 'success',
			messageProperty: 'message'
		},
	    buffer : 1
	},
	autoLoad: false
});
