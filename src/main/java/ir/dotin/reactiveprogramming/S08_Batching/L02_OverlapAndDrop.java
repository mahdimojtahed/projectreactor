package ir.dotin.reactiveprogramming.S08_Batching;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L02_OverlapAndDrop {
    public static void main(String[] args) {

        eventStream()
                .buffer(3,1) // overlap
//                .buffer(3,5) // drop
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }
}
