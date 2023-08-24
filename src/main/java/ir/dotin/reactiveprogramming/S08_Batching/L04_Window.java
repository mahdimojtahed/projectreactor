package ir.dotin.reactiveprogramming.S08_Batching;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class L04_Window {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {

        eventStream()
                .window(5) // this will give a flux to downstream
//                .map(L04_Window::saveEvents)
                .flatMap(L04_Window::saveEvents) // we should use flat map because we still getting a flux even after processing
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event" + i);
    }

    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(i -> System.out.println("saving: " + i))
                .doOnComplete(() -> {
                    System.out.println("saved this batch");
                    System.out.println("/////////////////");
                })
                .then(Mono.just(atomicInteger.getAndIncrement()));
    }
}
