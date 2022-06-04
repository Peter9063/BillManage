Ext.define('manageApp.base.view.main.TopFrame',{
	extend: 'Ext.panel.Panel',
	alias:'widget.baseViewTopFrame',
	layout: 'border',
	initComponent: function(){
		
		
		
		Ext.apply(this,{
			padding:'5 5 5 5',
//			html:'<h1>&nbsp;&nbsp;测试工单管理&nbsp;&nbsp;</h1>'
			html:'<div style="float:left;"><h1>&nbsp;&nbsp;测试工单管理&nbsp;&nbsp;</h1></div>'+
				 '<div style="float:right;padding:30 10 0 0"><a href="login.jsp">登录</a>&nbsp;&nbsp;<a href="logout.do">退出</a></div> '
		});
		this.callParent(arguments);
	}
});