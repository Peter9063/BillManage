/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2019
 */

package DongYu.WebBase.System.Mapping;


import DongYu.WebBase.System.Entity.Menu;
import DongYu.WebBase.System.Entity.MenuTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author Peter Chen
 * @version 1.0
 * @since 1.0
 * */
public interface MenuMapper {
    /**
     * 保存一个Menu实体
     */
    public void insert(DongYu.WebBase.System.Entity.Menu record);
    /**
     * 修改一个Menu实体
     */
    public void update(DongYu.WebBase.System.Entity.Menu record);
    /**
     * 按主键删除一个Menu实体
     */
    public void deleteById(DongYu.WebBase.System.Entity.Menu record);
    /**
     * 按主键查询一个Menu实体
     */
    public DongYu.WebBase.System.Entity.Menu findOne(DongYu.WebBase.System.Entity.Menu record);
    /**
     * 按条件查询记录总数
     */
    public Long getCount(DongYu.WebBase.System.Entity.Menu record);
    /**
     * 按条件分页查询记录
     */
    public List<Menu> findPage(@Param("record") DongYu.WebBase.System.Entity.Menu record,
                               @Param("rowBounds") DongYu.WebBase.System.Mapping.RowBounds rowBounds,
                               @Param("orderBy") String orderBy);

    /**
     *所有菜单
     */
    public List<Menu> findAllMenu();


    public void insertMenu(Menu menu);

    public List<MenuTree> getMenuTreeList();

    public List<MenuTree> getMenusByRoleIds(List<Long> roles);//
}
