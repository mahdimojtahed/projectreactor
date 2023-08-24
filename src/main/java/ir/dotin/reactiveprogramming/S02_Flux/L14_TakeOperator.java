package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L14_TakeOperator {

    public static void main(String[] args) {


        Flux.range(1, 10)
                .log()
                .take(3) // after 3 onNext calls, this will cancel the subscription
                .log()
                .subscribe(Utils.subscriber());


    }
}
