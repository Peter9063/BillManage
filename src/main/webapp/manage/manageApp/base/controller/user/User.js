Ext.define('manageApp.base.controller.user.User',{
	extend: 'Ext.app.Controller',
	stores:['manageApp.base.store.Users'],
	views:['manageApp.base.view.user.User'
	],
	refs:[
		{
			ref: 'searchBar',
			selector: 'baseViewUser form[name=searchBar]'
		},
		{
			ref: 'grid',
			selector: 'baseViewUser grid[name=grid]'
		}
	],
	aliasView:'baseViewUser ',
	aliasSearchBar:"form[name=searchBar] ",
	aliasGrid:'grid[name=grid] ',
	init: function(application){
		// this.gridStore=this.getStore('manageApp.base.store.Users');
		this.control({
			'baseViewUser grid button[name=butAdd]':{
				click: this.saveClick
			},
			'baseViewUser grid button[name=butEdit]':{
				click: this.editClick
			},
			'baseViewUser grid button[name=butDel]':{
				click: this.delClick
			},
			'baseViewUser form button[name=butFind]':{
				click: this.findClick,
				afterrender: this.findClick
			},
			'baseViewUser form button[name=butReset]':{
				click: this.resetClick
			},
			'baseViewUserFormWin button[name=btnWinFormSave]':{
				click:this.winFromSave
			},
			'baseViewUserFormWin button[name=btnWinFormModif]':{
				click:this.winFormModif
			}
		});
	},

	onLaunch:function(application){
		this.loadGridDate();
//		添加
//		修改

	},
	saveClick:function(obj, event, eOpts){

		var view =obj.up(this.aliasView);
		var searchBar =view.down(this.aliasSearchBar);

		console.log('saveClick');
		this.formWin=Ext.create('manageApp.base.view.user.UserFormWin',{
			title:'新增',
			isSave:true,
			sysType:searchBar.getForm().findField('sysType').getValue(),
			parentView:view
		});
	},
	editClick:function(obj, event, eOpts){
		console.log('editClick');
		var view =obj.up(this.aliasView);
		var searchBar =view.down(this.aliasSearchBar);
		var grid = view.down(this.aliasGrid);
		var selectedRows=grid.getSelectionModel().getSelection();
		// var selectedRows=this.getGrid().getSelectionModel().getSelection();
		if(selectedRows.length==1){
			console.log(selectedRows[0]);
			this.formWin=Ext.create('manageApp.base.view.user.UserFormWin',{
				title:'修改',
				isSave:false,
				sysType:searchBar.getForm().findField('sysType').getValue(),
				record:selectedRows[0],
				parentView:view
			});
		}
		else{
			Ext.Msg.alert('Status', '只能选择一条记录进行修改操作!');
		}
	},
	delClick:function(obj, event, eOpts){

		var view=obj.up(this.aliasView);
		var grid=view.down(this.aliasGrid);
		var gridStore=view.mainStore;
		if(grid==null || grid==undefined || grid=='undefined'){
			console.log("unload view's grid ");
			Ext.Msg.alert('Error', '界面出错，没有加载到界面控件.');
			return;
		}
		// var selectedRows=this.getGrid().getSelectionModel().getSelection();
		var selectedRows=grid.getSelectionModel().getSelection();
		if(selectedRows.length<1){
			Ext.Msg.alert('Status', '必需选取一条记录进行删除操作!');
		}
		else{
			//****************单条记录删除方式,但不更新store中的内容*********
			//selectedRows[0].destroy();
			//************************************************************

			//********借助store接口进行更新*******************************
			// if(this.gridStore!=null || this.gridStore!='undefined'){
			if(gridStore!=null || gridStore!='undefined'){
				// this.gridStore.remove(selectedRows);
				gridStore.remove(selectedRows);
				this.syncGridDate({
					success:function(batch, eOpts){
						var msg=batch.proxy.reader.rawData.message;
						if(msg==null){
							msg='Request failed.';
						}
						Ext.Msg.alert('Status', msg);
					},
					failure:function(batch, eOpts){
						var msg=batch.proxy.reader.rawData.message;
						if(msg==null){
							msg='Request failed.';
						}
						Ext.Msg.alert('Status', msg);
					},
					scope:this.gridStore
				},gridStore);
			}
			//**********************************************************
		}
	},
	/**
	 * 查询点击事件
	 */
	findClick:function(obj){

		var searchBar = obj.up(this.aliasView+this.aliasSearchBar);
		var view =obj.up(this.aliasView);
		var store=view.mainStore;
		view.roleStore.load();
		this.loadGridDate(searchBar,store);
	},
	/**
	 * 查询栏重置
	 */
	resetClick:function(){
		if(this.getSearchBar()!=null || this.getSearchBar()!='undefined'){
			this.getSearchBar().getForm().reset();
		}
	},
	/**
	 * 加载grid的数据
	 */
	loadGridDate:function(searchBar,store){
		if(searchBar==null || searchBar==undefined || searchBar=='undefined' ||
			store==null || store==undefined ||store=='undefined'){
			console.log("unload view's searchBar or store ");
			Ext.Msg.alert('Error', '界面出错，没有加载到界面控件.');
			return;
		}
		console.log("used Views owned SearchBar");
		store.proxy.extraParams=manageApp.utils.Form.getCommitParam(searchBar.getForm());
		store.load({page: 1});
	},
	/**
	 * 添加,删除后,同步grid中的数据
	 * options
	 * 	.success callback
	 *  .failure callback
	 *  .scope
	 */
	syncGridDate:function(options,gridStore){
		// if(this.gridStore!=null || this.gridStore!='undefined'){
		if(gridStore!=null || gridStore!='undefined'){
			var scope=null;
			if(typeof(options)!='undefined'){
				scope=options.scope;
			}
			gridStore.sync({
				// this.gridStore.sync({
				success : function(batch, eOpts) {
					if(typeof(options)!='undefined' && typeof(options.success)=='function'){
						if(scope!=null){
							options.success.call(scope,batch, eOpts);
						}
						else{
							options.success(batch, eOpts);
						}
					}
				},
				failure : function(batch, eOpts) {
					this.rejectChanges();
					if(typeof(options)!='undefined' && typeof(options.success)=='function'){
						if(scope!=null){
							options.failure.call(scope,batch, eOpts);
						}
						else{
							options.failure(batch, eOpts);
						}
					}
				},
				// scope:this.gridStore
				scope:gridStore
			});
		}
	},
	winFromSave:function(){
		if (!this.formWin.formPanel.getForm().isValid()) {
			return ;
		}

		var newRecord=this.formWin.formPanel.getForm().getValues();

		var model = Ext.create('manageApp.base.model.User',newRecord);

		model.save({
			callback:function(records,operation,success){
				if(success){
					if(operation.request.proxy.reader.rawData.success=='true'||
						operation.request.proxy.reader.rawData.success==true){
						success=true;
					}
					else{
						success=false;
					}
				}
				if(success){
					var view=this.formWin.parentView;
					var searchBar=view.down(this.aliasSearchBar);
					var store=view.mainStore;
					this.formWin.close();
					this.formWin.parentView=null;
					this.formWin=null;

					// var searchBar = obj.up(this.aliasSearchBar);
					// var view =obj.up(this.aliasView);
					// var store=view.mainStore;
					this.loadGridDate(searchBar,store);
				}
				else{
					var msg=operation.request.proxy.reader.rawData.message;
					if(msg==null){
						msg='save faile.';
					}
//					this.formWin.close();
//					this.formWin=null;
					Ext.Msg.alert('Status', msg);
				}
			},
			scope:this
		});
	},
	winFormModif:function(){
		console.log('edit');
		if (!this.formWin.formPanel.getForm().isValid()) {
			return ;
		}
		var model=this.formWin.formPanel.getForm().getRecord();
		this.formWin.formPanel.getForm().updateRecord(model);

//		this.gridStore.commitChanges();//该语句为本地保存,保存后this.gridStore.getModifiedRecords()会清掉;
// 		var gridStore=this.gridStore;
		var gridStore=this.formWin.parentView.mainStore;
		this.syncGridDate({
			success:function(batch, eOpts){
				var msg=batch.proxy.reader.rawData.message;
				if(msg==null){
					msg='Changes saved successfully.';
				}
				Ext.Msg.alert('Status',msg);
				var me=this;
				this.close();
			},
			failure:function(batch, eOpts){
				var msg=batch.proxy.reader.rawData.message;
				if(msg==null){
					msg='Request failed.';
				}
				Ext.Msg.alert('Status', msg);
			},
			scope:this.formWin
		},gridStore);

	}
});