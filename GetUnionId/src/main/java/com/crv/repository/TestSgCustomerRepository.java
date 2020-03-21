package com.crv.repository;

import com.crv.entity.TestSgCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: shenchao
 * @Date: created in 18:53 2018/3/19
 * @Description:
 */
public interface TestSgCustomerRepository extends JpaRepository<TestSgCustomer,Integer>,JpaSpecificationExecutor<TestSgCustomer> {

    @Modifying
    @Query(value="update test_sg_customer set reserve3 =?1 where card_no=?2",nativeQuery = true)
    int updateTestSgCustomerByCardNo(String reserve3,String cardNo);

}
