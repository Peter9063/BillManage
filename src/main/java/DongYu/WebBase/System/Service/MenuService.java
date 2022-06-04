package DongYu.WebBase.System.Service;





import DongYu.WebBase.System.Entity.Menu;
import DongYu.WebBase.System.Entity.MenuTree;
import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;

import java.util.List;

public interface MenuService {

    public WebMessage<Menu> save(Menu[] records, String userName);

    public WebMessage<Menu> delete(Menu[] records);

    public WebMessage<Menu> update(Menu[] records, String userName);

    public List<Menu> select(Menu records, Integer start, Integer limit, Sorte[] sortes);

    public Long getCount(Menu record);

    public WebMessage findPage(Menu record, Integer start, Integer limit, String sort);

    public List<MenuTree> getMenuTreeList();

    public List<MenuTree> getMenusByRoleIds(List<Long> roles);

    public List<MenuTree> getMenuTreeListByRoleIds(List<Long> roles);

    public List<Menu> findAllMenu();

    public List<Menu>  listToTreeList(List<Menu> list);

    public   Menu findChildren(Menu parent, List<Menu> treeList);
}
