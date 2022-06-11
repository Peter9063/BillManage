Ext.define('manageApp.ebusiness.view.order.Order',{
	extend: 'Ext.panel.Panel',
	alias:'widget.ebusinessViewOrder',
	layout: 'border',
	initComponent: function(){

		var me=this;

		if(this.params==null){
			this.params={};
			this.params.sysType=0;
		}
		//独立Store，按业务类型单独创建。
		var storeId="billStore"+this.params.sysType;
		me.mainStore= Ext.data.StoreManager.lookup(storeId);
		if(me.mainStore==null){
			me.mainStore=Ext.create('manageApp.ebusiness.store.Orders',{storeId: storeId,});
		}
		var columns =  [
			{xtype:'rownumberer'},
			{ text: '渠道商',  dataIndex: 'distributor',width:'8%',minWidth:130},
			{ text: '平台名称',  dataIndex: 'siteName' ,width:'10%'},
			{ text: '店铺名称',  dataIndex: 'storeName' ,width:'10%'},
			{ text: '网点名称',  dataIndex: 'netName' ,width:'10%'},
			{ text: '订单号Tid',  dataIndex: 'orderTid' ,width:'10%'},
			{ text: '子单号Oid',  dataIndex: 'orderOid' ,width:'10%'},
			{ text: '快递公司',  dataIndex: 'expressName' ,width:'10%'},
			{ text: '快递单号',  dataIndex: 'trackingNum' ,width:'10%'},
			{ text: '收件人手机号码',  dataIndex: 'receiverPhone' ,width:'10%'},
			{ text: '收件人姓名',  dataIndex: 'receiver' ,width:'10%'},
			{ text: '商品备注(选填)',  dataIndex: 'productNotes' ,width:'10%'},
			{ text: '收件人省份',  dataIndex: 'receiverProvince' ,width:'10%'},
			{ text: '收件人城市',  dataIndex: 'receiverCity' ,width:'10%'},
			{ text: '收件人区/镇',  dataIndex: 'receiverTown' ,width:'10%'},
			{ text: '收件人具体地址',  dataIndex: 'receiverAddres' ,width:'10%'},
			{ text: '收件人身份证号',  dataIndex: 'receiverCardId' ,width:'10%'},
			{ text: '收件人身份证姓名',  dataIndex: 'receiverCardName' ,width:'10%'},
			{ text: '平台SPU编码',  dataIndex: 'siteSPU' ,width:'10%'},
			{ text: '平台SKU编码',  dataIndex: 'siteSKU' ,width:'10%'},
			{ text: '商品名称',  dataIndex: 'productName' ,width:'10%'},
			{ text: '69码',  dataIndex: 'code69' ,width:'10%'},
			{ text: '商品ID',  dataIndex: 'productID' ,width:'10%'},
			{ text: '商品单价',  dataIndex: 'productPrice' ,width:'10%'},
			{ text: '商品规格',  dataIndex: 'productSpecific' ,width:'10%'},
			{ text: 'SPU简称',  dataIndex: 'spuShort' ,width:'10%'},
			{ text: 'SKU简称',  dataIndex: 'skuShort' ,width:'10%'},
			{ text: '商家SPU编码',  dataIndex: 'sellerSPU' ,width:'10%'},
			{ text: '商家SKU编码',  dataIndex: 'sellerSKU' ,width:'10%'},
			{ text: '商品数量',  dataIndex: 'sellerNumber' ,width:'10%'},
			{ text: '商品发货状态',  dataIndex: 'sendStatus' ,width:'10%'},
			{ text: '退款状态',  dataIndex: 'salesReturnStatus' ,width:'10%'},
			{ text: '退款金额',  dataIndex: 'salesReturnAmount' ,width:'10%'},
			{ text: '买家留言',  dataIndex: 'buyerComment' ,width:'10%'},
			{ text: '商品留言',  dataIndex: 'productComment' ,width:'10%'},
			{ text: '卖家备注',  dataIndex: 'sellerComment' ,width:'10%'},
			{ text: '发货时间',  dataIndex: 'sendDate' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s',width:'10%'},
			{ text: '收件人完整地址',  dataIndex: 'receiverFullAddres' ,width:'10%'},
			{ text: '供应商名称',  dataIndex: 'supplier' ,width:'10%'},
			{ text: '是否有赞分销',  dataIndex: 'isFowardZan' ,width:'10%'},
			{ text: '支付流水编号',  dataIndex: 'payId' ,width:'10%'},
			{ text: '外部订单号',  dataIndex: 'outOrderId' ,width:'10%'},
			{ text: '下单时间',  dataIndex: 'orderTime' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s',width:'10%'},
			{ text: '支付时间',  dataIndex: 'payTime' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s',width:'10%'},
			{ text: '完成时间',  dataIndex: 'finishTime' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s',width:'10%'},
			{ text: '邮费',  dataIndex: 'postage' ,width:'10%'},
			{ text: '优惠金额',  dataIndex: 'discount' ,width:'10%'},
			{ text: '订单支付金额',  dataIndex: 'payAmount' ,width:'10%'},
			{ text: '商品支付金额',  dataIndex: 'productAmount' ,width:'10%'},
			{ text: '订单状态',  dataIndex: 'orderStatus' ,width:'10%'},
			{ text: '订单类型',  dataIndex: 'orderType' ,width:'10%'},
			{ text: '买家昵称',  dataIndex: 'buynick' ,width:'10%'},
			{ text: '买家电话',  dataIndex: 'buyphone' ,width:'10%'},
			{ text: '发票信息',  dataIndex: 'invoice' ,width:'10%'},
			{ text: '配送方式',  dataIndex: 'delivery' ,width:'10%'},
			{ text: '创建时间',  dataIndex: 'createTime' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s' ,width:'15%'},
			{ text: '创建人',  dataIndex: 'createUser' ,width:'10%'},
			{ text: '修改时间',  dataIndex: 'modifyTime' ,xtype: 'datecolumn',   format:'Y-m-d H:i:s',width:'15%' },
			{ text: '修改人',  dataIndex: 'modifyUser' ,width:'10%'}
		];
		var gridPanel=Ext.create('Ext.grid.Panel', {
			region:'center',
			name:'grid',
			store:me.mainStore,
			columns:columns,
			selModel: Ext.create('Ext.selection.CheckboxModel', {mode: 'MULTI'}),//MULTI SINGLE
		    viewConfig: {
		        stripeRows: true,
		        enableTextSelection: true
		    },
		    tbar:[
		    	{
					xtype: 'button',
					name:'butDel',
					text: '删除',
					width: 60
				 },'-',{
						xtype: 'button',
						name:'butInput',
						text: '导入',
						width: 60
				 },'-',{
					xtype: 'button',
					name:'butExport',
					text: '导出',
					width: 60
				},'-',{
					xtype: 'button',
					name:'butAdd',
					text: '添加',
					width: 60
				 },'-',{
					xtype : 'button',
					name:'butEdit',
					text : '修改',
					width : 60
				}
		    ],
		    bbar: Ext.create('Ext.PagingToolbar', {
		    	store:me.mainStore,
	            displayInfo: true
			})
		});

		var searchPanel=Ext.create('Ext.form.Panel',{
			region:'east',
			title:'查询条件',
			name:'searchBar',
			width:260,
			split: true,
			collapsible: true,
			defaultType: 'textfield',
			tbar:[
				{
					xtype: 'button',
					name:'butFind',
					text: '查询',
					width: 60
				 },'-',
	 			{
					xtype : 'button',
					name:'butReset',
					text : '重置',
					width : 60
				}
			],
			items:[
				{
					xtype:'hiddenfield',
					name: 'sysType',
					value:(this.params.sysType==0)?'-1':this.params.sysType
				},
				{
					fieldLabel: '工单号',
					name: 'code',
					padding:'5 5 5 5'
				},
				{
					fieldLabel: '产品型号',
					name: 'productModel',
					padding:'5 5 5 5'
				},
				{
					fieldLabel: '产品编号',
					name: 'productCode',
					padding:'5 5 5 5'
				},
				{
					fieldLabel: '产品名称',
					name: 'productName',
					padding:'5 5 5 5'
				},
				{
					xtype:'combo',
					fieldLabel: '工单状态',
					name: 'state',
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'value',
				    value:'1',
					store:manageApp.utils.Model.getConstantStore('BillState'),
					padding:'5 5 5 5'
				},
				{
					xtype: 'datefield',
					fieldLabel: '创建开始时间',
					name: 'createTimeBegin',
					padding:'5 5 5 5',
					format: 'Y-m-d'
					//value: Ext.Date.add(new Date(), Ext.Date.DAY, -30)
				},
				{
					xtype: 'datefield',
					fieldLabel: '创建结束时间',
					name: 'createTimeEnd',
					padding:'5 5 5 5',
					format: 'Y-m-d'
					//value: Ext.Date.add(new Date(), Ext.Date.DAY, 1)
				}
			]
		});

		this.items=[gridPanel,searchPanel];
		this.callParent(arguments);
	},

});
