package ir.dotin.reactiveprogramming.S08_Batching;


import ir.dotin.reactiveprogramming.S08_Batching.assignment.OrderService;
import ir.dotin.reactiveprogramming.S08_Batching.assignment.PurchaseOrder;
import ir.dotin.reactiveprogramming.assignment.OrderProcessor;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class L06_Assignment {

    public static void main(String[] args) {

        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessor.kidsProcessing(),
                "Automotive", OrderProcessor.automotiveProcessor()
        );


        Set<String> strings = map.keySet();
        OrderService.orderStream()
                .filter(p -> strings.contains(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory) // group of flux with 2 keys
                .flatMap(gf -> map.get(gf.key()).apply(gf))
                .subscribe(Utils.subscriber());


        Utils.sleepInSeconds(60);

    }
}
