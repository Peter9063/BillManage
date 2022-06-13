package Yao.EBusiness.ServiceImp;

import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Service.Exception.ServiceException;
import DongYu.WebBase.System.Utils.ExcelReadUtils;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Mapping.OrdersMapper;
import Yao.EBusiness.Service.OrderService;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public WebMessage<Orders> save(Orders[] records) {
        for (Orders item:records){
            ordersMapper.insert(item);
        }
        return null;
    }

    @Override
    public WebMessage<Orders> delete(Orders[] records) {
        for (Orders item:records){
            ordersMapper.deleteById(item);
        }
        return null;
    }

    @Override
    public WebMessage<Orders> update(Orders[] records) {
        for (Orders item:records){
            ordersMapper.update(item);
        }
        return null;
    }

    @Override
    public Long getCount(Orders record) {
        return ordersMapper.getCount(record);
    }

    @Override
    public WebMessage findPage(Orders record, Integer start, Integer limit, String sort) {
        Long total=ordersMapper.getCount(record);
        List<Orders> list=ordersMapper.findPage(record,new DongYu.WebBase.System.Mapping.RowBounds(start,limit),sort);
        WebMessage msg=new WebMessage();
        msg.setTotal(total);
        msg.setData(list);
        msg.setSuccess(true);
        msg.setMessage("成功");
        return msg;
    }

    public WebMessage inputOrders(InputStream excelFilein) throws ServiceException {
        List<List<String>> sheet= ExcelReadUtils.readExcel(excelFilein);
        if(sheet.size()<1){
            throw new ServiceException("导入的是空文件.");
        }
        Map<Integer, String> mapping = setMapping(sheet.get(0));
        if(mapping==null || mapping.size()<1){
            throw new ServiceException("没有首列字段.");
        }
        List<Orders> ordersList=corverOrders(sheet,mapping);

        for(Orders item : ordersList){
            Orders searchItem=new Orders();
            searchItem.setOrderTid(item.getOrderTid());
            searchItem.setOrderOid(item.getOrderOid());
            searchItem=ordersMapper.findOne(searchItem);
            if(searchItem.getId()==null){
                ordersMapper.insert(item);
            }
            else{
                item.setId(searchItem.getId());
                ordersMapper.update(item);
            }
        }

        WebMessage msg=new WebMessage();
        msg.setSuccess(true);
        msg.setMessage("保存成功...");
        return msg;
    }

    private List<Orders> corverOrders(List<List<String>> sheet, Map<Integer, String> mapping) {
        List<Orders> page=new ArrayList<Orders>();
        for(int i=1;i<sheet.size();i++){
            List<String> row=sheet.get(i);
            Orders order=new Orders();
            for(int j=0;j<row.size();j++){
                try{
                    BeanUtils.setProperty(order,mapping.get(j),row.get(j));
                }
                catch (Exception ex){}
            }
            page.add(order);
        }
        return page;
    }

    /**
     * 设备字段印射
     * @param headRow
     * @return
     */
    private Map<Integer, String> setMapping(List<String> headRow) {
        Map<Integer,String> mapping=new HashMap<Integer,String>();
        List<String> headCol= headRow;
        for(int i=0;i<headCol.size();i++){
            if(headCol.get(i)!=null && headCol.get(i).equals("渠道商")){
                mapping.put(i,"distributor");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("平台名称")){
                mapping.put(i,"siteName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("店铺名称")){
                mapping.put(i,"storeName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("网点名称")){
                mapping.put(i,"netName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("订单号Tid")){
                mapping.put(i,"orderTid");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("子单号Oid")){
                mapping.put(i,"orderOid");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("快递公司")){
                mapping.put(i,"expressName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("快递单号")){
                mapping.put(i,"trackingNum");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人手机号码")){
                mapping.put(i,"receiverPhone");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人姓名")){
                mapping.put(i,"receiver");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品备注(选填)")){
                mapping.put(i,"productNotes");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人省份")){
                mapping.put(i,"receiverProvince");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人城市")){
                mapping.put(i,"receiverCity");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人区/镇")){
                mapping.put(i,"receiverTown");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人具体地址")){
                mapping.put(i,"receiverAddres");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人身份证号")){
                mapping.put(i,"receiverCardId");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人身份证姓名")){
                mapping.put(i,"receiverCardName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("平台SPU编码")){
                mapping.put(i,"siteSpu");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("平台SKU编码")){
                mapping.put(i,"siteSku");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品名称")){
                mapping.put(i,"productName");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("69码")){
                mapping.put(i,"code69");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品ID")){
                mapping.put(i,"productId");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品单价")){
                mapping.put(i,"productPrice");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品规格")){
                mapping.put(i,"productSpecific");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("SPU简称")){
                mapping.put(i,"spuShort");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("SKU简称")){
                mapping.put(i,"skuShort");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商家SPU编码")){
                mapping.put(i,"sellerSpu");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商家SKU编码")){
                mapping.put(i,"sellerSku");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品数量")){
                mapping.put(i,"sellerNumber");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品发货状态")){
                mapping.put(i,"sendStatus");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("退款状态")){
                mapping.put(i,"salesReturnStatus");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("退款金额")){
                mapping.put(i,"salesReturnAmount");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("买家留言")){
                mapping.put(i,"buyerComment");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品留言")){
                mapping.put(i,"productComment");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("卖家备注")){
                mapping.put(i,"sellerComment");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("发货时间")){
                mapping.put(i,"sendDate");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("收件人完整地址")){
                mapping.put(i,"receiverFullAddres");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("供应商名称")){
                mapping.put(i,"supplier");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("是否有赞分销")){
                mapping.put(i,"isFowardZan");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("支付流水编号")){
                mapping.put(i,"payId");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("外部订单号")){
                mapping.put(i,"outOrderId");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("下单时间")){
                mapping.put(i,"orderTime");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("支付时间")){
                mapping.put(i,"payTime");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("完成时间")){
                mapping.put(i,"finishTime");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("邮费")){
                mapping.put(i,"postage");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("优惠金额")){
                mapping.put(i,"discount");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("订单支付金额")){
                mapping.put(i,"payAmount");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("商品支付金额")){
                mapping.put(i,"productAmount");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("订单状态")){
                mapping.put(i,"orderStatus");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("订单类型")){
                mapping.put(i,"orderType");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("买家昵称")){
                mapping.put(i,"buynick");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("买家电话")){
                mapping.put(i,"buyphone");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("发票信息")){
                mapping.put(i,"invoice");
            }
            if(headCol.get(i)!=null && headCol.get(i).equals("配送方式")){
                mapping.put(i,"delivery");
            }
        }
        return mapping;
    }

}
