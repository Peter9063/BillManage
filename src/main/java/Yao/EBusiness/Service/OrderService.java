package Yao.EBusiness.Service;


import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Service.Exception.ServiceException;
import Yao.EBusiness.Entity.Orders;

import java.io.InputStream;
import java.util.List;

public interface OrderService {

    public WebMessage<Orders> save(Orders[] records);

    public WebMessage<Orders> delete(Orders[] records);

    public WebMessage<Orders> update(Orders[] records);

    public Long getCount(Orders record);

    public WebMessage findPage(Orders record, Integer start, Integer limit, Sorte[] sorts);

    public WebMessage inputOrders(InputStream excelFilein) throws ServiceException;

    public WebMessage importOrdersTracking(InputStream excelFilein) throws ServiceException;

    public WebMessage mergeOrder();

    public List<Orders> waitSendExport(Orders record);
}
