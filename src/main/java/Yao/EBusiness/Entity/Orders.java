/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2022
 */

package Yao.EBusiness.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import DongYu.WebBase.System.Entity.*;
import java.util.Date;

public class Orders {

    /**
     *
     */
	private Long id;
    /**
     * 渠道商
     */
	private String distributor;
    /**
     * 平台名称
     */
	private String siteName;
    /**
     * 店铺名称
     */
	private String storeName;
    /**
     * 网点名称
     */
	private String netName;
    /**
     * 订单号Tid
     */
	private String orderTid;
    /**
     * 子单号Oid
     */
	private String orderOid;
    /**
     * 快递公司
     */
	private String expressName;
    /**
     * 快递单号
     */
	private String trackingNum;
    /**
     * 收件人手机号码
     */
	private String receiverPhone;
    /**
     * 收件人姓名
     */
	private String receiver;
    /**
     * 商品备注(选填)
     */
	private String productNotes;
    /**
     * 收件人省份
     */
	private String receiverProvince;
    /**
     * 收件人城市
     */
	private String receiverCity;
    /**
     * 收件人区/镇
     */
	private String receiverTown;
    /**
     * 收件人具体地址
     */
	private String receiverAddres;
    /**
     * 收件人身份证号
     */
	private String receiverCardId;
    /**
     * 收件人身份证姓名
     */
	private String receiverCardName;
    /**
     * 平台SPU编码
     */
	private String siteSpu;
    /**
     * 平台SKU编码
     */
	private String siteSku;
    /**
     * 商品名称
     */
	private String productName;
    /**
     * 69码
     */
	private String code69;
    /**
     * 商品ID
     */
	private String productId;
    /**
     * 商品单价
     */
	private Double productPrice;
    /**
     * 商品规格
     */
	private String productSpecific;
    /**
     * SPU简称
     */
	private String spuShort;
    /**
     * SKU简称
     */
	private String skuShort;
    /**
     * 商家SPU编码
     */
	private String sellerSpu;
    /**
     * 商家SKU编码
     */
	private String sellerSku;
    /**
     * 商品数量
     */
	private Double sellerNumber;
    /**
     * 商品发货状态
     */
	private String sendStatus;
    /**
     * 退款状态
     */
	private String salesReturnStatus;
    /**
     * 退款金额
     */
	private Double salesReturnAmount;
    /**
     * 买家留言
     */
	private String buyerComment;
    /**
     * 商品留言
     */
	private String productComment;
    /**
     * 卖家备注
     */
	private String sellerComment;
    /**
     * 发货时间
     */
	private Date sendDate;
	private Date sendDateBegin;
	private Date sendDateEnd;
    /**
     * 收件人完整地址
     */
	private String receiverFullAddres;
    /**
     * 供应商名称
     */
	private String supplier;
    /**
     * 是否有赞分销
     */
	private Boolean isFowardZan;
    /**
     * 支付流水编号
     */
	private String payId;
    /**
     * 外部订单号
     */
	private String outOrderId;
    /**
     * 下单时间
     */
	private Date orderTime;
	private Date orderTimeBegin;
	private Date orderTimeEnd;
    /**
     * 支付时间
     */
	private Date payTime;
	private Date payTimeBegin;
	private Date payTimeEnd;
    /**
     * 完成时间
     */
	private Date finishTime;
	private Date finishTimeBegin;
	private Date finishTimeEnd;
    /**
     * 邮费
     */
	private Double postage;
    /**
     * 优惠金额
     */
	private Double discount;
    /**
     * 订单支付金额
     */
	private Double payAmount;
    /**
     * 商品支付金额
     */
	private Double productAmount;
    /**
     * 订单状态
     */
	private String orderStatus;
    /**
     * 订单类型
     */
	private String orderType;
    /**
     * 买家昵称
     */
	private String buynick;
    /**
     * 买家电话
     */
	private String buyphone;
    /**
     * 发票信息
     */
	private String invoice;
    /**
     * 配送方式
     */
	private String delivery;
    /**
     *
     */
	private String createUser;
    /**
     *
     */
	private Date createTime;
	private Date createTimeBegin;
	private Date createTimeEnd;
    /**
     *
     */
	private String modifyUser;
    /**
     *
     */
	private Date modifyTime;
	private Date modifyTimeBegin;
	private Date modifyTimeEnd;


    /**
     * 设置
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 修改
     */
    public Long getId() {
        return this.id;
    }


    /**
     * 设置渠道商
     */
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
    /**
     * 修改渠道商
     */
    public String getDistributor() {
        return this.distributor;
    }


    /**
     * 设置平台名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    /**
     * 修改平台名称
     */
    public String getSiteName() {
        return this.siteName;
    }


    /**
     * 设置店铺名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    /**
     * 修改店铺名称
     */
    public String getStoreName() {
        return this.storeName;
    }


    /**
     * 设置网点名称
     */
    public void setNetName(String netName) {
        this.netName = netName;
    }
    /**
     * 修改网点名称
     */
    public String getNetName() {
        return this.netName;
    }


    /**
     * 设置订单号Tid
     */
    public void setOrderTid(String orderTid) {
        this.orderTid = orderTid;
    }
    /**
     * 修改订单号Tid
     */
    public String getOrderTid() {
        return this.orderTid;
    }


    /**
     * 设置子单号Oid
     */
    public void setOrderOid(String orderOid) {
        this.orderOid = orderOid;
    }
    /**
     * 修改子单号Oid
     */
    public String getOrderOid() {
        return this.orderOid;
    }


    /**
     * 设置快递公司
     */
    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
    /**
     * 修改快递公司
     */
    public String getExpressName() {
        return this.expressName;
    }


    /**
     * 设置快递单号
     */
    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }
    /**
     * 修改快递单号
     */
    public String getTrackingNum() {
        return this.trackingNum;
    }


    /**
     * 设置收件人手机号码
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    /**
     * 修改收件人手机号码
     */
    public String getReceiverPhone() {
        return this.receiverPhone;
    }


    /**
     * 设置收件人姓名
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    /**
     * 修改收件人姓名
     */
    public String getReceiver() {
        return this.receiver;
    }


    /**
     * 设置商品备注(选填)
     */
    public void setProductNotes(String productNotes) {
        this.productNotes = productNotes;
    }
    /**
     * 修改商品备注(选填)
     */
    public String getProductNotes() {
        return this.productNotes;
    }


    /**
     * 设置收件人省份
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }
    /**
     * 修改收件人省份
     */
    public String getReceiverProvince() {
        return this.receiverProvince;
    }


    /**
     * 设置收件人城市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }
    /**
     * 修改收件人城市
     */
    public String getReceiverCity() {
        return this.receiverCity;
    }


    /**
     * 设置收件人区/镇
     */
    public void setReceiverTown(String receiverTown) {
        this.receiverTown = receiverTown;
    }
    /**
     * 修改收件人区/镇
     */
    public String getReceiverTown() {
        return this.receiverTown;
    }


    /**
     * 设置收件人具体地址
     */
    public void setReceiverAddres(String receiverAddres) {
        this.receiverAddres = receiverAddres;
    }
    /**
     * 修改收件人具体地址
     */
    public String getReceiverAddres() {
        return this.receiverAddres;
    }


    /**
     * 设置收件人身份证号
     */
    public void setReceiverCardId(String receiverCardId) {
        this.receiverCardId = receiverCardId;
    }
    /**
     * 修改收件人身份证号
     */
    public String getReceiverCardId() {
        return this.receiverCardId;
    }


    /**
     * 设置收件人身份证姓名
     */
    public void setReceiverCardName(String receiverCardName) {
        this.receiverCardName = receiverCardName;
    }
    /**
     * 修改收件人身份证姓名
     */
    public String getReceiverCardName() {
        return this.receiverCardName;
    }


    /**
     * 设置平台SPU编码
     */
    public void setSiteSpu(String siteSpu) {
        this.siteSpu = siteSpu;
    }
    /**
     * 修改平台SPU编码
     */
    public String getSiteSpu() {
        return this.siteSpu;
    }


    /**
     * 设置平台SKU编码
     */
    public void setSiteSku(String siteSku) {
        this.siteSku = siteSku;
    }
    /**
     * 修改平台SKU编码
     */
    public String getSiteSku() {
        return this.siteSku;
    }


    /**
     * 设置商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * 修改商品名称
     */
    public String getProductName() {
        return this.productName;
    }


    /**
     * 设置69码
     */
    public void setCode69(String code69) {
        this.code69 = code69;
    }
    /**
     * 修改69码
     */
    public String getCode69() {
        return this.code69;
    }


    /**
     * 设置商品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * 修改商品ID
     */
    public String getProductId() {
        return this.productId;
    }


    /**
     * 设置商品单价
     */
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
    /**
     * 修改商品单价
     */
    public Double getProductPrice() {
        return this.productPrice;
    }


    /**
     * 设置商品规格
     */
    public void setProductSpecific(String productSpecific) {
        this.productSpecific = productSpecific;
    }
    /**
     * 修改商品规格
     */
    public String getProductSpecific() {
        return this.productSpecific;
    }


    /**
     * 设置SPU简称
     */
    public void setSpuShort(String spuShort) {
        this.spuShort = spuShort;
    }
    /**
     * 修改SPU简称
     */
    public String getSpuShort() {
        return this.spuShort;
    }


    /**
     * 设置SKU简称
     */
    public void setSkuShort(String skuShort) {
        this.skuShort = skuShort;
    }
    /**
     * 修改SKU简称
     */
    public String getSkuShort() {
        return this.skuShort;
    }


    /**
     * 设置商家SPU编码
     */
    public void setSellerSpu(String sellerSpu) {
        this.sellerSpu = sellerSpu;
    }
    /**
     * 修改商家SPU编码
     */
    public String getSellerSpu() {
        return this.sellerSpu;
    }


    /**
     * 设置商家SKU编码
     */
    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }
    /**
     * 修改商家SKU编码
     */
    public String getSellerSku() {
        return this.sellerSku;
    }


    /**
     * 设置商品数量
     */
    public void setSellerNumber(Double sellerNumber) {
        this.sellerNumber = sellerNumber;
    }
    /**
     * 修改商品数量
     */
    public Double getSellerNumber() {
        return this.sellerNumber;
    }


    /**
     * 设置商品发货状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
    /**
     * 修改商品发货状态
     */
    public String getSendStatus() {
        return this.sendStatus;
    }


    /**
     * 设置退款状态
     */
    public void setSalesReturnStatus(String salesReturnStatus) {
        this.salesReturnStatus = salesReturnStatus;
    }
    /**
     * 修改退款状态
     */
    public String getSalesReturnStatus() {
        return this.salesReturnStatus;
    }


    /**
     * 设置退款金额
     */
    public void setSalesReturnAmount(Double salesReturnAmount) {
        this.salesReturnAmount = salesReturnAmount;
    }
    /**
     * 修改退款金额
     */
    public Double getSalesReturnAmount() {
        return this.salesReturnAmount;
    }


    /**
     * 设置买家留言
     */
    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }
    /**
     * 修改买家留言
     */
    public String getBuyerComment() {
        return this.buyerComment;
    }


    /**
     * 设置商品留言
     */
    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }
    /**
     * 修改商品留言
     */
    public String getProductComment() {
        return this.productComment;
    }


    /**
     * 设置卖家备注
     */
    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }
    /**
     * 修改卖家备注
     */
    public String getSellerComment() {
        return this.sellerComment;
    }



    /**
     * 设置发货时间
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    /**
     * 修改发货时间
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getSendDate() {
        return this.sendDate;
    }
    public void setSendDateBegin(Date value) {
        this.sendDateBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getSendDateBegin() {
        return this.sendDateBegin;
    }

    public void setSendDateEnd(Date value) {
        this.sendDateEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getSendDateEnd() {
        return this.sendDateEnd;
    }


    /**
     * 设置收件人完整地址
     */
    public void setReceiverFullAddres(String receiverFullAddres) {
        this.receiverFullAddres = receiverFullAddres;
    }
    /**
     * 修改收件人完整地址
     */
    public String getReceiverFullAddres() {
        return this.receiverFullAddres;
    }


    /**
     * 设置供应商名称
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    /**
     * 修改供应商名称
     */
    public String getSupplier() {
        return this.supplier;
    }


    /**
     * 设置是否有赞分销
     */
    public void setIsFowardZan(Boolean isFowardZan) {
        this.isFowardZan = isFowardZan;
    }
    /**
     * 修改是否有赞分销
     */
    public Boolean getIsFowardZan() {
        return this.isFowardZan;
    }


    /**
     * 设置支付流水编号
     */
    public void setPayId(String payId) {
        this.payId = payId;
    }
    /**
     * 修改支付流水编号
     */
    public String getPayId() {
        return this.payId;
    }


    /**
     * 设置外部订单号
     */
    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }
    /**
     * 修改外部订单号
     */
    public String getOutOrderId() {
        return this.outOrderId;
    }



    /**
     * 设置下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    /**
     * 修改下单时间
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getOrderTime() {
        return this.orderTime;
    }
    public void setOrderTimeBegin(Date value) {
        this.orderTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getOrderTimeBegin() {
        return this.orderTimeBegin;
    }

    public void setOrderTimeEnd(Date value) {
        this.orderTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getOrderTimeEnd() {
        return this.orderTimeEnd;
    }



    /**
     * 设置支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    /**
     * 修改支付时间
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getPayTime() {
        return this.payTime;
    }
    public void setPayTimeBegin(Date value) {
        this.payTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getPayTimeBegin() {
        return this.payTimeBegin;
    }

    public void setPayTimeEnd(Date value) {
        this.payTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getPayTimeEnd() {
        return this.payTimeEnd;
    }



    /**
     * 设置完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    /**
     * 修改完成时间
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getFinishTime() {
        return this.finishTime;
    }
    public void setFinishTimeBegin(Date value) {
        this.finishTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getFinishTimeBegin() {
        return this.finishTimeBegin;
    }

    public void setFinishTimeEnd(Date value) {
        this.finishTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getFinishTimeEnd() {
        return this.finishTimeEnd;
    }


    /**
     * 设置邮费
     */
    public void setPostage(Double postage) {
        this.postage = postage;
    }
    /**
     * 修改邮费
     */
    public Double getPostage() {
        return this.postage;
    }


    /**
     * 设置优惠金额
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    /**
     * 修改优惠金额
     */
    public Double getDiscount() {
        return this.discount;
    }


    /**
     * 设置订单支付金额
     */
    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
    /**
     * 修改订单支付金额
     */
    public Double getPayAmount() {
        return this.payAmount;
    }


    /**
     * 设置商品支付金额
     */
    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }
    /**
     * 修改商品支付金额
     */
    public Double getProductAmount() {
        return this.productAmount;
    }


    /**
     * 设置订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    /**
     * 修改订单状态
     */
    public String getOrderStatus() {
        return this.orderStatus;
    }


    /**
     * 设置订单类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    /**
     * 修改订单类型
     */
    public String getOrderType() {
        return this.orderType;
    }


    /**
     * 设置买家昵称
     */
    public void setBuynick(String buynick) {
        this.buynick = buynick;
    }
    /**
     * 修改买家昵称
     */
    public String getBuynick() {
        return this.buynick;
    }


    /**
     * 设置买家电话
     */
    public void setBuyphone(String buyphone) {
        this.buyphone = buyphone;
    }
    /**
     * 修改买家电话
     */
    public String getBuyphone() {
        return this.buyphone;
    }


    /**
     * 设置发票信息
     */
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
    /**
     * 修改发票信息
     */
    public String getInvoice() {
        return this.invoice;
    }


    /**
     * 设置配送方式
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    /**
     * 修改配送方式
     */
    public String getDelivery() {
        return this.delivery;
    }


    /**
     * 设置
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * 修改
     */
    public String getCreateUser() {
        return this.createUser;
    }



    /**
     * 设置
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 修改
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTimeBegin(Date value) {
        this.createTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    public void setCreateTimeEnd(Date value) {
        this.createTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getCreateTimeEnd() {
        return this.createTimeEnd;
    }


    /**
     * 设置
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    /**
     * 修改
     */
    public String getModifyUser() {
        return this.modifyUser;
    }



    /**
     * 设置
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    /**
     * 修改
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTimeBegin(Date value) {
        this.modifyTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getModifyTimeBegin() {
        return this.modifyTimeBegin;
    }

    public void setModifyTimeEnd(Date value) {
        this.modifyTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getModifyTimeEnd() {
        return this.modifyTimeEnd;
    }


}
