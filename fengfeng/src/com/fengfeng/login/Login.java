package com.fengfeng.login;

/**
 * Created by lihongxun on 15/6/30.
 */
import com.fengfeng.users.UsersEntity;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Login implements SessionAware {

    private String username;
    private String password;
    private String message = "";
    private  java.util.Map session;

    public String execute() throws Exception {
        Configuration conf = new Configuration().configure();
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<UsersEntity> list;
        try{
            //from后面是对象，不是表名
            String hql="from UsersEntity as user where user.username=:username and user.password=:password";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setString("username", username);
            query.setString("password", password);

            list=query.list();

            for(UsersEntity user:list){
                System.out.println(user.getUsername());
            }
        }finally{
            if(session!=null)
                session.close();
        }
        if(list.size() > 0) {
            message = "登录成功:"+ list.get(0).getUsername();
            this.session.put("loginUser", list.get(0));
            return "success";
        } else {
            message = "用户名或者密码错误";
        }
        return "error";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return this.message;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void setSession(java.util.Map<String, Object> map) {
        this.session = map;
    }
}
