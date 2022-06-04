package DongYu.WebBase.System.Service;


import DongYu.WebBase.System.Entity.RoleUserRelation;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Mapping.RowBounds;

import java.util.List;

public interface RoleUserRelationService {

    public WebMessage insertRelation(String data, Long roleId);

    public WebMessage deleteRelation(String data, Long roleId);

    public WebMessage delete(User[] record, Long roleId);

    /**
     * 保存一个RoleUserRelation实体
     */
    public WebMessage insert(RoleUserRelation record);
    /**
     * 修改一个RoleUserRelation实体
     */
    public WebMessage update(RoleUserRelation record);
    /**
     * 按主键删除一个RoleUserRelation实体
     */
    public WebMessage deleteById(RoleUserRelation record);
    /**
     * 按主键查询一个RoleUserRelation实体
     */
    public RoleUserRelation findOne(RoleUserRelation record);
    /**
     * 按条件查询记录总数
     */
    public Long getCount(RoleUserRelation record);
    /**
     * 按条件分页查询记录
     */
    public List<RoleUserRelation> findPage(RoleUserRelation record,
                                          RowBounds rowBounds,
                                           String orderBy);

    /**
     *根据roleIds查询记录
     */
    public List<RoleUserRelation> findAllByRoleId(List<Long> roleIds);

}
