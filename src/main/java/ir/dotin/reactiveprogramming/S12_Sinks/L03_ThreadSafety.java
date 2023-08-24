package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class L03_ThreadSafety {

    public static void main(String[] args) {


        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = objectMany.asFlux();
        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

//        for (int i = 0; i < 1000; i++) {
//            final int j = i;
//            CompletableFuture.runAsync(() -> {
//               objectMany.tryEmitNext(j);
//            });
//        }

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> objectMany.emitNext(j, (s, r) -> true));
        }

        Utils.sleepInSeconds(3);
        System.out.println(list.size());


    }
}
