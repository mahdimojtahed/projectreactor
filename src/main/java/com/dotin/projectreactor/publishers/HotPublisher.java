package com.dotin.projectreactor.publishers;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisher {

    private static Stream<String> getMovie() {
        System.out.println("::: Subscription Started :::");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5"
        );

    }

    public static void main(String[] args) throws InterruptedException {

        Flux<String> movieTheater = Flux.fromStream(HotPublisher::getMovie)
                .delayElements(Duration.ofSeconds(2)).share();

//        movieTheater.subscribe(scene -> System.out.println("Mehdi is watching scene " + scene));
//        Thread.sleep(5000);
//        movieTheater.subscribe(scene -> System.out.println("Mahdie is watching scene " + scene));
//        Thread.sleep(5000);

//        Consider this example using share method. But here when the second subscriber joins,
//        the source has already emitted data & completed.
//        So the second subscription repeats the emission process.
//        Imagine this like a same movie theater example with subsequent show once a first show is completed.

//        movieTheater.subscribe(scene -> System.out.println("Mehdi is watching scene " + scene));
//        Thread.sleep(12000);
//        movieTheater.subscribe(scene -> System.out.println("Mahdie is watching scene " + scene));
//        Thread.sleep(10000);

        Flux<String> movieTheater2 = Flux.fromStream(HotPublisher::getMovie)
                .delayElements(Duration.ofSeconds(2)).cache();

        movieTheater2.subscribe(scene -> System.out.println("Mehdi is watching scene " + scene));
        Thread.sleep(12000);
        movieTheater2.subscribe(scene -> System.out.println("Mahdie is watching scene " + scene));
        Thread.sleep(10000);

        // if we dont want cache we can use cache(0)
        Flux<String> movieTheater3 = Flux.fromStream(HotPublisher::getMovie)
                .delayElements(Duration.ofSeconds(2)).cache(0);

        movieTheater3.subscribe(scene -> System.out.println("Mehdi is watching scene " + scene));
        Thread.sleep(12000);
        movieTheater3.subscribe(scene -> System.out.println("Mahdie is watching scene " + scene));
        Thread.sleep(10000);



    }
}
