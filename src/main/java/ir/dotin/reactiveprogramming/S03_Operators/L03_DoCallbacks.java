package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L03_DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
                    System.out.println("Inside Create");
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
//                    fluxSink.complete();
                    fluxSink.error(new RuntimeException("ooops error happened"));
                    System.out.println("Completed");
                })
                .doFirst(() -> System.out.println("Do first 1  invoked"))
                .doOnSubscribe((s) -> System.out.println("doOnSubscribe : " + s))
                .doOnRequest(l -> System.out.println("Do on request : " + l))
                .doOnNext((o) -> System.out.println("Do on next with value : " + o))
                .doOnComplete(() -> System.out.println("Do on Completed invoked "))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnError(err -> System.out.println("Error : " + err))
                .doOnCancel(() -> System.out.println("Do On Cancel"))
                .doFinally((signal) -> System.out.println("Finally : " + signal))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o ))
                .doFirst(() -> System.out.println("Do first 2 invoked"))
                .take(2) // this will cancel the subscription
                .subscribe(Utils.subscriber());


    }
}
