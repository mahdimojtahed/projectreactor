package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L01_Handle {
    public static void main(String[] args) {

        // handle = filter + map

        Flux.range(1, 20)
                .handle((integer, sink) -> {
//                    if (integer == 7) { // filter
//                        sink.complete();
//                    }
                    if (integer % 2 == 0) { // filter
                        sink.next(integer);
                    } else {
                        sink.next(integer + " a "); // map
                    }
                }).subscribe(Utils.subscriber());


    }
}
