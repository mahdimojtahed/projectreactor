package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L04_LimitRate {

    public static void main(String[] args) {


        Flux.range(0, 100)
                .log()
                .doOnRequest((l) -> System.out.println("Request Sent : " + l))
                .limitRate(10,9)
                .subscribe(Utils.subscriber());


    }
}
