package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.Operators.Helper.Person;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class L10_Transform {

    public static void main(String[] args) {

        getPerson()
                .transform(f -> applyFilterMap2(f))
                .transform(applyFilterMap())
                .subscribe(Utils.subscriber());
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()));
    }

    public static Flux<Person> applyFilterMap2(Flux<Person> flux) {
        return flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()));

    }
}
