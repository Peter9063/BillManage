package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.Menu;
import DongYu.WebBase.System.Entity.MenuTree;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Service.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/base/menu")
public class MenuAction {

    @Autowired
    MenuService menuService;

    /**
     * 查找 父节点 id 及 name
     * @return
     */
    @RequestMapping(value = "/findPCode")
    @ResponseBody
    public WebMessage findPCode(){
        WebMessage webMessage=new WebMessage();
        webMessage.setSuccess(true);
        List<Map> list = new ArrayList<Map>();
        Map mp=new HashMap();
        mp.put("name","menu1");
        mp.put("value","value2");
        list.add(mp);
        list.add(mp);
        list.add(mp);
        webMessage.setData(list);
        return webMessage;
    }



    /**
     * 查找菜单
     * @return
     */
    @RequestMapping(value = "/findMenu")
    @ResponseBody
    public WebMessage findMenu(Menu record, Integer start, Integer limit, @Param("sort") String sort){
        return menuService.findPage(record,start,limit,sort);
    }
    /**
     * 查找菜单
     * @return
     */
    @RequestMapping(value = "/findAllMenu")
    @ResponseBody
    public List<Menu> findAllMenu(@Param("sort") String sort){
//        return menuService.findPage(record,start,limit,sort);
        List<Menu> list=menuService.findAllMenu();
        System.err.println(list);
        return list;
    }



    /**
     *菜单 菜单树(不含 check)
     * @return
     */
    @RequestMapping(value = "/getMenuTreeList")
    @ResponseBody
    public List<MenuTree> getMenuTreeList(){
        return menuService.getMenuTreeList();
    }

    /**
     *角色权限  菜单树（含check） （供侧面菜单栏及权限界面使用）
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getMenuTreeListByRoleIds")
    @ResponseBody
    public List<MenuTree> getMenuTreeListByRoleIds(Long roleId){
        System.err.println(roleId);
        List<Long> list=new ArrayList<>();
        list.add(roleId);
        return menuService.getMenuTreeListByRoleIds(list);
    }
    /**
     * 增加菜单
     * @return
     */
    @RequestMapping(value = "/insertMenu")
    @ResponseBody

    public WebMessage insertMenu(@RequestBody Menu[] menu, HttpSession httpSession){
//        System.err.println(parentId);
        System.err.println(httpSession.getAttribute("user"));
        User user=(User)httpSession.getAttribute("user");
        String name=user.getAlias();
        return menuService.save(menu,name);
    }

    /**
     * 删除菜单
     * @return
     */
    @RequestMapping(value = "/deleteMenu")
    @ResponseBody
    public WebMessage deleteMenu(@RequestBody Menu[] menu){
        return menuService.delete(menu);
    }

    /**
     * 修改菜单
     * @return
     */
    @RequestMapping(value = "/updateMenu")
    @ResponseBody
    public WebMessage updateMenu(@RequestBody Menu[] menu, HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        String name=user.getAlias();
        return menuService.update(menu,name);

    }

}
