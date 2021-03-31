package com.yrc.Filter;

import com.yrc.config.UserToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//FormAuthenticationFilter并重新createToken将我们扩展的Token引入即可，以下为扩展的FormAuthenticationFilter：
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String loginType = request.getParameter("loginType");
        if("User".equals(loginType)){
            return new UserToken(username, password, "User");
        } else {
            return new UserToken(username, password, "Admin");
        }
    }

}
