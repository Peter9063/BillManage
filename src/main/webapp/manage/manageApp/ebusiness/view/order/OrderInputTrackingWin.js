Ext.define('manageApp.ebusiness.view.order.OrderInputTrackingWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.OrderInputTrackingWin',
	//layout:'fit',
	autoShow: true,
	constrainHeader: true,
	isSave: true,
	width: 300,

	initComponent: function () {
		var me = this;

		var upLoadForm = Ext.create('Ext.form.Panel', {
			width: '100%',
			frame: true,
			bodyPadding: '10 10 0',

			defaults: {
				anchor: '100%',
				allowBlank: false,
				msgTarget: 'side',
				labelWidth: 60
			},

			items: [
				// {
				//     xtype: 'textfield',
				//     name: 'fileName',
				//     fieldLabel: '文件名称'
				// },
				{
					xtype: 'filefield',
					emptyText: '请选取上传文件',
					fieldLabel: '上传文件',
					name: 'filePath',
					buttonText: '选取文件...',
				},
				{
					xtype: 'displayfield',
					name: 'href',
					value: '<a  href='+this.href+'>模板</a>'
				}],

			buttons: [{
				text: '保存',
				name: 'btnUpLoadSave'
			}, {
				text: '关闭',
				scope: this,
				handler: this.close
			}]
		});

		this.items = [upLoadForm];
		this.callParent(arguments);
	}
});
