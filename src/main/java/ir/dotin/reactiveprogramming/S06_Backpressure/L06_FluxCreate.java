package ir.dotin.reactiveprogramming.S06_Backpressure;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class L06_FluxCreate {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                        Utils.sleepInMillis(1);
                    }
                    fluxSink.complete();
                }, FluxSink.OverflowStrategy.ERROR)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(20);})
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10);

    }
}
