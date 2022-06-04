/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2017
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
public interface UserMapper {
	/**
	 * 保存一个User实体 
	 */
	public void insert(DongYu.WebBase.System.Entity.User record);
	/**
	 * 修改一个User实体 
	 */
	public void update(DongYu.WebBase.System.Entity.User record);
	/**
	 * 按主键删除一个User实体 
	 */
	public void deleteById(DongYu.WebBase.System.Entity.User record);
	/**
	 * 按主键查询一个User实体 
	 */
	public DongYu.WebBase.System.Entity.User findOne(DongYu.WebBase.System.Entity.User record);
	/**
	 * 按条件查询记录总数
	 */
	public Long getCount(DongYu.WebBase.System.Entity.User record);
	/**
	 * 按条件分页查询记录
	 */
	public List<DongYu.WebBase.System.Entity.User> findPage(@Param("record") DongYu.WebBase.System.Entity.User record,
                                                          @Param("rowBounds") DongYu.WebBase.System.Mapping.RowBounds rowBounds,
                                                          @Param("orderBy") String orderBy);

	public List<DongYu.WebBase.System.Entity.User> findAll();

	public List<DongYu.WebBase.System.Entity.User> findAllByRoleId(@Param("record") User record, @Param("list") List<Long> roleIds);
}
