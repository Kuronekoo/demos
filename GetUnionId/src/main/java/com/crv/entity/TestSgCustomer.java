package com.crv.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: shenchao
 * @Date: created in 18:50 2018/3/19
 * @Description:
 */
@Entity
@Table(name="test_sg_customer")
public class TestSgCustomer {
    @Id
    private Integer id;
    private String cardNo;
    private String reserve3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
}
