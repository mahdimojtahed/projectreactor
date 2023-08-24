package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L05_Delay {

    public static void main(String[] args) {

        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }
}
