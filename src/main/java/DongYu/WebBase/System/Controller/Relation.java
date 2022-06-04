package DongYu.WebBase.System.Controller;


import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/base/relation")
public class Relation {

    @Autowired
    RelationService relationService;

    @RequestMapping(value = "/insertRelation")
    @ResponseBody
    public WebMessage insertRelation(
            String data,
             Long roleId){

        return  relationService.insertRelation(data,roleId);
    }

}
