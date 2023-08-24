package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class L04_SinksMultiCast {

    public static void main(String[] args) {

        // handle through which we would push items.
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        Flux<Object> objectFlux = sink.asFlux();
        objectFlux.subscribe(Utils.subscriber("mehdi"));
        objectFlux.subscribe(Utils.subscriber("somayeh")); // multiple sub in multiCast

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how r u ");
        sink.tryEmitNext("?");

        sink.tryEmitComplete();

    }
}
