package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper.ArmanBookStore;
import ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper.RoshdBookStore;
import ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper.SharghBookStore;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L03_Merge {

    public static void main(String[] args) {

        Flux.merge(ArmanBookStore.getBooks(),
                        RoshdBookStore.getBooks(),
                        SharghBookStore.getBooks())
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10);
    }

}
