


Ext.application({
    name: 'manageApp',
    appFolder:'manageApp',
//    autoCreateViewport: true,
	controllers: [
		'manageApp.base.controller.main.MainFrame'
	],
    launch: function() {
    	Ext.create('manageApp.base.view.Viewport');
    }
});