package ir.dotin.reactiveprogramming.Flux.helper;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {


    public static List<String> getNames(int count) {
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }

    public static Flux<String> getNames(int count, Boolean flux) {
        return Flux.range(0, count)
                .map(i -> getName());
    }

    private static String getName() {
        Utils.sleepInSeconds(1);
        return Utils.faker().name().fullName();
    }

}
