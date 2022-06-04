package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Service.RoleUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/base/roleUserRelation")
public class RoleUserRelationAction {

    @Autowired
    RoleUserRelationService roleUserRelationService;
    /**
     *
     * @param data
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/insertRelation")
    @ResponseBody
    public WebMessage insertRelation(
            String data,
            Long roleId){

       return  roleUserRelationService.insertRelation(data,roleId);
    }


    /**
     * 删
     */
    @RequestMapping(value = "/deleteRelation")
    @ResponseBody
    public WebMessage deleteRelation(
            String data,
            Long roleId){
        return  roleUserRelationService.deleteRelation(data,roleId);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public WebMessage delete(
           @RequestBody User[] record,
            Long roleId){

        return roleUserRelationService.delete(record,roleId);
    }

    /**
     * 改
     */

    /**
     * 查
     */

}
