package com.fengfeng.home;

import com.fengfeng.users.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Bear on 15/6/30.
 */
public class Home {

    private String q;


    private List list;
    private String message;

    public String execute() throws Exception {
        if(q != null && !q.isEmpty()) {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            try {
                //from后面是对象，不是表名
                String hql = "from UsersEntity as user where user.username like :username";//使用命名参数，推荐使用，易读。
                Query query = session.createQuery(hql);
                query.setString("username", "%"+q+"%");

                this.setList(query.list());
            } finally {
                if (session != null)
                    session.close();
            }
            if (list.size() > 0) {
                message = "搜索到" + list.size() + "条结果";
            } else {
                message = "没有搜索到结果";
            }
        } else {
            message = "请输入关键字";
        }
        return "success";
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}