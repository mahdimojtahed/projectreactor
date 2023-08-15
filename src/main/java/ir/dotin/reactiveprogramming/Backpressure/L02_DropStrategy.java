package ir.dotin.reactiveprogramming.Backpressure;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.util.ArrayList;
import java.util.List;

public class L02_DropStrategy {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        List<Object> droppedItems = new ArrayList<>();

        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                        Utils.sleepInMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureDrop(droppedItems::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(20);})
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10);
        System.out.println(droppedItems);

    }
}
