package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L02_Concat {
    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d", "e");
        Flux<String> flux3 = Flux.just("f","g");
        Flux<String> flux4 = Flux.error(new RuntimeException("error"));

        flux1.concatWith(flux2)
                .subscribe(Utils.subscriber());

        Flux.concat(flux1,flux2,flux3)
                .subscribe(Utils.subscriber());

        Flux.concat(flux1, flux2, flux4)
                .subscribe(Utils.subscriber());

        Flux.concat(flux1, flux4, flux2)
                .subscribe(Utils.subscriber()); // it will not getting data since error happens


        Flux.concatDelayError(flux1, flux4, flux3)
                .subscribe(Utils.subscriber());

    }
}
