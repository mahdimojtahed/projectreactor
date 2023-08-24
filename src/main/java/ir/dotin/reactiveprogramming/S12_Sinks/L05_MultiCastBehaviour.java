package ir.dotin.reactiveprogramming.S12_Sinks;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class L05_MultiCastBehaviour {

    public static void main(String[] args) {

        // handle through which we would push items.
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        // handle through which subscribers will receive items
        Flux<Object> objectFlux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how r u ");

        // why is this happening ?
        objectFlux.subscribe(Utils.subscriber("mehdi"));
        objectFlux.subscribe(Utils.subscriber("somayeh")); // only receives ?
        sink.tryEmitNext("?");
        objectFlux.subscribe(Utils.subscriber("mojtaba"));
        sink.tryEmitNext("new msg");

        // when we emmit items and there is no subscribers, the items will be stored in queue
        // and from when some subscribers joins, items will be passed to fist subscriber
        // so new subscribers would not receive older items and only get the items that emitted after
        // joining the pipeline.

        // for ignoring ites when there is no subscriber we can use directAllOrNothing();
    }
}
