package com.mobei.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * 角色和权限在ini配置文件中写死
 */
public class ShiroTest02 {

    private SecurityManager securityManager;

    @Before
    public void init() {
        //1.根据配置文件创建SecurityManagerFactory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-test-2.ini");

        //2.通过工厂获取SecurityManager
        SecurityManager securityManager = factory.getInstance();

        //3.将SecurityManager绑定到当前运行环境
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testLogin() {
        //4.从当前运行环境中构造Subject
        Subject subject = SecurityUtils.getSubject();

        //5.构造shiro登录的数据
        String username = "zhangsan";//模拟登录用户名
        String pwd = "123456";//模拟用户登录密码

        AuthenticationToken token = new UsernamePasswordToken(username, pwd);

        //6.主体登录
        subject.login(token);

        /**
         * 7.登陆成功后完成授权:
         *  授权:校验当前登录用户是否具有操作权限,是否具有某个角色
         */
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:save"));
    }

}
