package DongYu.WebBase.System.Entity;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {

    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点id
     */
    private Long pId;

    /**
     * 节点名称
     */
    private String text;
    /**
     * 是否叶子节点
     */
    private Boolean leaf;
    /**
     * 是否打开节点
     */
    private String  open;

    /**
     * 是否被选中
     */
    private Boolean checked;

    /**
     * url地址
     */

    private String url;

    /**
     * 子节点的集合
     */
    private List<MenuTree> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<MenuTree> linkedList = new ArrayList<>();

    private String menuFlag;

    public String getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public List<MenuTree> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(List<MenuTree> linkedList) {
        this.linkedList = linkedList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public MenuTree(Long id,Long pId,String text){
        this.id=id;
        this.pId=pId;
        this.text=text;
    }
//    public static MenuTree createParent() {
//        MenuTree zMenuTree = new MenuTree();
//        zMenuTree.setChecked(true);
//        zMenuTree.setId(0L);
//        zMenuTree.setName("顶级");
//        zMenuTree.setOpen(true);
//        zMenuTree.setpId(0L);
//        return zMenuTree;
//    }

    public MenuTree(){
        super();
    }

    @Override
    public String toString() {
        return "{id=" + id + ", pId=" + pId + ", text=" + text+", leaf="+leaf +", url="+url+ ", children=" + children + "}";
    }



    public  List<MenuTree> ToMenuTree(List<MenuTree> treeList) {
        List<MenuTree> retList = new ArrayList<>();
        for (MenuTree parent : treeList) {
            if ((parent.getpId()).equals(0L)) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private  MenuTree findChildren(MenuTree parent, List<MenuTree> treeList ) {
        for (MenuTree child : treeList) {
            if (null!=child.getMenuFlag()&&child.getMenuFlag().equals("2"))
                continue;
            if (parent.getId().equals(child.getpId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<MenuTree>());
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


