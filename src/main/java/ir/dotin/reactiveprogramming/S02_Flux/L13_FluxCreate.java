package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L13_FluxCreate {
    public static void main(String[] args) {


        Flux.create(fluxSink -> {
                    String countryName = "";
                    int counter = 0;
                    do {
                        countryName = Utils.faker().country().name();
                        fluxSink.next(countryName);
                        counter++;
                    } while (!countryName.toLowerCase().equals("canada") || fluxSink.isCancelled() || counter < 10);
                    fluxSink.complete();
                })
                .subscribe(Utils.subscriber());


    }
}
