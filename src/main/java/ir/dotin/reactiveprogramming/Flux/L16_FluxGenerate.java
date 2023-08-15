package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L16_FluxGenerate {

    public static void main(String[] args) {


        // example 1
//        Flux.generate(synchronousSink -> {
//                    synchronousSink.next(Utils.faker().country().name());
//                    synchronousSink.next(Utils.faker().country().name());
//                })
//                .subscribe(Utils.subscriber());

        // example 2
//        Flux.generate(synchronousSink -> {
//                    synchronousSink.next(Utils.faker().country().name());
//                })
//                .subscribe(Utils.subscriber());


        // example 3
        Flux.generate(synchronousSink -> {
                    System.out.println("Emitting :");
                    synchronousSink.next(Utils.faker().country().name());
                })
                .take(4)
                .subscribe(Utils.subscriber());

        // example 4
        Flux.generate(synchronousSink -> {
                    System.out.println("Emitting :");
                    synchronousSink.next(Utils.faker().country().name());
//                    synchronousSink.complete();
                    synchronousSink.error(new RuntimeException("ops"));
                })
                .subscribe(Utils.subscriber());


    }
}
