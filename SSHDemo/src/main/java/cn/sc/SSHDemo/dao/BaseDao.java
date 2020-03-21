package cn.sc.SSHDemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public class BaseDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
