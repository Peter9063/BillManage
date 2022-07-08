package Yao.EBusiness.Controller;


import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Service.Exception.ServiceException;
import DongYu.WebBase.System.Utils.ExcelReadUtils;
import DongYu.WebBase.System.Utils.FileUtil;
import Yao.EBusiness.Entity.Orders;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import Yao.EBusiness.Service.OrderService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/ebusiness/order")
public class OrderAction {
    private Logger logger = LoggerFactory.getLogger(OrderAction.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/mergeOrder")
    @ResponseBody
    public WebMessage mergeOrder(){
        return orderService.mergeOrder();
    }

    /**
     * 新增
     * @param records
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody()
    public WebMessage<Orders> create(@RequestBody Orders[] records){
        return orderService.save(records);
    }

    /**
     * 修改
     * @param records
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public WebMessage<Orders> update(@RequestBody Orders[] records){
        return orderService.save(records);
    }

    /**
     * 删除
     * @param records
     * @return
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WebMessage<Orders> delete(@RequestBody Orders[] records){
        return orderService.delete(records);
    }

    /**
     * 查询
     * @param record
     * @param start
     * @param limit
     * @param sort
     * @return
     */
    @RequestMapping(value = "/findPage")
    @ResponseBody
    public WebMessage findPage(Orders record, Integer start, Integer limit, String sort){
        Sorte[] sortes = Sorte.parserSorte(sort);
        return orderService.findPage(record,start,limit,sortes);
    }

    @RequestMapping(value = "/importOrders", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WebMessage importOrders(@RequestParam("filePath") MultipartFile multipartFile,String batchId, HttpServletResponse response) throws ServiceException, IOException {
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ServiceException("文件格式不正确");
        }
        InputStream ordersFile = multipartFile.getInputStream();
        WebMessage webMessage=orderService.inputOrders(ordersFile,batchId);
        return webMessage;
    }

    @RequestMapping(value = "/importOrdersTracking", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WebMessage importOrdersTracking(@RequestParam("filePath") MultipartFile multipartFile, HttpServletResponse response) throws ServiceException, IOException {
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ServiceException("文件格式不正确");
        }
        InputStream ordersFile = multipartFile.getInputStream();
        WebMessage webMessage=orderService.importOrdersTracking(ordersFile);
        return webMessage;
    }

    @RequestMapping(value = "/exportOrders", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<byte[]> exportOrders(Orders record) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        WebMessage message=orderService.exportOrders(record,0,Integer.MAX_VALUE,null);
        List<Orders> searchList= (List<Orders>) message.getData();

        Map<String, List<List<String>>> modelMap = new HashMap<String, List<List<String>>>();
        List<List<String>> excelList = new ArrayList<List<String>>();
        List<String> headList = new ArrayList<String>();
        headList.add("渠道商");
        headList.add("平台名称");
        headList.add("店铺名称");
        headList.add("网点名称");
        headList.add("订单号Tid");
        headList.add("子单号Oid");
        headList.add("快递公司");
        headList.add("快递单号");
        headList.add("收件人手机号码");
        headList.add("收件人姓名");
        headList.add("商品备注(选填)");
        headList.add("收件人省份");
        headList.add("收件人城市");
        headList.add("收件人区/镇");
        headList.add("收件人具体地址");
        headList.add("收件人身份证号");
        headList.add("收件人身份证姓名");
        headList.add("平台SPU编码");
        headList.add("平台SKU编码");
        headList.add("商品名称");
        headList.add("69码");
        headList.add("商品ID");
        headList.add("商品单价");
        headList.add("商品规格");
        headList.add("SPU简称");
        headList.add("SKU简称");
        headList.add("商家SPU编码");
        headList.add("商家SKU编码");
        headList.add("商品数量");
        headList.add("商品发货状态");
        headList.add("退款状态");
        headList.add("退款金额");
        headList.add("买家留言");
        headList.add("商品留言");
        headList.add("卖家备注");
        headList.add("发货时间");
        headList.add("收件人完整地址");
        headList.add("供应商名称");
        headList.add("是否有赞分销");
        headList.add("支付流水编号");
        headList.add("外部订单号");
        headList.add("下单时间");
        headList.add("支付时间");
        headList.add("完成时间");
        headList.add("邮费");
        headList.add("优惠金额");
        headList.add("订单支付金额");
        headList.add("商品支付金额");
        headList.add("订单状态");
        headList.add("订单类型");
        headList.add("买家昵称");
        headList.add("买家电话");
        headList.add("发票信息");
        headList.add("配送方式");
//        headList.add("创建时间");
//        headList.add("创建人");
//        headList.add("修改时间");
//        headList.add("修改人");
        excelList.add(headList);

        for (Orders item : searchList) {
            List<String> bodyList = new ArrayList<String>();
            bodyList.add(item.getDistributor()==null?"":item.getDistributor());
            bodyList.add(item.getSiteName()==null?"":item.getSiteName());
            bodyList.add(item.getStoreName()==null?"":item.getStoreName());
            bodyList.add(item.getNetName()==null?"":item.getNetName());
            bodyList.add(item.getOrderTid()==null?"":item.getOrderTid());
            bodyList.add(item.getOrderOid()==null?"":item.getOrderOid());
            bodyList.add(item.getExpressName()==null?"":item.getExpressName());
            bodyList.add(item.getTrackingNum()==null?"":item.getTrackingNum());
            bodyList.add(item.getReceiverPhone()==null?"":item.getReceiverPhone());
            bodyList.add(item.getReceiver()==null?"":item.getReceiver());
            bodyList.add(item.getProductNotes()==null?"":item.getProductNotes());
            bodyList.add(item.getReceiverProvince()==null?"":item.getReceiverProvince());
            bodyList.add(item.getReceiverCity()==null?"":item.getReceiverCity());
            bodyList.add(item.getReceiverTown()==null?"":item.getReceiverTown());
            bodyList.add(item.getReceiverAddres()==null?"":item.getReceiverAddres());
            bodyList.add(item.getReceiverCardId()==null?"":item.getReceiverCardId());
            bodyList.add(item.getReceiverCardName()==null?"":item.getReceiverCardName());
            bodyList.add(item.getSiteSpu()==null?"":item.getSiteSpu());
            bodyList.add(item.getSiteSku()==null?"":item.getSiteSku());
            bodyList.add(item.getProductName()==null?"":item.getProductName());
            bodyList.add(item.getCode69()==null?"":item.getCode69());
            bodyList.add(item.getProductId()==null?"":item.getProductId());
            bodyList.add(item.getProductPrice()==null?"": String.format("%.4f",item.getProductPrice()));
            bodyList.add(item.getProductSpecific()==null?"":item.getProductSpecific());
            bodyList.add(item.getSpuShort()==null?"":item.getSpuShort());
            bodyList.add(item.getSkuShort()==null?"":item.getSkuShort());
            bodyList.add(item.getSellerSpu()==null?"":item.getSellerSpu());
            bodyList.add(item.getSellerSku()==null?"":item.getSellerSku());
            bodyList.add(item.getSellerNumber()==null?"":String.format("%.4f",item.getSellerNumber()));
            bodyList.add(item.getSendStatus()==null?"":item.getSendStatus());
            bodyList.add(item.getSalesReturnStatus()==null?"":item.getSalesReturnStatus());
            bodyList.add(item.getSalesReturnAmount()==null?"":String.format("%.4f",item.getSalesReturnAmount()));
            bodyList.add(item.getBuyerComment()==null?"":item.getBuyerComment());
            bodyList.add(item.getProductComment()==null?"":item.getProductComment());
            bodyList.add(item.getSellerComment()==null?"":item.getSellerComment());
            bodyList.add(item.getSendDate()==null?"":sdf.format(item.getSendDate()).toString());
            bodyList.add(item.getReceiverFullAddres()==null?"":item.getReceiverFullAddres());
            bodyList.add(item.getSupplier()==null?"":item.getSupplier());
            bodyList.add(item.getIsFowardZan()==null?"":item.getIsFowardZan().toString());
            bodyList.add(item.getPayId()==null?"":item.getPayId());
            bodyList.add(item.getOutOrderId()==null?"":item.getOutOrderId());
            bodyList.add(item.getOrderTime()==null?"":sdf.format(item.getOrderTime()).toString());
            bodyList.add(item.getPayTime()==null?"":sdf.format(item.getPayTime()).toString());
            bodyList.add(item.getFinishTime()==null?"":sdf.format(item.getFinishTime()).toString());
            bodyList.add(item.getPostage()==null?"":String.format("%.4f",item.getPostage()));
            bodyList.add(item.getDiscount()==null?"":String.format("%.4f",item.getDiscount()));
            bodyList.add(item.getPayAmount()==null?"":String.format("%.4f",item.getPayAmount()));
            bodyList.add(item.getProductAmount()==null?"":String.format("%.4f",item.getProductAmount()));
            bodyList.add(item.getOrderStatus()==null?"":item.getOrderStatus());
            bodyList.add(item.getOrderType()==null?"":item.getOrderType());
            bodyList.add(item.getBuynick()==null?"":item.getBuynick());
            bodyList.add(item.getBuyphone()==null?"":item.getBuyphone());
            bodyList.add(item.getInvoice()==null?"":item.getInvoice());
            bodyList.add(item.getDelivery()==null?"":item.getDelivery());
            excelList.add(bodyList);
        }
        modelMap.put("订单数据", excelList);
        byte[] content = ExcelReadUtils.createExcel(modelMap);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String subFileName = dateformat.format(new Date());
        String fileName = "" + subFileName + ".xls";
        return FileUtil.outputFile(content, MediaType.APPLICATION_OCTET_STREAM, fileName);
    }

    /**
     * 待发送订单导出
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/waitSendExport", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<byte[]> waitSendExport(Orders record) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<Orders> searchList= orderService.waitSendExport(record);

        Map<String, List<List<String>>> modelMap = new HashMap<String, List<List<String>>>();
        List<List<String>> excelList = new ArrayList<List<String>>();
        List<String> headList = new ArrayList<String>();
        headList.add("收件人姓名");
        headList.add("收件人手机号码");
        headList.add("收件人省份");
        headList.add("收件人城市");
        headList.add("收件人区/镇");
        headList.add("收件人具体地址");
        headList.add("商品规格/数量");
//        headList.add("商品数量");
        headList.add("商品备注(选填)");
        headList.add("卖家备注");
        headList.add("买家留言");
        headList.add("虚拟单号");
        headList.add("快递公司");
        headList.add("快递单号");
        excelList.add(headList);

        for (Orders item : searchList) {
            List<String> bodyList = new ArrayList<String>();
            bodyList.add(item.getReceiver()==null?"":item.getReceiver());
            bodyList.add(item.getReceiverPhone()==null?"":item.getReceiverPhone());
            bodyList.add(item.getReceiverProvince()==null?"":item.getReceiverProvince());
            bodyList.add(item.getReceiverCity()==null?"":item.getReceiverCity());
            bodyList.add(item.getReceiverTown()==null?"":item.getReceiverTown());
            bodyList.add(item.getReceiverAddres()==null?"":item.getReceiverAddres());
            bodyList.add(item.getProductSpecific()==null?"":item.getProductSpecific());
//            bodyList.add(item.getSellerNumber()==null?"":String.format("%.4f",item.getSellerNumber()));
            bodyList.add(item.getProductNotes()==null?"":item.getProductNotes());
            bodyList.add(item.getSellerComment()==null?"":item.getSellerComment());
            bodyList.add(item.getBuyerComment()==null?"":item.getBuyerComment());
            bodyList.add(item.getUuid()==null?"":item.getUuid());
            bodyList.add(item.getExpressName()==null?"":item.getExpressName());
            bodyList.add(item.getTrackingNum()==null?"":item.getTrackingNum());
            excelList.add(bodyList);
        }
        modelMap.put("待发订单", excelList);
        byte[] content = ExcelReadUtils.createExcel(modelMap);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String subFileName = dateformat.format(new Date());
        String fileName = "" + subFileName + ".xls";
        return FileUtil.outputFile(content, MediaType.APPLICATION_OCTET_STREAM, fileName);
    }

}


