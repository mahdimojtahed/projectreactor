package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class L01_SinkOne {

    public static void main(String[] args) {

        // mono -> value / empty / error
        Sinks.One<Object> sinkOne = Sinks.one();
        Mono<Object> objectMono = sinkOne.asMono();
        objectMono.subscribe(Utils.subscriber("mehdi"));

//        sinkOne.tryEmitEmpty();
//        sinkOne.tryEmitValue("value");
//        sinkOne.tryEmitError(new RuntimeException("ooops"));

//        sinkOne.emitValue("hi", ((signalType, emitResult) -> {
//            System.out.println(signalType.name());
//            System.out.println(emitResult.name());
//            return false;
//        }));

//        // this will Cause an error ( mono emitts only one ) then failureHndler will be called
//        sinkOne.emitValue("hello", ((signalType, emitResult) -> {
//            System.out.println(signalType.name());
//            System.out.println(emitResult.name());
////            return false;
//            return true; // will cause an infinite loop because it tries again and again
//        }));


        // Multiple Subscribers
        objectMono.subscribe(Utils.subscriber("mojtaba"));
        sinkOne.tryEmitValue("hi");
    }

}
