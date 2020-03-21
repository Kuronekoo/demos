package cn.sc.SSHDemo.dao;

import cn.sc.SSHDemo.beans.EmployeeEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class EmployeeDao extends BaseDao{


    public EmployeeEntity getById(Integer id){
        String hql ="FROM EmployeeEntity e WHERE e.id = ?";
        Query query = getSession().createQuery(hql).setInteger(0, id);
        return (EmployeeEntity) query.uniqueResult();
    }

    public List<EmployeeEntity> getAll() {
        String hql = "FROM EmployeeEntity e LEFT OUTER JOIN FETCH e.departmentEntity";
        return getSession().createQuery(hql).list();
    }

    public void deleteById(Integer id) {
        String hql = "DELETE FROM EmployeeEntity e WHERE e.id = ?";
        getSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }


    public void saveOrUpdate(EmployeeEntity employeeEntity){
        getSession().saveOrUpdate(employeeEntity);
    }

    public EmployeeEntity getByEmployeeName(String name){
        String hql = "FROM EmployeeEntity e WHERE e.name = ?";
        Query query = getSession().createQuery(hql).setString(0, name);
        return (EmployeeEntity) query.uniqueResult();

    }
}
