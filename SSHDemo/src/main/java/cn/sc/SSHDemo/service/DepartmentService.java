package cn.sc.SSHDemo.service;

import cn.sc.SSHDemo.beans.DepartmentEntity;
import cn.sc.SSHDemo.dao.DepartmentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DepartmentService {

    @Resource(name = "departmentDao")
    DepartmentDao departmentDao;

    public List<DepartmentEntity> getAll(){
        return departmentDao.getAll();
    }
}
