package DongYu.WebBase.System.Entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Menu {

    /**
     * 菜单id  
     */
    private Long menuId;
    /**
     * 菜单编号  
     */
    private String code;
    /**
     * 菜单父编号  
     */
    private Long pId;
    /**
     * 菜单名称  
     */
    private String name;
    /**
     * url地址  
     */
    private String url;
    /**
     * 排序  
     */
    private Integer sort;
    /**
     * 层级  
     */
    private Integer levels;
    /**
     * 是否菜单  
     */
    private String menuFlag;
    /**
     * 备注  
     */
    private String description;
    /**
     * 菜单状态  
     */
    private String status;
    /**
     * 创建时间  
     */
    private Date createTime;
    private Date createTimeBegin;
    private Date createTimeEnd;
    /**
     * 修改时间  
     */
    private Date updateTime;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    /**
     * 创建人  
     */
    private String createUser;
    /**
     * 修改人  
     */
    private String updateUser;


    /**
     * 是否叶子节点
     */
    private Boolean leaf;
    /**
     * 子节点的集合
     */
    private List<Menu> children;

    /**
     * Ext的tree panel 默认添加的参数
     */
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    //    TreeList treeList;
    public Menu(){
        super();
    }
    public Menu(Long id,String name,Long pid){
        //id pid text(name) leaf url children
        this.menuId = id;
        this.name = name;
        this.pId = pid;
    }

    /**
     * 设置菜单id  
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    /**
     * 修改菜单id  
     */
    public Long getMenuId() {
        return this.menuId;
    }


    /**
     * 设置菜单编号  
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 修改菜单编号  
     */
    public String getCode() {
        return this.code;
    }


    /**
     * 设置菜单父编号  
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }
    /**
     * 修改菜单父编号  
     */
    public Long getpId() {
        return this.pId;
    }


    /**
     * 设置菜单名称  
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 修改菜单名称  
     */
    public String getName() {
        return this.name;
    }


    /**
     * 设置url地址  
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 修改url地址  
     */
    public String getUrl() {
        return this.url;
    }


    /**
     * 设置排序  
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    /**
     * 修改排序  
     */
    public Integer getSort() {
        return this.sort;
    }


    /**
     * 设置层级  
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }
    /**
     * 修改层级  
     */
    public Integer getLevels() {
        return this.levels;
    }


    /**
     * 设置是否菜单  
     */
    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag;
    }
    /**
     * 修改是否菜单  
     */
    public String getMenuFlag() {
        return this.menuFlag;
    }


    /**
     * 设置备注  
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 修改备注  
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * 设置菜单状态  
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * 修改菜单状态  
     */
    public String getStatus() {
        return this.status;
    }



    /**
     * 设置创建时间  
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 修改创建时间  
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTimeBegin(Date value) {
        this.createTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    public void setCreateTimeEnd(Date value) {
        this.createTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getCreateTimeEnd() {
        return this.createTimeEnd;
    }



    /**
     * 设置修改时间  
     */
    public void setupdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 修改修改时间  
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getupdateTime() {
        return this.updateTime;
    }
    public void setupdateTimeBegin(Date value) {
        this.updateTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getupdateTimeBegin() {
        return this.updateTimeBegin;
    }

    public void setupdateTimeEnd(Date value) {
        this.updateTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getupdateTimeEnd() {
        return this.updateTimeEnd;
    }


    /**
     * 设置创建人  
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * 修改创建人  
     */
    public String getCreateUser() {
        return this.createUser;
    }


    /**
     * 设置修改人  
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * 修改修改人  
     */
    public String getUpdateUser() {
        return this.updateUser;
    }


    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString(){
        return "{menuId:"+ menuId+",code:"+code+",pId:"+pId
                +",neme:"+name+",url:"+url+",sort:"+sort+
                ",levels:"+levels+",menuFlag:"+menuFlag+
                ",description:"+description+",status:"+status
                +",createTime:"+createTime+",createUser:"+createUser
                +",updateUser:"+updateUser+",updateTime:"+updateTime+"}";
    }
}
