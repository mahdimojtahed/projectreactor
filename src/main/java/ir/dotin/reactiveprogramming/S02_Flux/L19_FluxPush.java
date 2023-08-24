package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.S02_Flux.helper.NameProducer;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L19_FluxPush {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.push(nameProducer)
                .subscribe(Utils.subscriber());

        Runnable runnable = nameProducer::produce;
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

    }
}
