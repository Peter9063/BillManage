package DongYu.WebBase.System.ServiceImp;


import DongYu.WebBase.System.Entity.Menu;
import DongYu.WebBase.System.Entity.MenuTree;
import DongYu.WebBase.System.Entity.Relation;
import DongYu.WebBase.System.Entity.Role;
import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Mapping.MenuMapper;
import DongYu.WebBase.System.Mapping.RelationMapper;
import DongYu.WebBase.System.Mapping.RoleMapper;
import DongYu.WebBase.System.Mapping.RowBounds;
import DongYu.WebBase.System.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImp  implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RelationMapper relationMapper;

    @Autowired
    RoleMapper roleMapper;
    /**
     *
     * @param menu
     * @param userName
     * @return
     */
    @Override
    public WebMessage<Menu> save(Menu[] menu, String userName) {
        WebMessage<Menu> msg=new WebMessage<Menu>();
        Menu menu1=new Menu();
        if(menu!=null){
            menu1.setName(menu[0].getName());
            menu1= menuMapper.findOne(menu1);
            if(menu1!=null){
                msg.setSuccess(false);
                msg.setMessage("已存在该菜单");
                return msg;
            }else {
                menu[0].setCreateTime(new Date());
                menu[0].setCreateUser(userName);
                menuMapper.insertMenu(menu[0]);


                //增加的菜单赋予超级管理员
                //start
                menu[0].setMenuId(null);
                menu1 = new Menu();
                menu1.setName(menu[0].getName());
                menu1 = menuMapper.findOne(menu1);
                Long menuId = menu1.getMenuId();
                Relation relation = new Relation();
                Role role = new Role();
                role.setName("超级管理员");
                Long roleId = roleMapper.findOne(role).getRoleId();
                if (roleId != null) {
                    relation.setMenuId(menuId);
                    relation.setRoleId(roleId);
                    relationMapper.insert(relation);
                }
                //end
            }
        }
        msg.setSuccess(true);
        msg.setMessage("保存成功!");
        return msg;
    }

    @Override
    public WebMessage<Menu> delete(Menu[] records) {
        WebMessage<Menu> msg=new WebMessage<Menu>();
        if(records!=null){
            menuMapper.deleteById(records[0]);
            relationMapper.deleteByMenuIdAndRoleId(null,records[0].getMenuId());
        }else {
        }
        msg.setSuccess(true);
        msg.setMessage("删除成功!");
        return msg;
//        return null;
    }

    @Override
    public WebMessage<Menu> update(Menu[] records,String name) {
        WebMessage<Menu> msg=new WebMessage<Menu>();
        Date date = new Date();
        if(records!=null){
            Menu menu=new Menu();
            menu.setName(records[0].getName());
            menu.setUrl(records[0].getUrl());
            menu=menuMapper.findOne(menu);
            if(menu!=null&&menu.getMenuId()!=records[0].getMenuId()){
                msg.setSuccess(false);
                msg.setMessage("已存在该菜单");
                return msg;
            }else {
                records[0].setupdateTime(date);
                records[0].setUpdateUser(name);
                menuMapper.update(records[0]);
            }
        }
        msg.setSuccess(true);
        msg.setMessage("修改成功!");
        return msg;
    }

    @Override
    public List<Menu> select(Menu records, Integer start, Integer limit, Sorte[] sortes) {

        List<Menu> list=new ArrayList<>();
//        list.add(menuMapper.findAll());
        return list;
    }

    @Override
    public Long getCount(Menu record) {
        return menuMapper.getCount(record);
    }

    @Override
    public WebMessage findPage(Menu record, Integer start, Integer limit, String sort) {
        Sorte[] sortes = Sorte.parserSorte(sort);
        RowBounds rowBounds;
        if(null!=start&&null!=limit){
            rowBounds=new RowBounds(start,limit);
        }else {
            rowBounds=new RowBounds();
        }
        List<Menu> list=menuMapper.findPage(record, rowBounds, Sorte.getSqlOrderStr(sortes));
//        List<Menu> list=menuMapper.findPage(record, null,null);
        Long total=menuMapper.getCount(record);
        WebMessage<List<Menu>> msg=new WebMessage<List<Menu>>();
        msg.setMessage("ok");
        msg.setSuccess(true);
        msg.setTotal(total);
        msg.setData(list);
        return msg;
    }

    @Override
    public List<MenuTree> getMenuTreeList() {
        List<MenuTree> treeList=menuMapper.getMenuTreeList();
        MenuTree menuTree=new MenuTree();
        return menuTree.ToMenuTree(treeList);
    }

    /**
     * 根据 roleIds 获取所有菜单
     * @param roles
     * @return
     */
    @Override
    public List<MenuTree> getMenusByRoleIds(List<Long> roles) {
        List<MenuTree> list = menuMapper.getMenusByRoleIds(roles);
        return  list;
    }

    /**
     * 获取 菜单的所有内容，并标注已有权限（checked=true）
     * @param roles
     * @return
     */
    @Override
    public List<MenuTree> getMenuTreeListByRoleIds(List<Long> roles) {
        List<MenuTree> treeList=menuMapper.getMenuTreeList();
        List<MenuTree> treeList1=this.getMenusByRoleIds(roles);

        for (MenuTree list : treeList) {
            list.setChecked(false);
            for (MenuTree list1:treeList1){
                if(list.getId()==list1.getId()){
                    list.setChecked(true);
                }
            }
        }
        MenuTree menuTree=new MenuTree();
        return menuTree.ToMenuTree(treeList);
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> list= menuMapper.findAllMenu();
        return this.listToTreeList(list);
    }

    /**
     * Menu list 转化为 treeList 格式
     * @param list
     * @return
     */
    @Override
    public List<Menu> listToTreeList(List<Menu> list) {
        List<Menu> retList = new ArrayList<>();
        for (Menu parent : list) {
            if ((parent.getpId()).equals(0L)) {
                retList.add(this.findChildren(parent, list));
            }
        }
        return retList;
    }

    /**
     *Menu list 转化为 treeList 格式  的 递归子操作
     * @param parent
     * @param treeList
     * @return
     */
    @Override
    public Menu findChildren(Menu parent, List<Menu> treeList) {
        for (Menu child : treeList) {
            if (parent.getMenuId().equals(child.getpId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<Menu>());
                    parent.setLeaf(false);
                }
                child.setLeaf(true);
                parent.getChildren().add(findChildren(child, treeList));
//                child.setChecked(false);
            }
        }
        return parent;
    }


}
