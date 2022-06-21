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
    public WebMessage importOrders(@RequestParam("filePath") MultipartFile multipartFile, HttpServletResponse response) throws ServiceException, IOException {
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ServiceException("文件格式不正确");
        }
        InputStream ordersFile = multipartFile.getInputStream();
        WebMessage webMessage=orderService.inputOrders(ordersFile);
        return webMessage;
    }

    @RequestMapping(value = "/exportOrders", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<byte[]> exportOrders(Orders record) throws Exception {
        WebMessage message=orderService.findPage(record,0,Integer.MAX_VALUE,null);
        List<Orders> searchList= (List<Orders>) message.getData();

        Map<String, List<List<String>>> modelMap = new HashMap<String, List<List<String>>>();
        List<List<String>> excelList = new ArrayList<List<String>>();
        List<String> headList = new ArrayList<String>();

        headList.add("设备地址");
        headList.add("imei");
        headList.add("imsi");
        headList.add("itf25");
        headList.add("传感器编号");
        headList.add("水司站点编号");
        headList.add("注销原因");
        headList.add("状态");
        headList.add("工单号");
        headList.add("产品型号");
        headList.add("产品编号");
        headList.add("'产品名称");
        headList.add("创建时间");
        headList.add("创建人");
        headList.add("修改时间");
        headList.add("修改人");
        excelList.add(headList);

//        for (Orders item : searchList) {
//            List<String> bodyList = new ArrayList<String>();
//            bodyList.add(item.getRadioAddress()==null?"":item.getRadioAddress().toString());
//            bodyList.add(item.getImei()==null?"":item.getImei().toString());
//            bodyList.add(item.getImsi()==null?"":item.getImsi().toString());
//            bodyList.add(item.getItf25()==null?"":item.getItf25().toString());
//            bodyList.add(item.getSensorNumber()==null?"":item.getSensorNumber().toString());
//            bodyList.add(item.getStation()==null?"":item.getStation().toString());
//            bodyList.add(item.getReason()==null?"":item.getReason().toString());
//            if(item.getState()==null){
//                bodyList.add("");
//            }
//            else{
//                bodyList.add(AppConstant.Constants.get(AppConstant.EquipmentState).get(item.getState()));
//            }
//            bodyList.add(item.getBillCode()==null?"":item.getBillCode().toString());
//            bodyList.add(item.getProductModel()==null?"":item.getProductModel().toString());
//            bodyList.add(item.getProductCode()==null?"":item.getProductCode().toString());
//            bodyList.add(item.getProductName()==null?"":item.getProductName().toString());
//            bodyList.add(item.getCreateTime()==null?"":item.getCreateTime().toString());
//            bodyList.add(item.getCreateUser());
//            bodyList.add(item.getModifTime()==null?"":item.getModifTime().toString());
//            bodyList.add(item.getModifUser());
//            excelList.add(bodyList);
//        }
        modelMap.put("订单数据", excelList);
        byte[] content = ExcelReadUtils.createExcel(modelMap);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        String subFileName = dateformat.format(new Date());
        String fileName = "" + subFileName + ".xls";
        return FileUtil.outputFile(content, MediaType.APPLICATION_OCTET_STREAM, fileName);
    }

}


