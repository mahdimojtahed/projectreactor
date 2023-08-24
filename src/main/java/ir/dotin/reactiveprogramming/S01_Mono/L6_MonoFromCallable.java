package ir.dotin.reactiveprogramming.S01_Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class L6_MonoFromCallable {
    public static void main(String[] args) {

        Callable<String> stringCallable = () -> getName();
        Mono<String> mono = Mono.fromCallable(stringCallable);
        mono.subscribe(
                Utils.onNext()
        );

    }

    private static String getName() {
        return Utils.faker().name().fullName();
    }
}
