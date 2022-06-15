Ext.require('manageApp.utils.Model');
Ext.define('manageApp.ebusiness.model.Order',
{
	extend : 'Ext.data.Model',
    idProperty:'id',
    fields: [
        { name: 'id', type: 'int', defaultValue: 0 },
        { name: 'distributor', type: 'string' },//渠道商
        { name: 'siteName', type: 'string' },//平台名称
        { name: 'storeName', type: 'string' },//店铺名称
        { name: 'netName', type: 'string' },//网点名称
        { name: 'orderTid', type: 'string' },//订单号Tid
        { name: 'orderOid', type: 'string' },//子单号Oid
        { name: 'expressName', type: 'string' },//快递公司
        { name: 'trackingNum', type: 'string' },//快递单号
        { name: 'receiverPhone', type: 'string' },//收件人手机号码
        { name: 'receiver', type: 'string' },//收件人姓名
        { name: 'productNotes', type: 'string' },//商品备注(选填)
        { name: 'receiverProvince', type: 'string' },//收件人省份
        { name: 'receiverCity', type: 'string' },//收件人城市
        { name: 'receiverTown', type: 'string' },//收件人区/镇
        { name: 'receiverAddres', type: 'string' },//收件人具体地址
        { name: 'receiverCardId', type: 'string' },//收件人身份证号
        { name: 'receiverCardName', type: 'string' },//收件人身份证姓名
        { name: 'siteSpu', type: 'string' },//平台SPU编码
        { name: 'siteSku', type: 'string' },//平台SKU编码
        { name: 'productName', type: 'string' },//商品名称
        { name: 'code69', type: 'string' },//69码
        { name: 'productId', type: 'string' },//商品ID
        { name: 'productPrice', type: 'double' },//商品单价
        { name: 'productSpecific', type: 'string' },//商品规格
        { name: 'spuShort', type: 'string' },//SPU简称
        { name: 'skuShort', type: 'string' },//SKU简称
        { name: 'sellerSpu', type: 'string' },//商家SPU编码
        { name: 'sellerSku', type: 'string' },//商家SKU编码
        { name: 'sellerNumber', type: 'double' },//商品数量
        { name: 'sendStatus', type: 'string' },//商品发货状态
        { name: 'salesReturnStatus', type: 'string' },//退款状态
        { name: 'salesReturnAmount', type: 'double' },//退款金额
        { name: 'buyerComment', type: 'string' },//买家留言
        { name: 'productComment', type: 'string' },//商品留言
        { name: 'sellerComment', type: 'string' },//卖家备注
        { name: 'sendDate', type: 'date' },//发货时间
        { name: 'receiverFullAddres', type: 'string' },//收件人完整地址
        { name: 'supplier', type: 'string' },//供应商名称
        { name: 'isFowardZan', type: 'boolean' },//是否有赞分销
        { name: 'payId', type: 'string' },//支付流水编号
        { name: 'outOrderId', type: 'string' },//外部订单号
        { name: 'orderTime', type: 'date' },//下单时间
        { name: 'payTime', type: 'date' },//支付时间
        { name: 'finishTime', type: 'date' },//完成时间
        { name: 'postage', type: 'double' },//邮费
        { name: 'discount', type: 'double' },//优惠金额
        { name: 'payAmount', type: 'double' },//订单支付金额
        { name: 'productAmount', type: 'double' },//商品支付金额
        { name: 'orderStatus', type: 'string' },//订单状态
        { name: 'orderType', type: 'string' },//订单类型
        { name: 'buynick', type: 'string' },//买家昵称
        { name: 'buyphone', type: 'string' },//买家电话
        { name: 'invoice', type: 'string' },//发票信息
        { name: 'delivery', type: 'string' },//配送方式
        { name: 'createTime', type: 'date'},//创建时间
        { name: 'createTimeBegin', type: 'date'},//
        { name: 'createTimeEnd', type: 'date'},//
        { name: 'createUser', type: 'string' },//创建人
        { name: 'modifyTime', type: 'date'},//修改时间
        { name: 'modifyTimeBegin', type: 'date'},//
        { name: 'modifyTimeEnd', type: 'date'},//
        { name: 'modifyUser',  type: 'string' }//修改人
    ],
//    validations : [{
//        type : 'presence',
//        field : 'firstName'
//    } ],
    /**
     * Proxy无法去除，model.save需要用到该代理
     */
    proxy :
    {
        type : 'ajax',
        reader :
        {
    	    type : 'json',
        	root:'data',
            totalProperty:'total'
        },
        // api :
        // {
        //     read : '../ebusiness/order/findPage.do',
        //     create : '../ebusiness/order/create.do',
        //     update : '../ebusiness/order/update.do',
        //     destroy : '../ebusiness/order/delete.do'
        // },
        actionMethods :
        {
            read : 'GET',
            create : 'POST',
            update : 'POST',
            destroy : 'POST'
        },
        writer:{
        	type:'json',
        	allowSingle:false
        }
    }
});
