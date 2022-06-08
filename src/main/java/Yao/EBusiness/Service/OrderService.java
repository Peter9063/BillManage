package Yao.EBusiness.Service;


import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import Yao.EBusiness.Entity.Orders;

public interface OrderService {

    public WebMessage<Orders> save(Orders[] records);

    public WebMessage<Orders> delete(Orders[] records);

    public WebMessage<Orders> update(Orders[] records);

    public Long getCount(Orders record);

    public WebMessage findPage(Orders record, Integer start, Integer limit, String sort);

}
