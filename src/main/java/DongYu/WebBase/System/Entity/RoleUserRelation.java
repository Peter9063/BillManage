package DongYu.WebBase.System.Entity;



public class RoleUserRelation {

    /**
     * 角色  用户 关联表
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 状态
     */
    private String status;



    /**
     * 设置角色  用户 关联表
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 修改角色  用户 关联表
     */
    public Long getId() {
        return this.id;
    }


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
     * 设置用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 修改用户id
     */
    public Long getUserId() {
        return this.userId;
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


    @Override
    public String toString() {
        return  "{id:"+id+",roleId :"+roleId+", userId :"+userId+"}";
    }

    @Override
    public int hashCode() {
        String result =  userId+roleId+"";
        return result.hashCode();
//        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        RoleUserRelation r = (RoleUserRelation) obj;
         return        (this.getRoleId().equals(r.getRoleId()))&&(this.getUserId().equals(r.getUserId()));
    }
}