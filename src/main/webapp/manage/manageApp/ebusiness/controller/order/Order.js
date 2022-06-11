﻿Ext.define('manageApp.ebusiness.controller.order.Order', {
	extend: 'Ext.app.Controller',
	// models:['manageApp.ebusiness.model.FlowTemplate'],
	views:['manageApp.ebusiness.view.order.Order'
	],
	//stores:['manageApp.forum.store.BillRunInfos'],
	alias4Views:'ebusinessViewOrder ',
	alias4SearchBar:"form[name=searchBar] ",
	alias4Grid:'grid[name=grid] ',
	init: function(application){
		var regViewEvent={};
		regViewEvent[this.alias4Views+'grid button[name=butInput]']={click: this.inputClick};
		regViewEvent[this.alias4Views+'grid button[name=butExport]']={click: this.exportClick};
		regViewEvent[this.alias4Views+'grid button[name=butAdd]']={click: this.saveClick};
		regViewEvent[this.alias4Views+'grid button[name=butEdit]']={click: this.editClick};
		regViewEvent[this.alias4Views+'grid button[name=butDel]']={click: this.delClick};
		regViewEvent[this.alias4Views+'form button[name=butFind]']={click: this.findClick,afterrender: this.findClick};
		regViewEvent[this.alias4Views+'form button[name=butReset]']={click: this.resetClick};
		regViewEvent['forumViewBillFormWin button[name=btnWinFormSave]']={click:this.winFromSave};
		regViewEvent['forumViewBillFormWin button[name=btnWinFormModif]']={click:this.winFormModif};
		regViewEvent['forumBillInputWin button[name=btnWinFormReset]']={click:this.winFormInputerReset};
		regViewEvent['forumBillInputWin button[name=btnWinFormSave]']={click:this.winFromInputerSave};

		regViewEvent['forumViewBillConfWin button[name=btnWinFlowClear]']={click: this.flowClearClick};
		regViewEvent['forumBillFlowClearWin button[name=btnWinFormClear]']={click: this.winFormFlowClear};
		regViewEvent['forumViewBillConfWin button[name=btnWinFormSaveAll]']={click: this.winFromConfSave};
		regViewEvent['forumViewBillConfWin button[name=btnWinReInitAllot]']={click: this.winFromReInitAllot};

		this.control(regViewEvent);
	},
	onLaunch:function(application){

	},
	/**
	 * 参数配置窗口 保存全部
	 * @param obj
	 */
	winFromConfSave:function(obj){
		var view = this.formWin;
		var me= this;
		var stores = view.mainStores;
		let updatedRecords=[] ;
		for(let index in stores){
			let store = stores[index];
			updatedRecords=updatedRecords.concat(store.getUpdatedRecords());
		}
		var mainStore= Ext.create('manageApp.forum.store.ConfFlowInstances', {storeId: "mainStore",});
		mainStore.loadRecords(updatedRecords,true);
		me.syncGridDate({
			success:function(batch, eOpts){
				for(let index in stores){
					stores[index].load();
				}
				Ext.Msg.alert('Status','保存成功.');
			},
			failure:function(batch, eOpts){
				var msg='保存失败';
				if(batch.proxy.reader.rawData.message != undefined ||
					batch.proxy.reader.rawData.message !='' ){
					msg=batch.proxy.reader.rawData.message;
				}
				Ext.Msg.alert('Status',msg);
			},
			scope:mainStore
		},mainStore);

	},
	winFormFlowClear:function(obj){
		if (!this.formWin2.formPanel.getForm().isValid()) {
			return ;
		}
		let me = this;
		let seqNum = this.formWin2.formPanel.getForm().findField("seqNum").getValue();
		let billId = this.formWin2.formPanel.getForm().findField("billId").getValue();
		console.log(seqNum);
		manageApp.utils.Ajax.request2(
			"../forum/testDevice/flowClear",//清理工单流程（按序号清理）
			'POST',
			{orderIndex:seqNum,billId:billId},//字符串
			function (options, success, response) {
				//todo da 分析结果
				var message = JSON.parse(response.responseText)
				if (message.success) {
					if (me.formWin2!=null){
						me.formWin2.close();
					}
					Ext.Msg.alert('Success', message.message);
				} else {
					Ext.Msg.alert('Error', message.message);
				}
			});
	},
	flowClearClick :function(obj){
		var me= this;
		var view = this.formWin;
		me.formWin2=Ext.create('manageApp.forum.view.bill.BillFlowClearWin',{
			width: 400,
			height: 100,
			title:"流程清理",
			isSave:false,
			records:view.records,
			parentView:view
		})
	},
	winFromReInitAllot:function(obj){
		var me =this;
		let formWin = me.formWin;
		let parentView = formWin.parentView ,title = formWin.title;
		console.log(formWin.records)

		Ext.Msg.confirm("信息", "是否确认重新初始化配置信息", function (button) {
			//     console.log(button)
			if (button == "yes") {
				manageApp.utils.Ajax.request2(
					"../forum/confFlowInstance/reInitAllot",//初始化配置信息
					'POST',
					{billId: formWin.records[0].billId},//字符串
					function (options, success, response) {
						//todo da 分析结果
						var message = JSON.parse(response.responseText)
						if (message.success) {
							if (me.formWin!=null){
								me.formWin.close();
							}
							me.formWin=Ext.create('manageApp.forum.view.bill.BillConfWin',{
								width: 800,
								height: 600,
								title:title,
								isSave:false,
								records:message.data,
								parentView:parentView
							})
						} else {
							Ext.Msg.alert('Error', message.message);
						}
					});
			}
		})
	},
	initAllot:function(obj,record,title,view,grid,url){
		manageApp.utils.Ajax.request2(
			url||"../forum/confFlowInstance/initAllot",//初始化配置信息
			'POST',
			{billId: record.raw.id},//字符串
			function (options, success, response) {
				//todo da 分析结果
				var message = JSON.parse(response.responseText)
				if (message.success) {
					if (record.raw.confStatus != 1){
						grid.getStore().reload();
					}
					if (obj.formWin!=null){
						obj.formWin.close();
					}
					obj.formWin=Ext.create('manageApp.forum.view.bill.BillConfWin',{
						width: 800,
						height: 600,
						title:title,
						isSave:false,
						records:message.data,
						parentView:view
					})
				} else {
					Ext.Msg.alert('Error', message.message);
				}
			});
	},

	winFormSelect:function(obj,records,eOpts){
		console.log("winFormSelect")
		console.log(obj)
		console.log(records)
		console.log(eOpts)
		var view=obj.up(this.alias4Views);
		var me =this;
		var productCode = this.formWin.formPanel.getForm().findField("productCode").getValue();
		var pattern = /^[A-Z]{2,}[\-][0-9]{5}/;
		if(!pattern.test(productCode)){
			Ext.Msg.alert('Status','产品编码格式不正确');
			this.formWin.formPanel.getForm().findField("flowTemplateId").reset();
			return;
		}

		manageApp.utils.Ajax.request2('../forum/confFlowItem/findTestItemsEnable.do','post',
			{
				'flowTemplateld':records[0].data.id
			},
			function(options,success,response){
				var jsonObj=Ext.JSON.decode(response.responseText);
				console.log(jsonObj)
				if(jsonObj!=null && jsonObj.data!=null&&jsonObj.data.length>0){
					// params.flowTemplateInstance=jsonObj.data;
// mainPanel.jumpPage('测试工单执行情况','manageApp.forum.controller.device.Device',params);
					me.formWin2=Ext.create('manageApp.forum.view.bill.BillConfWin',{
						width: 800,
						height: 600,
						title:'参数配置',
						parentView:view,
						records:jsonObj.data,
						layoutConfig: {columns: jsonObj.data.length},
					});

				}

			});


	},
	winFormInputerReset:function(obj, event, eOpts){
		console.log('winFormInputerReset');
		var treePanel=obj.up().up().down('treepanel')
		treePanel.getStore().load();
	},
	winFromInputerSave:function(obj, event, eOpts){
		var me=this;
		console.log('winFromInputerSave');
		var win=obj.up().up();
		var treePanel=obj.up().up().down('treepanel');
		var parentView=win.parentView;
		var grid=parentView.down('grid[name=grid]');
		var searchBar=parentView.down('form[name=searchBar]');

		var date=new Date();
		//********借助store接口进行更新*******************************
		var store=treePanel.getStore();
		var map=store.tree.nodeHash;
		for(var key in map){
			if(key!='root'){
				map[key].set('modifTime',Ext.Date.format(date,'Y-m-d'));
			}
		}
		if(store!=null || store!='undefined'){
			me.syncGridDate({
				success:function(batch, eOpts){
					Ext.Msg.alert('Status','保存成功.');
					win.close();
					me.loadGridDate(searchBar,grid.getStore());
				},
				failure:function(batch, eOpts){
					var msg='保存失败';
					if(batch.proxy.reader.rawData.message != undefined ||
							batch.proxy.reader.rawData.message !='' ){
							msg=batch.proxy.reader.rawData.message;
					}
					Ext.Msg.alert('Status',msg);
				},
				scope:store
			},store);
		}
		//**********************************************************
	},
	/**
	 * 导出数据
	 */
	exportClick:function(obj){
		console.log('exportClick');
		var view=obj.up(this.alias4Views);
		var searchBar=view.down(this.alias4SearchBar);
		var conditions=manageApp.utils.Form.getCommitParam(searchBar.getForm());
		conditions=Ext.Object.toQueryString({conditions:Ext.encode(conditions)})
		window.open('../forum/testBill/bill/exportExcel.do?'+conditions);
	},
	inputClick:function(obj, event, eOpts){
		console.log('inputClick');
		var me=this;
		var view=obj.up(this.alias4Views);

		this.formWin=Ext.create('manageApp.forum.view.bill.BillInputWin',{
	        width: 800,
	        height: 600,
			title:'导入工单',
			parentView:view
		});
	},
	saveClick:function(obj, event, eOpts){
		console.log('saveClick');
		var me=this;
		var view=obj.up(this.alias4Views);
		var searchBar=view.down(this.alias4SearchBar);
		if(searchBar==null || searchBar==undefined || searchBar=='undefined'){
			console.log("unload view's searchBar ");
			Ext.Msg.alert('Error', '界面出错，没有加载到界面控件.');
			return;
		}

		this.formWin=Ext.create('manageApp.forum.view.bill.BillFormWin',{
			title:'新增',
			isSave:true,
			sysType:searchBar.getForm().findField('sysType').getValue(),
			parentView:view
		});
	},
	editClick:function(obj, event, eOpts){
		console.log('editClick');
		var me=this;
		var view=obj.up(this.alias4Views);
		var grid=view.down(this.alias4Grid);
		if(grid==null || grid==undefined || grid=='undefined'){
			console.log("unload view's grid ");
			Ext.Msg.alert('Error', '界面出错，没有加载到界面控件.');
			return;
		}

		var selectedRows=grid.getSelectionModel().getSelection();
		if(selectedRows.length==1){
			console.log(selectedRows[0]);
			this.formWin=Ext.create('manageApp.forum.view.bill.BillFormWin',{
				title:'修改',
				isSave:false,
				record:selectedRows[0],
				parentView:view
			});
		}
		else{
			Ext.Msg.alert('Status', '只能选择一条记录进行修改操作!');
		}
	},
	delClick:function(obj, event, eOpts){
		console.log('delClick');
		var view=obj.up(this.alias4Views);
		var grid=view.down(this.alias4Grid);
		var store=view.mainStore;
		if(grid==null || grid==undefined || grid=='undefined'){
			console.log("unload view's grid ");
			Ext.Msg.alert('Error', '界面出错，没有加载到界面控件.');
			return;
		}

		var me=this;
		var selectedRows=grid.getSelectionModel().getSelection();
		if(selectedRows.length<1){
			Ext.Msg.alert('Status', '必需选取一条记录进行删除操作!');
		}
		else{
			Ext.Msg.confirm('确认', '是否需要删除当前记录', function(param){
				if(param=='yes'||param=='Yes'){
					//****************单条记录删除方式,但不更新store中的内容*********
					//selectedRows[0].destroy();
					//************************************************************

					//********借助store接口进行更新*******************************
					if(store!=null || store!='undefined'){
						store.remove(selectedRows);
						me.syncGridDate({
							success:function(batch, eOpts){
								Ext.Msg.alert('Status','保存成功.');
							},
							failure:function(batch, eOpts){
								Ext.Msg.alert('Status', '保存失败.');
							},
							scope:me.gridStore
						},store);
					}
					//**********************************************************
				}
			});
		}
	},
	/**
	 * 查询点击事件
	 */
	findClick:function(obj, event, eOpts ){
		console.log("findClick");
		var searchBar=obj.up(this.alias4Views+this.alias4SearchBar);
		var view=obj.up(this.alias4Views);
		var store=view.mainStore;
		this.loadGridDate(searchBar,store);
	},
	/**
	 * 查询栏重置
	 */
	resetClick:function(obj, event, eOpts ){
		var searchBar=obj.up(this.alias4Views+this.alias4SearchBar);
		if(searchBar!=null || searchBar!='undefined' || searchBar==undefined ){
			searchBar.getForm().reset();
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
		if(gridStore!=null || gridStore!='undefined'){
			var scope=null;
			if(typeof(options)!='undefined'){
				scope=options.scope;
			}
			gridStore.sync({
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
				scope:gridStore
			});
		}
	},
	winFromSave:function(){
		if (!this.formWin.formPanel.getForm().isValid()) {
			return ;
		}
		var newRecord=this.formWin.formPanel.getForm().getValues();
		var model = Ext.create('manageApp.forum.model.Bill',newRecord);
		model.save({
			callback:function(records,operation,success){
				if(success){
					var view=this.formWin.parentView;
					var searchBar=view.down(this.alias4SearchBar);
					var store=view.mainStore;

					this.formWin.close();
					this.formWin.parentView=null;
					this.formWin=null;

					this.loadGridDate(searchBar,store);
				}
				else{
//					this.formWin.close();
//					this.formWin=null;
					var msg='保存失败';
					if(operation.request.proxy.reader.rawData.message != undefined ||
						operation.request.proxy.reader.rawData.message !='' ){
						msg=operation.request.proxy.reader.rawData.message;
					}
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
		var gridStore=this.formWin.parentView.mainStore;
		this.syncGridDate({
			success:function(batch, eOpts){
				Ext.Msg.alert('Status','保存成功.');
				var me=this;
				me.parentView=null;
				me.close();
			},
			failure:function(batch, eOpts){
				var msg='保存失败';
				if(batch.proxy.reader.rawData.message != undefined ||
					batch.proxy.reader.rawData.message !='' ){
					msg=batch.proxy.reader.rawData.message;
				}
				Ext.Msg.alert('Status', msg);
			},
			scope:this.formWin
		},gridStore);

	}
});