package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class L07_SinksReplay {
    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many().replay().all();

        Flux<Object> objectFlux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how r u");

        objectFlux.subscribe(Utils.subscriber("mehdi"));
        objectFlux.subscribe(Utils.subscriber("hadi"));
        sink.tryEmitNext("?");
        objectFlux.subscribe(Utils.subscriber("afsoon"));


        Utils.sleepInSeconds(10);

    }
}
