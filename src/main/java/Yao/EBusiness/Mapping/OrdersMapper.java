/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2022
 */

package Yao.EBusiness.Mapping;

import java.util.List;

import DongYu.WebBase.System.Mapping.RowBounds;
import org.apache.ibatis.annotations.Param;
import Yao.EBusiness.Entity.*;
import java.util.Date;


/**
 *
 * @author Peter Chen
 * @version 1.0
 * @since 1.0
 * */
public interface OrdersMapper {
	/**
	 * 保存一个Orders实体
	 */
	public void insert(Yao.EBusiness.Entity.Orders record);
	/**
	 * 修改一个Orders实体
	 */
	public void update(Yao.EBusiness.Entity.Orders record);
	/**
	 * 按主键删除一个Orders实体
	 */
	public void deleteById(Yao.EBusiness.Entity.Orders record);
	/**
	 * 按主键查询一个Orders实体
	 */
	public Yao.EBusiness.Entity.Orders findOne(@Param("record")Yao.EBusiness.Entity.Orders record);
	/**
	 * 按条件查询记录总数
	 */
	public Long getCount(@Param("record")Yao.EBusiness.Entity.Orders record);
	/**
	 * 按条件分页查询记录
	 */
	public List<Yao.EBusiness.Entity.Orders> findPage(@Param("record")Yao.EBusiness.Entity.Orders record,
								@Param("rowBounds")DongYu.WebBase.System.Mapping.RowBounds rowBounds,
								@Param("orderBy")String orderBy);

	public List<Yao.EBusiness.Entity.Orders>  waitSendExport(@Param("record")Yao.EBusiness.Entity.Orders record,
															 @Param("rowBounds")DongYu.WebBase.System.Mapping.RowBounds rowBounds,
															 @Param("orderBy")String orderBy);
}
