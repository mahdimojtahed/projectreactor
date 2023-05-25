package com.dotin.projectreactor.java_techie_utube.handler;

import com.dotin.projectreactor.java_techie_utube.dao.CustomerDao;
import com.dotin.projectreactor.java_techie_utube.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private final CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        return ServerResponse.ok()
                .body(customerDao.getCustomers_Reactive(), Customer.class);
    }

    public Mono<ServerResponse> loadCustomerById(ServerRequest request) {
        int customerID = Integer.valueOf(request.pathVariable("id"));
//        Mono<Customer> customerMono = customerDao.getCustomers_Stream()
//        .filter(c -> c.getId() == customerID).take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomers_Stream()
                .filter(c -> c.getId() == customerID).next();
        return ServerResponse
                .ok()
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {

        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId()+ ":" + dto.getName());

        return ServerResponse
                .ok()
                .body(saveResponse, String.class);
    }

}
