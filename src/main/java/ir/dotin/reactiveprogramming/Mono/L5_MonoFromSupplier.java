package ir.dotin.reactiveprogramming.Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

public class L5_MonoFromSupplier {
    public static void main(String[] args) {

//        Mono<String> just = Mono.just(getName());

//        Mono<String> fromSupplier = Mono.fromSupplier(() -> getName()); // it's not doing anything unless someone subscribe to it

        Mono<String> fromSupplier = Mono.fromSupplier(() -> getName());
        fromSupplier.subscribe(
                Utils.onNext()
        );


    }

    private static String getName() {
        System.out.println("Generating name..");
        return Utils.faker().name().fullName();
    }
}
