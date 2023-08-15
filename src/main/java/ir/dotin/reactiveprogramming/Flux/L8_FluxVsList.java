package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.Flux.helper.NameGenerator;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.List;

public class L8_FluxVsList {

    public static void main(String[] args) {

        // Using List
//        List<String> namesAsList = NameGenerator.getNames(5);
//        System.out.println(namesAsList);


        // Using Flux
        Flux<String> namesAsFlux = NameGenerator.getNames(5, true);
        namesAsFlux.subscribe(Utils.onNext());


    }
}
