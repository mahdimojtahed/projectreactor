package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L9_FluxInterval {
    public static void main(String[] args) {

        Flux.interval(Duration.ofSeconds(5)) // we can not see anything because it's not blocking so we need to sleep thread
                .subscribe(Utils.onNext());

        Utils.sleepInSeconds(5);

    }
}
