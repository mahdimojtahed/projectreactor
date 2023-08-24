package ir.dotin.reactiveprogramming.S12_Sinks;


import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class L02_SinksUniCast {
    public static void main(String[] args) {

        // handle through which we would push items.
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        Flux<Object> objectFlux = sink.asFlux();
        objectFlux.subscribe(Utils.subscriber("mehdi"));
        objectFlux.subscribe(Utils.subscriber("somayeh")); // only one sub in unicast

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how r u ");
        sink.tryEmitNext("?");

        sink.tryEmitComplete();

    }
}
