
package com.yunji.oa.service;
import com.yunji.oa.dao.IUserDAO;
import com.yunji.oa.domain.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();
}