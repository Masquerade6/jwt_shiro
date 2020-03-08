package com.mobei.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realms对象:
 *      继承AuthorizingRealm,重写doGetAuthorizationInfo和doGetAuthenticationInfo方法
 *      一般还会重写setName方法
 */
public class PermissionRealm extends AuthorizingRealm {

    /**
     * 自定义realm名称
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("permissionRealm");
    }

    /**
     * 授权:获取到用户的授权数据(用户的权限数据)
     *      主要目的就是根据认证数据获取到用户的权限信息
     * @param principals 包含了所有已认证的安全数据
     * @return 授权数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权方法------");
        //1.获取安全数据:username,用户id
        String username = (String) principals.getPrimaryPrincipal();

        //2.根据id或者名称查询用户(这里省略数据库查询步骤,和3一起模拟)
        //3.查询用户的角色和权限信息
        List<String> perms = new ArrayList<>();//模拟查询到的用户权限
        perms.add("user:save");
        perms.add("user:update");

        List<String> roles = new ArrayList<>();//模拟查询到的用户角色
        roles.add("role1");
        roles.add("role2");

        //4.构造返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);//设置权限集合
        info.addRoles(roles);

        return info;
    }

    /**
     * 认证:根据用户名、密码登录,将用户数据保存(安全数据)
     *      目的是比较用户名和密码是否与数据库中的一致,
     *      将安全数据存入到shiro进行托管
     * @param token 登录构造的UsernamePasswordToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证方法------");
        //1.构造UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2.获取输入的用户名、密码
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        //3.根据用户名查询数据库:这里只是模拟,和4步骤合并在一起了,正常情况下需要查询数据库
        //4.比较密码和数据库中的密码是否一致(密码可能需要加密)
        if ("123456".equals(password)) {
            //5.如果成功,向shiro存入安全数据
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());//参数:1.安全数据,2.密码,3.当前realm域名称
            return info;
        } else {
            //6.如果失败可以抛出异常或者返回null
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
