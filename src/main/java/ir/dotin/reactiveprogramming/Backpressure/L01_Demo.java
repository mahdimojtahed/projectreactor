package ir.dotin.reactiveprogramming.Backpressure;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L01_Demo {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                    }
                    fluxSink.complete();
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {Utils.sleepInMillis(20);})
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }
}
