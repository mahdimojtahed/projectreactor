package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class L06_SinkMultiDirectAll {
    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        //  because second subscriber is more slow than first, it will affact the first sub
        // performance. to avoid this behaviour we can use directBestEffort() so no first sub
        // receives all the items then second one starts getting.

//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

        Flux<Object> objectFlux = sink.asFlux();

        objectFlux.subscribe(Utils.subscriber("mehdi"));
        objectFlux.delayElements(Duration.ofMillis(200)).subscribe(Utils.subscriber("hadi"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Utils.sleepInSeconds(10);

    }
}
