package DongYu.WebBase.System.Service;


import DongYu.WebBase.System.Entity.Role;
import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;

import java.util.List;

public interface RoleService {

    public WebMessage<Role> save(Role[] records, String userName);

    public WebMessage<Role> delete(Role[] records);

    public WebMessage<Role> update(Role[] records, String userName);

    public List<Role> select(Role records, Integer start, Integer limit, Sorte[] sortes);

    public Long getCount(Role record);

    public WebMessage findPage(Role record, Integer start, Integer limit, String sort);

    public List<Role> findPage();
}
