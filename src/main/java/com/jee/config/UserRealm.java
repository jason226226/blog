package com.jee.config;

import com.jee.pojo.User;
import com.jee.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进行了授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行了认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.checkUser(userToken.getUsername());
        if (null==user){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        System.out.println(user.getNickname());
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
