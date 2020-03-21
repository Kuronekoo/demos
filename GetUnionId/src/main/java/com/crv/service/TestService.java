package com.crv.service;

import com.crv.entity.TestSgCustomer;
import com.crv.repository.TestSgCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: shenchao
 * @Date: created in 20:27 2018/3/19
 * @Description:
 */
@Service
public class TestService {
    @Autowired
    private TestSgCustomerRepository testSgCustomerRepository;

    @Transactional(readOnly = false)
    public int update(String reserve3,String cardNo){
        return testSgCustomerRepository.updateTestSgCustomerByCardNo(reserve3,cardNo);
    }
    
    
    public void save (TestSgCustomer testSgCustomer){
        testSgCustomerRepository.save(testSgCustomer);
    }

    public TestSgCustomer get(int id){
        TestSgCustomer one = testSgCustomerRepository.getOne(id);
        return one;
    }
}
