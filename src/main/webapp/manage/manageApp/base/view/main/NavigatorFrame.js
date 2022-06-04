Ext.define('manageApp.base.view.main.NavigatorFrame',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewNavigatorFrame',
	layout: {
	    type: 'accordion',
	    titleCollapse: false,
	    animate: true,
	    activeOnTop: true
	},
	initComponent: function(){
		Ext.apply(this,{
			padding:'0 2 0 5',
			title:'菜单',
			split: true,
			collapsible: true,
			items:[
			
			]
		});
		this.callParent(arguments);
	}
});