/*
 * Powered By DongGuan SoftWare
 * Since 2017 - 2017
 */

package DongYu.WebBase.System.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class User {

    /**
     *
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户别名
     */
    private String alias;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 新密码
     */
    private String newPassWord;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 部门
     */
    private String depart;
    /**
     * 角色
     */
    private Integer role;
    /**
     * 角色
     */
    private String roles;
    /**
     * 状态 0未启用，1启用
     */
    private Integer state;

    /**
     *系统类型
     */
    private Integer sysType;
    /**
     * 创建时间
     */
    private Date createTime;
    private Date createTimeBegin;
    private Date createTimeEnd;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date modifTime;
    private Date modifTimeBegin;
    private Date modifTimeEnd;
    /**
     * 修改人
     */
    private String modifUser;


    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 修改
     */
    public Integer getId() {
        return this.id;
    }


    /**
     * 设置用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 修改用户名
     */
    public String getUserName() {
        return this.userName;
    }


    /**
     * 设置用户别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    /**
     * 修改用户别名
     */
    public String getAlias() {
        return this.alias;
    }


    /**
     * 设置密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    /**
     * 修改密码
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * 设置密码
     */
    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
    /**
     * 修改密码
     */
    public String getNewPassWord() {
        return this.newPassWord;
    }
    /**
     * 设置邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 修改邮箱
     */
    public String getEmail() {
        return this.email;
    }


    /**
     * 设置性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * 修改性别
     */
    public String getSex() {
        return this.sex;
    }


    /**
     * 设置部门
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }
    /**
     * 修改部门
     */
    public String getDepart() {
        return this.depart;
    }


    /**
     * 设置角色
     */
    public void setRole(Integer role) {
        this.role = role;
    }
    /**
     * 修改角色
     */
    public Integer getRole() {
        return this.role;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * 设置状态 0未启用，1启用
     */
    public void setState(Integer state) {
        this.state = state;
    }
    /**
     * 修改状态 0未启用，1启用
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置系统类型
     * @return
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 修改系统类型
     * @param sysType
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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
     * 设置修改时间
     */
    public void setModifTime(Date modifTime) {
        this.modifTime = modifTime;
    }
    /**
     * 修改修改时间
     */
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.FullDateFormat)
    public Date getModifTime() {
        return this.modifTime;
    }
    public void setModifTimeBegin(Date value) {
        this.modifTimeBegin = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getModifTimeBegin() {
        return this.modifTimeBegin;
    }

    public void setModifTimeEnd(Date value) {
        this.modifTimeEnd = value;
    }
    @DateTimeFormat(pattern=DongYu.WebBase.System.Utils.AppConstant.ShortDateFormat)
    public Date getModifTimeEnd() {
        return this.modifTimeEnd;
    }


    /**
     * 设置修改人
     */
    public void setModifUser(String modifUser) {
        this.modifUser = modifUser;
    }
    /**
     * 修改修改人
     */
    public String getModifUser() {
        return this.modifUser;
    }


    public String toString(){
        return "{ id :"+id+", userName :"+userName+", alias :"+alias +"}";
    }

    /**
     * 重写hascode ,使Map
     * 对象 内存地址不一样
     *对于值对象，==比较的是两个对象的值
     *对于引用对象，比较的是两个对象的地址
     * @return
     */
    @Override
    public int hashCode() {
        String result =id+ userName;
        return result.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        User u = (User)obj;
        return  this.getId().equals(u.getId())
                &&(this.getUserName().equals(u.getUserName())
        );
    }


}