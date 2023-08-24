package ir.dotin.reactiveprogramming.S09_RepeatAndRetry;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class L05_RetryWhenAdvance {

    public static void main(String[] args) {

        orderService(Utils.faker().business().creditCardNumber())
                .doOnError(err -> System.out.println(err.getMessage()))
                .retryWhen(Retry.from(
                        retrySignalFlux -> retrySignalFlux
                                .doOnNext(retrySignal -> {
                                    System.out.println(retrySignal.totalRetries());
                                    System.out.println("error code: " + retrySignal.failure());
                                })
                                .handle((retrySignal, synchronousSink) -> {
                                    if (retrySignal.failure().getMessage().equals("500"))
                                        synchronousSink.next(1); // if we emit a value it will be considered as a signal to retry
                                    else synchronousSink.error(retrySignal.failure());
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(50);
    }

    private static void processPayment(String ccNumber) {
        int random = Utils.faker().random().nextInt(1, 10);
        if (random < 8)
            throw new RuntimeException("500"); // means server error, we want to retry in this situation
        else if (random < 10)
            throw new RuntimeException("404"); // our error, we don't want to retry here
    }

    private static Mono<String> orderService(String ccNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Utils.faker().idNumber().valid();
        });
    }


}

