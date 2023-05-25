package com.dotin.projectreactor.java_techie_utube.handler;

import com.dotin.projectreactor.java_techie_utube.dao.CustomerDao;
import com.dotin.projectreactor.java_techie_utube.domain.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private final CustomerDao customerDao;

    public CustomerStreamHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerDao.getCustomers_Stream(), Customer.class);
    }

}
