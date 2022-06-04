package DongYu.WebBase.System.Mapping;


import DongYu.WebBase.System.Entity.RoleUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author Peter Chen
 * @version 1.0
 * @since 1.0
 * */
public interface RoleUserRelationMapper {
    /**
     * 保存一个RoleUserRelation实体 
     */
    public void insert(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 修改一个RoleUserRelation实体 
     */
    public void update(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 按主键删除一个RoleUserRelation实体 
     */
    public void deleteById(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 按roleID ,userId删除一个RoleUserRelation实体
     */
    public void deleteByRoleIdAndUserId(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 按主键查询一个RoleUserRelation实体 
     */
    public DongYu.WebBase.System.Entity.RoleUserRelation findOne(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 按条件查询记录总数
     */
    public Long getCount(DongYu.WebBase.System.Entity.RoleUserRelation record);
    /**
     * 按条件分页查询记录
     */
    public List<DongYu.WebBase.System.Entity.RoleUserRelation> findPage(@Param("record") DongYu.WebBase.System.Entity.RoleUserRelation record,
                                                                      @Param("rowBounds") DongYu.WebBase.System.Mapping.RowBounds rowBounds,
                                                                      @Param("orderBy") String orderBy);

    public List<RoleUserRelation> findAllByRoleIds(@Param("list") List<Long> roleId);


}
