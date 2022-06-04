package DongYu.WebBase.System.ServiceImp;


import DongYu.WebBase.System.Entity.Relation;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Mapping.RelationMapper;
import DongYu.WebBase.System.Service.RelationService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public  class RelationServiceImp implements RelationService {

    @Autowired
    RelationMapper relationMapper;


    /**
     * 增加角色权限关系
     * @param record
     * @return
     */
    @Override
    public WebMessage insert(Relation[] record) {
        WebMessage msg = new WebMessage();
        if(record!=null&&record.length>0) {
            for (Relation relation : record) {
                if(relation!=null){
                    relationMapper.insert(relation);
                }
            }
        }
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    /**
     * 增加角色资源关系
     * @param data
     * @param roleId
     * @return
     */
    @Override
    public WebMessage insertRelation(String data, Long roleId) {
          return   this.deleteRelation(data,roleId);
    }


    /**
     * 删除角色资源关系表
     * @param data
     * @param roleId
     * @return
     */
    @Override
    public WebMessage deleteRelation(String data, Long roleId) {
        WebMessage msg=new WebMessage();
       List<Relation> list= this.findAllByRoleId(roleId);//roleId 数据库已存的相关数据
        JSONArray json = JSONArray.fromObject(data);
        List<Relation> list1=new ArrayList<>();
        for (int i=0;i<json.size();i++){
           Relation relation=new Relation();
           relation.setRoleId(roleId);
           relation.setMenuId(Long.parseLong(json.getJSONObject(i).getString("id")));
           list1.add(relation);
        }
        for(Relation relation: getDifferent(list1,list)){
            relationMapper.deleteByMenuIdAndRoleId(relation);
        }
        for (Relation relation: getDifferent(list,list1)){
            relationMapper.insert(relation);
        }

        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    /**
     * 返回  list2减去list1 的数据
     * @param list1
     * @param list2
     * @return
     */
    public List<Relation>  getDifferent(List<Relation> list1, List<Relation> list2){
        List<Relation> diff=new ArrayList<>();
        Map<Relation,Integer> map=new HashMap<>();
        for(Relation relation: list1){
            map.put(relation,1);
        }
        for(Relation relation : list2){
            if(map.get(relation)==null){
                diff.add(relation);
            }
        }
        return diff;
    }
    @Override
    public void deleteByMenuIdAndRoleId(Relation record) {

        relationMapper.deleteByMenuIdAndRoleId(record);
    }

    @Override
    public List<Relation> findAllByRoleId(Long roleId) {
        return relationMapper.findAllByRoleId(roleId);
    }


}
