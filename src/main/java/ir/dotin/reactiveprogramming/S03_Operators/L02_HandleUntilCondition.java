package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L02_HandleUntilCondition {

    public static void main(String[] args) {


        Flux.generate(sink -> {
                    sink.next(Utils.faker().country().name());
                })
                .map(Object::toString)
                .handle((string, sink) -> {
                    sink.next(string);
                    if (string.toLowerCase().equals("canada"))
                        sink.complete();
                }).subscribe(Utils.subscriber());


    }
}
