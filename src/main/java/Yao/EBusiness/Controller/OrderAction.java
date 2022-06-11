package Yao.EBusiness.Controller;


import Yao.EBusiness.Entity.Orders;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import Yao.EBusiness.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/ebusiness/order")
public class OrderAction {

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

}


