package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.Flux.helper.NameProducer;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L15_FluxPush {
    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer)
                .subscribe(Utils.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
            Utils.sleepInSeconds(2);
        }

    }
}
