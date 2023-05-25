package com.dotin.projectreactor.java_techie_utube.service;

import com.dotin.projectreactor.java_techie_utube.dao.CustomerDao;
import com.dotin.projectreactor.java_techie_utube.domain.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> loadAllCustomers() {
        Long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        Long end = System.currentTimeMillis();
        System.out.println("Time Difference" + (start - end));
        return customers;
    }
    public Flux<Customer> loadAllCustomers_Stream() {
        Long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomers_Stream();
        Long end = System.currentTimeMillis();
        System.out.println("Time Difference in Stream " + (start - end));
        return customers;
    }
}
