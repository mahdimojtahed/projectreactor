package ir.dotin.reactiveprogramming.S09_RepeatAndRetry;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class L04_RetryWhen {

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getNumbers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(50);
    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 3)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> i / (Utils.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error"));
    }
}
