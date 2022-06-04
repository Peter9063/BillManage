Ext.define('manageApp.base.view.main.CentFrame',{
	extend: 'Ext.tab.Panel',
	alias:'widget.baseViewCentFrame',
	layout: 'fit',
	initComponent: function(){
		Ext.apply(this,{
			padding:'0 5 0 2',
			items:[
					{
						id: 'HomePage',
						title: '首页',
						padding:'2 2 2 2',
						layout: 'fit',
						html:'<p>欢迎使用!</p>'
					}
			]
		});
		this.callParent(arguments);
	}
});