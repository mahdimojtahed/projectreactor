package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.Operators.Helper.Person;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class L11_SwitchOnFirst {

    public static void main(String[] args) {

        getPerson()
                .switchOnFirst((signal, personFlux) ->
                        signal.isOnNext() && signal.get().getAge() > 15 // if the first item is greater than 15 return the flux itself otherwise apply the filter
                                ? personFlux
                                : applyFilterMap().apply(personFlux)
//                                : applyFilterMap2(personFlux)
                )
                .subscribe(Utils.subscriber());
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Not Allowed : " + p));
    }

    public static Flux<Person> applyFilterMap2(Flux<Person> flux) {
        return flux
                .filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()));

    }
}
