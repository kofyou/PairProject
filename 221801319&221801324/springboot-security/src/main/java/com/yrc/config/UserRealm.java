package com.yrc.config;


import com.yrc.mapper.UserMapper;
import com.yrc.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

//自定义UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权user");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("userPermission");
       return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证user");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userMapper.queryUserByName(userToken.getUsername());
        if (user==null) //没有这个用户
        {
            return null; //UnknownAccountException异常
        }
        if (user!=null)
        {
            if (user.getTip_off_statue()==1)//如果用户被封禁
            {
                throw new DisabledAccountException("账户已被禁用！");
            }
        }
        String password = "";
        if (userToken.getPassword()!= null){
            password = new String(userToken.getPassword());
        }
        System.out.println("用户名"+userToken.getUsername()+"密码"+password);
        // 返回给安全管理器，由 securityManager 比对密码的正确性
        //密码认证shiro自己做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
