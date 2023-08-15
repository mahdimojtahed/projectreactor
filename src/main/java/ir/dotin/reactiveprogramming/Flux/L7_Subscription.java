package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import javax.sound.midi.Soundbank;
import java.util.concurrent.atomic.AtomicReference;

public class L7_Subscription {
    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("Received sub: " + s);
                        atomicReference.set(s);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });


        Utils.sleepInSeconds(3);
        atomicReference.get().request(3);
        Utils.sleepInSeconds(5);
        atomicReference.get().request(3);
        Utils.sleepInSeconds(5);
        atomicReference.get().cancel();
        Utils.sleepInSeconds(3);
        atomicReference.get().request(4);

    }
}
