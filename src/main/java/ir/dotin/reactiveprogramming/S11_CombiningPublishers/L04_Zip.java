package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L04_Zip {

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires())
                .subscribe(Utils.subscriber());

    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body " + i);
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(i -> "engine " + i);
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6)
                .map(i -> "tires " + i);
    }


}
