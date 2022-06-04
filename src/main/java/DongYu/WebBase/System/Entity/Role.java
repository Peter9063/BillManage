package DongYu.WebBase.System.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Role {

    /**
     * 角色id  
     */
    private Long roleId;
    /**
     * 角色名称  
     */
    private String name;
    /**
     * 备注  
     */
    private String description;
    /**
     * 序号  
     */
    private Integer sort;
    /**
     * 状态  
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
     * 设置角色id  
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    /**
     * 修改角色id  
     */
    public Long getRoleId() {
        return this.roleId;
    }


    /**
     * 设置角色名称  
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 修改角色名称  
     */
    public String getName() {
        return this.name;
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
     * 设置序号  
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    /**
     * 修改序号  
     */
    public Integer getSort() {
        return this.sort;
    }


    /**
     * 设置状态  
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * 修改状态  
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
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 修改修改时间  
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTimeBegin(Date value) {
        this.updateTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getUpdateTimeBegin() {
        return this.updateTimeBegin;
    }

    public void setUpdateTimeEnd(Date value) {
        this.updateTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getUpdateTimeEnd() {
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

    public Role(){
        super();
    }

    public Role(String name,String description,Integer sort ,String status ){
        this.name=name;
        this.description=description;
        this.sort=sort;
        this.status=status;
    }
}
