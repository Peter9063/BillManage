package DongYu.WebBase.System.ServiceImp;


import DongYu.WebBase.System.Entity.Role;
import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Mapping.RelationMapper;
import DongYu.WebBase.System.Mapping.RoleMapper;
import DongYu.WebBase.System.Mapping.RowBounds;
import DongYu.WebBase.System.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public WebMessage<Role> save(Role[] records, String userName) {
        WebMessage<Role> msg=new WebMessage<Role>();
        Date date = new Date();
        if(records!=null){
            Role role=new Role();
            role.setName(records[0].getName());
            role= roleMapper.findOne(role);
            if(role!=null){
                msg.setSuccess(false);
                msg.setMessage("已存在该角色");
                return msg;
            }else {
                records[0].setCreateTime(date);
                records[0].setCreateUser(userName);
                roleMapper.insert(records[0]);
            }
        }
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    @Override
    public WebMessage<Role> delete(Role[] records) {
        WebMessage<Role> msg=new WebMessage<Role>();
        if(records!=null&&!records[0].getName().equals("超级管理员")){
            roleMapper.deleteById(records[0]);
            relationMapper.deleteByMenuIdAndRoleId(records[0].getRoleId(),null);
        }else {
            msg.setSuccess(false);
            msg.setMessage("删除失败");
            return msg;
        }
        msg.setSuccess(true);
        msg.setMessage("删除成功!");
        return msg;
    }

    @Override
    public WebMessage<Role> update(Role[] records, String userName) {
        WebMessage<Role> msg=new WebMessage<Role>();
        // 1L默认超级管理员
        if(records!=null&&records[0].getRoleId()!=1L){
            Role role=new Role();
            role.setName(records[0].getName());
            role=roleMapper.findOne(role);
            if(role!=null&&role.getRoleId()!=records[0].getRoleId()){
                msg.setSuccess(false);
                msg.setMessage("已存在该角色");
                return msg;
            }else {
                records[0].setUpdateTime(new Date());
                records[0].setUpdateUser(userName);
                roleMapper.update(records[0]);
            }
        }else {
            msg.setSuccess(false);
            msg.setMessage("修改失败");
            return msg;
        }
        msg.setSuccess(true);
        msg.setMessage("修改成功!");
        return msg;
    }

    @Override
    public List<Role> select(Role records, Integer start, Integer limit, Sorte[] sortes) {
        return null;
    }

    @Override
    public Long getCount(Role record) {
        return null;
    }

    @Override
    public WebMessage findPage(Role record, Integer start, Integer limit, String sort) {

        Sorte[] sortes = Sorte.parserSorte(sort);
        RowBounds rowBounds=null;
        if(start!=null&&limit!=null){
            rowBounds=new RowBounds(start,limit);
        }
        List<Role> list=roleMapper.findPage(record,rowBounds , Sorte.getSqlOrderStr(sortes));
        Long total=roleMapper.getCount(record);
        WebMessage<List<Role>> msg=new WebMessage<List<Role>>();
        msg.setMessage("ok");
        msg.setSuccess(true);
        msg.setTotal(total);
        msg.setData(list);
        return msg;

//        return null;
    }

    @Override
    public List<Role> findPage() {
        Role role = new Role( );
        return roleMapper.findPage(role,null,null);
    }

}
