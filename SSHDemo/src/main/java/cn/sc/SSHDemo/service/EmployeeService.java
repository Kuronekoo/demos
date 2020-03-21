package cn.sc.SSHDemo.service;

import cn.sc.SSHDemo.beans.EmployeeEntity;
import cn.sc.SSHDemo.dao.EmployeeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {
    @Resource(name="employeeDao")
    private EmployeeDao employeeDao;

public EmployeeEntity getById(Integer id) {
    return employeeDao.getById(id);
    }
    public List<EmployeeEntity> getAll(){
        return  employeeDao.getAll();
    }

    public void deleteById(Integer id){
        employeeDao.deleteById(id);
    }

    public void saveOrUpdate(EmployeeEntity employeeEntity){
        employeeDao.saveOrUpdate(employeeEntity);
    }

    public boolean  getByEmployeeName (String name){
        return employeeDao.getByEmployeeName(name) == null;
    }
}
