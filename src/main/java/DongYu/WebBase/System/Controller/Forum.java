package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.MenuTree;
import DongYu.WebBase.System.Entity.RoleUserRelation;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Mapping.UserMapper;
import DongYu.WebBase.System.Service.MenuService;
import DongYu.WebBase.System.Service.RoleUserRelationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping(value="/manage")
public class Forum {
	Logger logger = LoggerFactory.getLogger(Forum.class);

	@Autowired
	private UserMapper mapping;
	@Autowired
	private RoleUserRelationService roleUserRelationService;
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/test")
	@ResponseBody
	public User test(){
		User user = new User();
		user.setUserName("用户");
		return user;
	}
	
	@RequestMapping(value="/login", method ={RequestMethod.POST})
	public ModelAndView login(User record, HttpSession httpSession){
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(record.getUserName(), record.getPassWord());

			Subject subject = SecurityUtils.getSubject();

			subject.login(token);
		}catch (Exception e) {
			logger.error("Login Error:",e);
//			response.setStatus(ResponseVO.failCode);
			Throwable ex = e.getCause();
//			if(ex instanceof BugException) {
//				if(ex.getMessage() != null) {
//					response.setMessage(ex.getMessage());
//				}
//			}else if(e instanceof IncorrectCredentialsException) {
//				response.setMessage("密码错误");
//			}else {
//				response.setMessage("登录失败");
//			}
		}
		if(!record.getUserName().equals("") &&
		   !record.getPassWord().equals("")){
			User user=mapping.findOne(record);
			if(user!=null){
				httpSession.setAttribute("user", user);  
				return new ModelAndView("redirect:/manage/index.jsp ");
			}
		}
		Map<String, String> model = new HashMap<String, String>();

		model.put("error", "用户名密码错误");
		   return  new ModelAndView("redirect:/manage/login.jsp",model);
	}
	
	@RequestMapping(value="/logout", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView logout(HttpSession httpSession){
		httpSession.setAttribute("user", null);  
		return  new ModelAndView("redirect:/manage/login.jsp");
	}
	
	@RequestMapping(value="/menu", method ={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List menu(HttpSession httpSession) throws UnsupportedEncodingException {
		User user=null;
		if(httpSession.getAttribute("user")!=null){
			user=(User) httpSession.getAttribute("user");
		}

		if(user!=null){
//			System.err.println(menuService.getMenuTreeList().toString());
//			return JSONArray.fromObject(menuService.getMenuTreeList()).toString();
//			return menuService.getMenuTreeList();

			Integer roleId=user.getRole();
			List<Long> list= new ArrayList<>();
			RoleUserRelation roleUserRelation=new RoleUserRelation();
			roleUserRelation.setUserId(user.getId().longValue());
			List<RoleUserRelation> list1= roleUserRelationService.findPage(roleUserRelation,null,null);

				for(RoleUserRelation roleUserRelation1:list1){
				list.add(roleUserRelation1.getRoleId());
			}
//			list.add(roleId.longValue());
			MenuTree menuTree=new MenuTree();
			return menuTree.ToMenuTree(menuService.getMenusByRoleIds(list));

//			if(user.getRole().equals(0)){
//				return"[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','checked':true,'leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=1'},"+
//		"		{'id':12,'parentId':1,'leaf' : true,'text':'拆解单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=5'},"+
//		"		{'id':13,'parentId':1,'leaf' : true,'text':'预处理单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=6'},"+
//		"		{'id':14,'parentId':1,'leaf' : true,'text':'水道工单' ,'url':'manageApp.forum.controller.bill.Bill?sysType=2'},"+
//		"		{'id':15,'parentId':1,'leaf' : true,'text':'燃气表工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=3'},"+
//		"		{'id':16,'parentId':1,'leaf' : true,'text':'电磁水表工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=4'},"+
//		"			{'id':17,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
//		"			{'id':18,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'},"+
//		"			{'id':19,'parentId':1,'leaf' : true,'text':'清理' ,'url':'manageApp.forum.controller.clear.Clear'}"+
//
//		"			,{'id':20,'parentId':1,'leaf' : true,'text':'菜单' ,'url':'manageApp.base.controller.menu.Menu?sysType=1'},"+
//		"			{'id':26,'parentId':1,'leaf' : true,'text':'角色' ,'url':'manageApp.base.controller.role.Role?sysType=2'}"+
//
//		"]"+
//	"},"+
//	"{"+
//	"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,'checked':'true',"+
//	"	'children':["+
//	"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
//	"	]"+
//	"},"+
//"]";
//			}
//
//			if(user.getRole().equals(1)){
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=1'},"+
//		"		{'id':12,'parentId':1,'leaf' : true,'text':'拆解单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=5'},"+
//		"		{'id':13,'parentId':1,'leaf' : true,'text':'预处理单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=6'},"+
//		"		{'id':14,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
//		"		{'id':15,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
////	",{"+
////	"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
////	"	'children':["+
////	"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
////	"	]"+
////	"},"+
//"]";
//			}
//
//			if(user.getRole().equals(2)){
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
////		"		{'id':11,'parentId':1,'leaf' : true,'text':'工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=1'},"+
////		"			{'id':12,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'}"+
//		"			{'id':13,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
////	",{"+
////	"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
////	"	'children':["+
////	"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
////	"	]"+
////	"},"+
//"]";
//			}
//
//			if(user.getRole().equals(3)){
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=1'},"+
//		"		{'id':12,'parentId':1,'leaf' : true,'text':'拆解单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=5'},"+
//		"		{'id':13,'parentId':1,'leaf' : true,'text':'预处理单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=6'},"+
//		"		{'id':14,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
//		"		{'id':15,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
//	//",{"+
//	//"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
//	//"	'children':["+
//	//"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
//	//"	]"+
//	//"},"+
//	"]";
//			}
//			if(user.getRole().equals(4)){//水道
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=2'},"+
////		"		{'id':12,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
////		"		{'id':13,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
//	//",{"+
//	//"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
//	//"	'children':["+
//	//"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
//	//"	]"+
//	//"},"+
//	"]";
//			}
//			if(user.getRole().equals(5)){//燃气
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'燃气表工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=3'},"+
////		"		{'id':12,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
////		"		{'id':13,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
//	//",{"+
//	//"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
//	//"	'children':["+
//	//"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
//	//"	]"+
//	//"},"+
//	"]";
//			}
//			if(user.getRole().equals(6)){//电磁水表
//				return "[{ "+
//		"'id':1,'parentId':0,'text':'测试工单管理','leaf' : false,'url':null,"+
//		"'children':["+
//		"		{'id':11,'parentId':1,'leaf' : true,'text':'电磁水表工单查询' ,'url':'manageApp.forum.controller.bill.Bill?sysType=4'},"+
////		"		{'id':12,'parentId':1,'leaf' : true,'text':'流程模板' ,'url':'manageApp.forum.controller.flowTemplate.FlowTemplate'},"+
////		"		{'id':13,'parentId':1,'leaf' : true,'text':'维修处理' ,'url':'manageApp.forum.controller.repair.Repair'}"+
//		"]"+
//	"}"+
//	//",{"+
//	//"	'id':2,'parentId':0,'text':'用户管理','leaf' : false,'url':null,"+
//	//"	'children':["+
//	//"			   {'id':21,'parentId':2,'leaf' : true,'text':'用户列表','url':'manageApp.forum.controller.user.User'}"+
//	//"	]"+
//	//"},"+
//	"]";
//			}
//
//
		}


return null;
//		return "[]";
	}
}
