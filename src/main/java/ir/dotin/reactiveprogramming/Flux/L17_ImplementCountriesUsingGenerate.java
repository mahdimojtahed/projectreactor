package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L17_ImplementCountriesUsingGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String country = Utils.faker().country().name();
            System.out.println("Emitting : " + country);
            synchronousSink.next(country);
            if (country.toLowerCase().equals("canada"))
                synchronousSink.complete();
        }).subscribe(Utils.subscriber());


    }
}
