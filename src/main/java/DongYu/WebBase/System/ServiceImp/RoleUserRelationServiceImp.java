package DongYu.WebBase.System.ServiceImp;


import DongYu.WebBase.System.Entity.RoleUserRelation;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Mapping.RoleUserRelationMapper;
import DongYu.WebBase.System.Mapping.UserMapper;
import DongYu.WebBase.System.Service.RoleUserRelationService;
import DongYu.WebBase.System.Service.UserService;
import net.sf.json.JSONArray;

import DongYu.WebBase.System.Mapping.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleUserRelationServiceImp implements RoleUserRelationService {


    @Autowired
    RoleUserRelationMapper roleUserRelationMapper ;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;



    @Override
    public WebMessage insertRelation(String data, Long roleId) {


        WebMessage msg=new WebMessage();
        List<RoleUserRelation> list=this.decode(data,roleId);
        for (RoleUserRelation r : list){
            this.insert(r);
        }

        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    @Override
    public WebMessage deleteRelation(String data, Long roleId) {
        WebMessage msg=new WebMessage();
        List<RoleUserRelation> list=this.decode(data,roleId);
        for (RoleUserRelation r : list){
            this.deleteById(r);
        }
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    @Override
    public WebMessage delete(User[] record, Long roleId) {
        WebMessage msg=new WebMessage();
        List<RoleUserRelation> list=new ArrayList<>();
        for(User user : record){
            RoleUserRelation r = new RoleUserRelation();
            r.setRoleId(roleId);
            r.setUserId(user.getId().longValue());
            list.add(r);
        }
        for(RoleUserRelation roleUserRelation : list){
            this.deleteById(roleUserRelation);
//            roleUserRelationMapper.deleteByRoleIdAndUserId(roleUserRelation);
        }
        msg.setSuccess(true);
        msg.setMessage("移除成功!");
        return msg;
    }


    @Override
    public WebMessage insert(RoleUserRelation record) {
        WebMessage msg=new WebMessage();
        roleUserRelationMapper.insert(record);
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    @Override
    public WebMessage update(RoleUserRelation record) {
//        roleUserRelationMapper.update(record);
        return null;
    }

    @Override
    public WebMessage deleteById(RoleUserRelation record) {
        WebMessage msg=new WebMessage();
        roleUserRelationMapper.deleteById(record);
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return  msg;
    }

    @Override
    public RoleUserRelation findOne(RoleUserRelation record) {
//        roleUserRelationMapper.findOne(record);
        return null;
    }

    @Override
    public Long getCount(RoleUserRelation record) {
//        roleUserRelationMapper.getCount(record);
        return null;
    }

    @Override
    public List<RoleUserRelation> findPage(RoleUserRelation record, RowBounds rowBounds, String orderBy) {
      return  roleUserRelationMapper.findPage(record,rowBounds,orderBy);
//        return null;
    }

    @Override
    public List<RoleUserRelation> findAllByRoleId(List<Long> roleIds) {
        return roleUserRelationMapper.findAllByRoleIds(roleIds);
    }


    //解析 转化 前端总的relation
    public List<RoleUserRelation> decode(String data, Long roleId){
        JSONArray jsonArray=JSONArray.fromObject(data);
        List<User> userList=(List<User>) JSONArray.toCollection(jsonArray,User.class);
        return this.toList(userList,roleId);
    }

    public List<RoleUserRelation> toList(List<User> userList , Long roleId){
        List<RoleUserRelation> list=new ArrayList<>();
        for(User user : userList){
            RoleUserRelation r = new RoleUserRelation();
            r.setRoleId(roleId);
            r.setUserId(user.getId().longValue());
            list.add(r);
        }
        return list;
    }

}
