
package com.yunji.oa.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunji.oa.dao.IUserDAO;
import com.yunji.oa.domain.User;
import com.yunji.oa.service.IUserService;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Service("UserServiceImpl")
/*public class UserServiceImpl extends AbstractPageService<IUserDAO,User> implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	@Override
	public boolean getEnableDataPerm() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public IUserDAO getDao() {
		return userDAO;
	}

}*/

public class UserServiceImpl  implements IUserService {

	public List<User> findAll() {

		List arr = new ArrayList();
		User user = new User();
		user.setUserName("1");
		arr.add(user);
		return arr;
	};

}
