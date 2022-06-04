/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2019
 */

package DongYu.WebBase.System.Mapping;

import DongYu.WebBase.System.Entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *
 * @author Peter Chen
 * @version 1.0
 * @since 1.0
 * */
public interface RoleMapper {
	/**
	 * 保存一个Role实体 
	 */
	public void insert(DongYu.WebBase.System.Entity.Role record);
	/**
	 * 修改一个Role实体 
	 */
	public void update(DongYu.WebBase.System.Entity.Role record);
	/**
	 * 按主键删除一个Role实体 
	 */
	public void deleteById(DongYu.WebBase.System.Entity.Role record);
	/**
	 * 按主键查询一个Role实体 
	 */
	public DongYu.WebBase.System.Entity.Role findOne(DongYu.WebBase.System.Entity.Role record);
	/**
	 * 按条件查询记录总数
	 */
	public Long getCount(DongYu.WebBase.System.Entity.Role record);
	/**
	 * 按条件分页查询记录
	 */
	public List<DongYu.WebBase.System.Entity.Role> findPage(@Param("record") DongYu.WebBase.System.Entity.Role record,
                                                          @Param("rowBounds") DongYu.WebBase.System.Mapping.RowBounds rowBounds,
                                                          @Param("orderBy") String orderBy);
	
}
