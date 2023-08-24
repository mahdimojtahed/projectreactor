package ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private List<String> list = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(stringSynchronousSink -> {
                    System.out.println("Generated name");
                    Utils.sleepInSeconds(2);
                    String name = Utils.faker().name().firstName();
                    list.add(name);
                    stringSynchronousSink.next(name);
                })
                .cast(String.class)
                .startWith(getFromCache());
    }


    public Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }

}
