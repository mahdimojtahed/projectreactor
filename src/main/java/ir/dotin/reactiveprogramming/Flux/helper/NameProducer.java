package ir.dotin.reactiveprogramming.Flux.helper;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        String name = Utils.faker().name().fullName();
        String thread = Thread.currentThread().getName();
        this.fluxSink.next(thread + " : " + name);
    }

}
