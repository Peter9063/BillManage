package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/base/user")
public class UserAction {
	private Logger logger = LoggerFactory.getLogger(UserAction.class);

	@Autowired
	private UserService service;

	/**
	 * 根据roleid 查找 所有 相关联user
	 * @param roleId
	 * @param type  1:左边列表  2 ： 右边列表
	 * @return
	 */
	@RequestMapping(value = "findAllByRoleIds")
	@ResponseBody
	public List<User> findAllByRoleIds(User record,Long roleId , Integer type){



		return  service.findAllByRoleIds(record,roleId,type);
	}
	/**
	 * 分类查询
	 * @param record 查询条件
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/findPage", method = RequestMethod.GET )
	@ResponseBody
	public WebMessage findPage(User record, Integer start, Integer limit, String sort){
		
		Sorte[] sortes = Sorte.parserSorte(sort);
		List<User> list=service.findPage(record,start,limit,sortes);
		Long total=service.getCount(record);
		
		WebMessage<List<User>> msg=new WebMessage<List<User>>();
		msg.setMessage("ok");
		msg.setSuccess(true);
		msg.setTotal(total);
		msg.setData(list);
		
		return msg;
	}
	
	/**
	 * 删除单条分类
	 * @param records
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/delete", method ={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public WebMessage delete(@RequestBody User[] records){
		return service.delete(records);
	}
		
	/**
	 * 新增单条分类
	 * @param records
	 * @return
	 */
	@RequiresPermissions(value = {"menu_update"})
	@RequestMapping(value="/save", method ={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public WebMessage save(@RequestBody User[] records){
		return service.save(records);
	}

//	/**
//	 * 修改密码
//	 * @param data
//	 * @return
//	 */
//	@RequestMapping(value="/edit", method ={RequestMethod.GET,RequestMethod.POST})
//	@ResponseBody
//	public WebMessage edit(String data){
//
//		JSONObject jsonObject=JSONObject.fromObject(data);
//		User user=(User) JSONObject.toBean(jsonObject,User.class);
//
//		return service.ediitPassword(user);
//	}


}
