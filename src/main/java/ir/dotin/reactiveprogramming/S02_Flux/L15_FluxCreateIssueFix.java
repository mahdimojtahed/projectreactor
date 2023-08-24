package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L15_FluxCreateIssueFix {

    public static void main(String[] args) {

//        Flux.create(fluxSink -> {
//                    String countryName = "";
//                    do {
//                        countryName = Utils.faker().country().name();
//                        System.out.println("Emitting : " + countryName);
//                        fluxSink.next(countryName);
//                    } while (!countryName.toLowerCase().equals("canada"));
//                    fluxSink.complete();
//                })
//                .take(3) // now the subscriber not receiving any more items more than 3 but the publisher keep emitting items to Canada
//                .subscribe(Utils.subscriber());

        // to fix above problem we can make our if statement to use two condition
        // either we reached canada or fluxsink canceled the request
        Flux.create(fluxSink -> {
                    String countryName = "";
                    do {
                        countryName = Utils.faker().country().name();
                        System.out.println("Emitting : " + countryName);
                        fluxSink.next(countryName);
                    } while (!countryName.toLowerCase().equals("canada") && !fluxSink.isCancelled());
                    fluxSink.complete();
                })
                .take(3) // now the subscriber not receiving any more items more than 3 but the publisher keep emitting items to Canada
                .subscribe(Utils.subscriber());


    }
}
