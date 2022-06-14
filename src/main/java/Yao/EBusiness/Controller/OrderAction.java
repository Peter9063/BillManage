package Yao.EBusiness.Controller;


import DongYu.WebBase.System.Service.Exception.ServiceException;
import Yao.EBusiness.Entity.Orders;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import Yao.EBusiness.Service.OrderService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/delete")
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
        return orderService.findPage(record,start,limit,sort);
    }

    @RequestMapping(value = "/importOrders", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WebMessage importOrders(@RequestParam("filePath") MultipartFile multipartFile, HttpServletResponse response) throws ServiceException, IOException {
        WebMessage webMessage = new WebMessage();

        try {
            String fileName = multipartFile.getOriginalFilename();
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                throw new ServiceException("文件格式不正确");
            }
            InputStream ordersFile = multipartFile.getInputStream();
            webMessage=orderService.inputOrders(ordersFile);

        }catch (ServiceException e){
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
        catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            webMessage.setSuccess(false);
            webMessage.setMessage("系统出错，联系管理员");
        }
        return webMessage;
    }

}


