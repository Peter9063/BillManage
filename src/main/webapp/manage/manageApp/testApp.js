Ext.application({
    name: 'manageApp',
    appFolder:'manageApp',
	controllers: [
		//1.修改成待测试的controller
//	'manageApp.base.controller.Test'
		'manageApp.forum.controller.bill.Bill'
	],
    launch: function() {
    	
		Ext.create('manageApp.forum.view.bill.Bill',{
			//2.修改成待测试的view层别名
			viewName:'baseviewTest'
    	});
    	
    }
});