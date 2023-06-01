package com.dotin.projectreactor.dotin_class.publishers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisher {

    private static int getData() {
        System.out.println("Get Data method called");
        return 1;
    }

    private static Stream<String> getMovie() {
        System.out.println("Movie Stream request received.");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5"
        );
    }

    public static void main(String[] args) throws InterruptedException {

        // this method will not print any data because there is no observer for it.
        // we need at least one observer
        Mono.fromSupplier(ColdPublisher::getData);

        // this method print data because there is one observer to it.
        Mono.fromSupplier(ColdPublisher::getData)
                .subscribe(i -> System.out.println("Method Invoked - " + i));


        Flux<String> netFlux = Flux.fromStream(ColdPublisher::getMovie)
                .delayElements(Duration.ofSeconds(2));

        netFlux.subscribe(scene -> System.out.println("You are watching " + scene));
        Thread.sleep(5000);
        netFlux.subscribe(scene -> System.out.println("Mahi is watching " + scene));
        Thread.sleep(10000);

    }
}
