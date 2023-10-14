package org.example.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.advice.ExceptionEnums;
import org.example.advice.MyException;
import org.example.dao.ResourcesMapper;
import org.example.entity.Resources;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Resource
    private ResourcesMapper resourcesMapper;
    @Resource
    private RoleService roleService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        Set<String> permissionSet = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Role role = roleService.getById(user.getRoleId());
        List<Resources> roleResource = resourcesMapper.findRoleResource(user.getRoleId(), null);
        if (!CollectionUtils.isEmpty(roleResource)) {
            roleResource.forEach(v -> {
                if (!StringUtils.isEmpty(v.getPermission())) {
                    permissionSet.add(v.getPermission());
                }
            });
        }
        info.addRole(role.getRoleName());
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证登录
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String username = usernamePasswordToken.getUsername();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);

        User user = userService.getOne(userLambdaQueryWrapper);
        if (Objects.isNull(user)) {
            throw new MyException(ExceptionEnums.ACCOUNT_IS_NOT_EXIT);
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
