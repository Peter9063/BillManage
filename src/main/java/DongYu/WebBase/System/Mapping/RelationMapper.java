package DongYu.WebBase.System.Mapping;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationMapper {

    /**
     * 保存一个Relation实体 
     */
    public void insert(DongYu.WebBase.System.Entity.Relation record);
    /**
     * 修改一个Relation实体 
     */
    public void update(DongYu.WebBase.System.Entity.Relation record);
    /**
     * 按主键删除一个Relation实体 
     */
    public void deleteById(DongYu.WebBase.System.Entity.Relation record);
    /**
     * 按menuId,roleId删除一个Relation实体
     */
    public void deleteByMenuIdAndRoleId(DongYu.WebBase.System.Entity.Relation record);
    public void deleteByMenuIdAndRoleId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
    /**
     * 按主键查询一个Relation实体
     */
    public DongYu.WebBase.System.Entity.Relation findOne(DongYu.WebBase.System.Entity.Relation record);
    /**
     * 按条件查询记录总数
     */
    public Long getCount(DongYu.WebBase.System.Entity.Relation record);
    /**
     * 按条件分页查询记录
     */
    public List<DongYu.WebBase.System.Entity.Relation> findPage(@Param("record") DongYu.WebBase.System.Entity.Relation record,
                                                              @Param("rowBounds") DongYu.WebBase.System.Mapping.RowBounds rowBounds,
                                                              @Param("orderBy") String orderBy);

    /**
     *按roleId 查询所有 角色资源记录
     */
    public  List<DongYu.WebBase.System.Entity.Relation> findAllByRoleId(@Param("roleId") Long roleId);
}
