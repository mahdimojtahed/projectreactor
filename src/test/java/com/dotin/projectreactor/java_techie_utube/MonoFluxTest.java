package com.dotin.projectreactor.java_techie_utube;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void tryMono() {
        Mono<?> monoString = Mono.just("medev")
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log();
        monoString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }
    @Test
    public void tryFlux() {
        Flux<String> fluxString = Flux.just("mehdi", "mahdie", "hadi")
                .concatWithValues("Mojtaba","Somayeh")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred in flux")))
                .concatWithValues("Hamid")
                .log();
        fluxString.subscribe(System.out::println, e -> System.out.println(e));
    }


}
