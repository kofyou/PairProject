package com.yrc.config;

import com.yrc.mapper.AdminMapper;
import com.yrc.pojo.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权admin");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("adminPermission");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证admin");
        UsernamePasswordToken adminToken = (UsernamePasswordToken) token;
        Admin admin = adminMapper.queryAdminByIName(adminToken.getUsername());
        if (admin==null){//无次管理员
            return null; //UnknownAccountException异常
        }
        String password = "";
        if (adminToken.getPassword()!= null){
            password = new String(adminToken.getPassword());
        }
        System.out.println("用户名"+adminToken.getUsername()+"密码"+password);
        // 返回给安全管理器，由 securityManager 比对密码的正确性
        //密码认证shiro自己做
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),"");
    }
}
