package DongYu.WebBase.System.Service;

import DongYu.WebBase.System.Entity.Relation;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;

import java.util.List;

public interface RelationService {

    public WebMessage insert(DongYu.WebBase.System.Entity.Relation[] record);

    public  WebMessage insertRelation(String data, Long roleId);

    public  WebMessage deleteRelation(String data, Long roleId);

    public void deleteByMenuIdAndRoleId(DongYu.WebBase.System.Entity.Relation record);

    public List<Relation> findAllByRoleId(Long roleId);

    public List<Relation>  getDifferent(List<Relation> list1, List<Relation> list2);


    }
