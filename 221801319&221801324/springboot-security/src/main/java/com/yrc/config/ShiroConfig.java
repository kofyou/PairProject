package com.yrc.config;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean 3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean (@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
        /*
            anon:无需认证就可访问
            authc:必须认证了才可访问
            user:必须拥有 记住我 功能才能用
            perms:拥有对某个资源的权限才可以访问
            role:拥有某个角色权限才能访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        {
            filterMap.put("/activate", "perms[userPermission]"); //拦截
            filterMap.put("/usermain", "perms[userPermission]"); //拦截
            filterMap.put("/personalInfo", "perms[userPermission]"); //拦截
            filterMap.put("/updateInfo", "perms[userPermission]"); //拦截
            filterMap.put("/updateName", "perms[userPermission]"); //拦截
            filterMap.put("/updateEmail", "perms[userPermission]"); //拦截
            filterMap.put("/updatePassword", "perms[userPermission]"); //拦截
            filterMap.put("/Safe", "perms[userPermission]"); //拦截
            filterMap.put("/ask_question", "perms[userPermission]"); //拦截
            filterMap.put("/answer_question", "perms[userPermission]"); //拦截
            filterMap.put("/tip_off/{id_ask}", "perms[userPermission]"); //拦截
            filterMap.put("/answer_question/{question_id}", "perms[userPermission]"); //拦截
            filterMap.put("/email_active", "perms[userPermission]"); //拦截
            filterMap.put("/activate_email", "perms[userPermission]"); //拦截
            filterMap.put("/activate_box", "perms[userPermission]"); //拦截
            filterMap.put("/close_box", "perms[userPermission]"); //拦截
            filterMap.put("/update_username", "perms[userPermission]"); //拦截
            filterMap.put("/update_password", "perms[userPermission]"); //拦截
            filterMap.put("/question_list", "perms[userPermission]"); //拦截
            filterMap.put("/tip_off", "perms[userPermission]"); //拦截
            filterMap.put("/black/{id_ask}", "perms[userPermission]"); //拦截
            filterMap.put("/askQuestion", "perms[userPermission]"); //拦截
            filterMap.put("/answerQuestion", "perms[userPermission]"); //拦截
            filterMap.put("/delete/{id}", "perms[userPermission]"); //拦截
            filterMap.put("/updateHead", "perms[userPermission]"); //拦截
            filterMap.put("/user/socket", "perms[userPermission]"); }//拦截用户界面
        {
            filterMap.put("/user_query", "perms[adminPermission]"); //拦截
            filterMap.put("/tip_off_center", "perms[adminPermission]"); //拦截
            filterMap.put("/get_tip_off/{id_ask}", "perms[adminPermission]"); //拦截
            filterMap.put("/remove_tip_off/{id_ask}", "perms[adminPermission]"); //拦截
            filterMap.put("/admin/queryUser", "perms[adminPermission]"); //拦截
            filterMap.put("/admin/user_query_info/questions/{id}", "perms[adminPermission]"); //拦截
            filterMap.put("/admin/user_query_info/answers/{id}", "perms[adminPermission]"); //拦截
            filterMap.put("/admin/user_query_info/askeds/{id}", "perms[adminPermission]"); //拦截
            filterMap.put("/all_users", "perms[adminPermission]"); //拦截
            filterMap.put("/admin/socket", "perms[adminPermission]"); //拦截
        }//拦截管理员界面
            bean.setFilterChainDefinitionMap(filterMap);
            bean.setLoginUrl("/"); //跳至登录页面
            return bean;
    }

    //DafaultWebSecurityManager 2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager (){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(userRealm());
        realms.add(adminRealm());
        securityManager.setRealms(realms);
        return securityManager;
    }

    //realm对象,需要自己定义类 1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public AdminRealm adminRealm(){
        return new AdminRealm();
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


}
