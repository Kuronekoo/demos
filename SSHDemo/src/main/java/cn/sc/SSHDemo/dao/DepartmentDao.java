package cn.sc.SSHDemo.dao;

import cn.sc.SSHDemo.beans.DepartmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao extends BaseDao {

    public List<DepartmentEntity> getAll(){
        String hql = "FROM DepartmentEntity";
        return getSession().createQuery(hql).list();
    }
}
