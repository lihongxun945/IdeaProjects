package com.fengfeng.interceptor;

import com.fengfeng.login.Login;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by lihongxun on 15/7/4.
 */
public class LoginInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation a) throws Exception {
        // 判断是否请求为登录界面(login),如果是则不拦截
        if (Login.class == a.getAction().getClass())
            return a.invoke();

        // 如果是请求其他页面，进行拦截
        Map map = a.getInvocationContext().getSession();
        if (null == map.get("loginUser"))
            return "login";

        return a.invoke();
    }
}
