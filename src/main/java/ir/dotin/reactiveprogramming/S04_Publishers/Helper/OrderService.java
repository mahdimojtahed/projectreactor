package ir.dotin.reactiveprogramming.S04_Publishers.Helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {
    private Flux<PurchaseOrder> flux;

    // we are doing this so every subscriber can have the same publisher
    // if we directly access getOrderStream method , for each subscriber we are creating the pipeline again
    public Flux<PurchaseOrder> orderStream() {
        if (Objects.isNull(flux))
            flux = getOrderStream();
        return flux;
    }

    private Flux<PurchaseOrder> getOrderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }


}


