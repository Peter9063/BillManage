Ext.define('manageApp.base.view.main.BottomFrame',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewBottomFrame',
	layout: 'border',
	initComponent: function(){
		Ext.apply(this,{
			padding:'2 5 2 5',
			html:'测试工单管理'		
		});
		this.callParent(arguments);
	}
});