package com.lx.eims.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.contract.Customer;
import com.lx.eims.mapper.constract.CustomerMapper;
import com.lx.eims.service.CustomerService;
import org.springframework.stereotype.Service;
/**
 * @author: lixing
 * date: 2019-04-10
 * time: 0:01
 * description:客户业务
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
