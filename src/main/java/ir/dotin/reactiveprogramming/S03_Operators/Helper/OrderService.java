package ir.dotin.reactiveprogramming.S03_Operators.Helper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> list = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
        );
        List<PurchaseOrder> list2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        List<PurchaseOrder> list3 = Arrays.asList(
                new PurchaseOrder(3),
                new PurchaseOrder(3),
                new PurchaseOrder(3)
        );
        db.put(1, list);
        db.put(2, list2);
        db.put(3, list3);
    }

    public static Flux<PurchaseOrder> getOrders(int id) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
           db.get(id).forEach(purchaseOrderFluxSink::next);
           purchaseOrderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }


}


