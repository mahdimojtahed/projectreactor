package com.dotin.projectreactor.java_techie_utube.dao;

import com.dotin.projectreactor.java_techie_utube.domain.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.dotin.projectreactor.dotin_class.util.CommonUtil.delay;

@Component
public class CustomerDao {
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek((i) -> delay(1000))
                .peek(i -> System.out.println("Processing Count " + i))
                .mapToObj(i -> new Customer(i, "Customer " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomers_Stream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing Count in Stream " + i))
                .map(i -> new Customer(i, "Customer " + i));
    }
    public Flux<Customer> getCustomers_Reactive() {
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("Processing Count in Reactive " + i))
                .map(i -> new Customer(i, "Customer " + i));
    }

}
