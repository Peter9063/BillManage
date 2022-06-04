Ext.define('manageApp.base.view.main.MainFrame',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewMainFrame',
	layout: 'border',
	initComponent: function(){
		Ext.apply(this,{
			items:[
				Ext.create('manageApp.base.view.main.TopFrame', {
					region:'north',
					height:70
				}),
				Ext.create('manageApp.base.view.main.NavigatorFrame', {
					region:'west',
					width:200
				}),
				Ext.create('manageApp.base.view.main.CentFrame', {
					region:'center'
				}),
				Ext.create('manageApp.base.view.main.BottomFrame', {
					region:'south',
					height:25
				})
			]
		});
		this.callParent(arguments);
	}
});