package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L1_FluxIntro {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, 4)
                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

        Flux.empty()
                .subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

        Flux<Object> fLux = Flux.just(1, true, "Hello", Utils.faker().name().fullName());
        fLux.subscribe(Utils.onNext(),Utils.onError());
    }

}
