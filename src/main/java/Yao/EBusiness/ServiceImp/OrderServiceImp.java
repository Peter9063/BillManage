package Yao.EBusiness.ServiceImp;

import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Mapping.RowBounds;
import Yao.EBusiness.Entity.Orders;
import Yao.EBusiness.Mapping.OrdersMapper;
import Yao.EBusiness.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
