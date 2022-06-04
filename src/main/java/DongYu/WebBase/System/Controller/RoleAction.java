package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.Role;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/base/role")
public class RoleAction {

  @Autowired
  RoleService roleService;


    /**
     * 增加角色
     * @param role
     * @param httpSession
     * @return
     */

    @RequestMapping(value = "/insertRole")
    @ResponseBody()
    public WebMessage<Role> insertRole(@RequestBody Role[] role, HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        String name=user.getAlias();
        return roleService.save(role,name);
    }
    /**
     * 删除角色
     * @param records
     * @return
     */
    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    public WebMessage<Role> delete(@RequestBody Role[] records){
        return roleService.delete(records);
    }

    /**
     * 更新角色
     * @param records
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public WebMessage<Role> update(@RequestBody Role[] records,HttpSession httpSession){

        User user=(User)httpSession.getAttribute("user");
        String name=user.getAlias();
        return roleService.update(records,name);
    }

    /**
     * 查找角色
     * @param record
     * @param start
     * @param limit
     * @param sort
     * @return
     */
    @RequestMapping(value = "/findRole")
    @ResponseBody
    public WebMessage findRole(Role record,Integer start,Integer limit,String sort){

        return roleService.findPage(record,start,limit,sort);
    }


    /**
     * 获取数量
     * @param record
     * @return
     */
    public Long getCount(Role record){
        return null;
    }

}


