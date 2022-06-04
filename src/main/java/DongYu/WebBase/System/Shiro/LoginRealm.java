package DongYu.WebBase.System.Shiro;


import DongYu.WebBase.System.Entity.MenuTree;
import DongYu.WebBase.System.Entity.RoleUserRelation;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Mapping.UserMapper;
import DongYu.WebBase.System.Service.MenuService;
import DongYu.WebBase.System.Service.RoleUserRelationService;
import DongYu.WebBase.System.Utils.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    RoleUserRelationService roleUserRelationService;

    @Autowired
    MenuService menuService;

    @Autowired
    private UserMapper userMapper;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(LoginRealm.class);


    /**
     * 登陆
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();                //得到用户名
        String password = new String((char[]) authenticationToken.getCredentials());  //得到密码

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user = userMapper.findOne(user);

//        /**检测是否有此用户 **/
        if (user == null) {
            throw new UnknownAccountException();//没有找到账号异常
        }
        SecurityUtils.getSubject().isPermitted();

        if (null != username && null != password) {
            logger.info("用户：{}，密码：{},登陆时间：{}", new Object[]{username, password, DateUtil.getTime()});
            return new SimpleAuthenticationInfo(user, password, getName());
        } else {
            return null;
        }
    }


    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();
        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        List<Long> list = new ArrayList<>();
        RoleUserRelation roleUserRelation = new RoleUserRelation();
        roleUserRelation.setUserId(user.getId().longValue());
        List<RoleUserRelation> list1 = roleUserRelationService.findPage(roleUserRelation, null, null);

        for (RoleUserRelation roleUserRelation1 : list1) {
            list.add(roleUserRelation1.getRoleId());
        }

        List<MenuTree> menuTrees = menuService.getMenusByRoleIds(list);

        for (MenuTree item : menuTrees) {
            if (null != item.getUrl() && !"".equals(item.getUrl())) {
                permissionSet.add(item.getUrl());
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }


}