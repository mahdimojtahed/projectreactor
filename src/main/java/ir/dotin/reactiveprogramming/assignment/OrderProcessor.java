package ir.dotin.reactiveprogramming.assignment;

import ir.dotin.reactiveprogramming.S08_Batching.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class OrderProcessor {
    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessor() {
        return flux -> flux
                .doOnNext(p -> p.setPrice(1.1 * p.getPrice()))
                .doOnNext(p -> p.setItem("{{ " + p.getItem() + " }}"));
    }

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .doOnNext(p -> p.setPrice(.5 * p.getPrice()))
                .flatMap(p -> Flux.just(p, getFreeKidsOrder()));
    }

    private static PurchaseOrder getFreeKidsOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setItem("Free - " + purchaseOrder.getItem());
        purchaseOrder.setPrice(0.0);
        purchaseOrder.setCategory("Kids");
        return purchaseOrder;
    }


}
