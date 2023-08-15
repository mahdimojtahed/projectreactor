package ir.dotin.reactiveprogramming.Backpressure;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class L03_Latest {


    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                        Utils.sleepInMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(20);})
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10);

    }

}
