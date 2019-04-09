package com.lx.eims.entity.contract;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
/**
 * @author: lixing
 * date: 2019-04-08
 * time: 21:00
 * description:客户实体类
 */
@TableName("customer_info")
@Setter
@Getter
public class Customer {
    /**
     *客户ID
     */
    private Integer customerId;
    /**
     * 客户单位
     */
    private String customerUnit;
    /**
     * 客户地址
     */
    private String customerAddress;
    /**
     * 客户邮件
     */
    private String customerEmail;
    /**
     * 客户联系电话
     */
    private String customerPhone;
    /**
     * 客户单位简介
     */
    private String customerProfile;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerUnit='" + customerUnit + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerProfile='" + customerProfile + '\'' +
                '}';
    }
}
