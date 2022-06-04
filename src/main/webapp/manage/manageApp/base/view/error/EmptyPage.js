Ext.define('manageApp.base.view.error.EmptyPage',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewEmptyPage',
	layout: 'border',
	initComponent: function(){
		Ext.apply(this,{
			padding:'5 5 5 5',
			html:'<h1>功能开发中...</h1>'
			
		});
		this.callParent(arguments);
	}
});