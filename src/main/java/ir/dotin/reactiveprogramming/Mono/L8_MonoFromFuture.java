package ir.dotin.reactiveprogramming.Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class L8_MonoFromFuture {
    public static void main(String[] args) {

        Mono.fromFuture(getName())
                .subscribe(Utils.onNext());

        // we should block the thread to see the result
        // because the method getName will execute immediately
        Utils.sleepInSeconds(1);

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Utils.faker().name().fullName());
    }
}
