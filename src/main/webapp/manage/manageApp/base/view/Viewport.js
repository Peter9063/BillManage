Ext.define('manageApp.base.view.Viewport',{
	extend: 'Ext.Viewport',
	layout: 'fit',
	initComponent: function(){
		this.items={
			xtype:'panel',
			layout:'fit',
			items:[{xtype:'baseViewMainFrame'}]
		};
		this.callParent(arguments);
	}
});