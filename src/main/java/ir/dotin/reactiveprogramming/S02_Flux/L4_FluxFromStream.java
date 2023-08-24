package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class L4_FluxFromStream {

    public static void main(String[] args) {

        List<Integer> integers = List.of(1, 2, 3, 5, 6);
        Stream<Integer> stream = integers.stream();

        // we can not use a stream twice.
//        stream.forEach(System.out::println); // closed.
//        stream.forEach(System.out::println);

//        Flux.fromStream(stream)
//                .subscribe(Utils.onNext(),
//                        Utils.onError(),
//                        Utils.onComplete());

//        Flux.fromStream(stream)
//                .subscribe(Utils.onNext(),
//                        Utils.onError(),
//                        Utils.onComplete());


        Flux<Integer> integersFlux = Flux.fromStream(() -> integers.stream());

        integersFlux.subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());
        integersFlux.subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());



    }
}
