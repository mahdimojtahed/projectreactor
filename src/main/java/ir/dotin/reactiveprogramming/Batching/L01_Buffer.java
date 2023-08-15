package ir.dotin.reactiveprogramming.Batching;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L01_Buffer {

    public static void main(String[] args) {

        eventStream()
//                .buffer(5)
//                .buffer(Duration.ofSeconds(2))
                .bufferTimeout(7, Duration.ofSeconds(3))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }
}
