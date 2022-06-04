package DongYu.WebBase.System.Mapping;

public class Relation {

    /**
     * 角色 资源 关联表
     */
    private Long relationId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 状态
     */
    private String status;


    /**
     * 设置角色 资源 关联表
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
    /**
     * 修改角色 资源 关联表
     */
    public Long getRelationId() {
        return this.relationId;
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


    public Relation(){
        super();
    }

    public Relation(Long menuId , Long roleId){
        this.menuId=menuId;
        this.roleId=roleId;
    }

    public String toString() {
        return "id:"+relationId+",menuId:"+menuId+",roleId:"+roleId+",status:"+status;
    }
}
