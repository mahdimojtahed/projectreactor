package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper.NameGenerator;
import ir.dotin.reactiveprogramming.util.Utils;

public class L01_StartsWith {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Utils.subscriber("subscriber1 "));

        generator.generateNames()
                .take(5)
                .subscribe(Utils.subscriber("subscriber2 "));

        generator.generateNames()
                .filter(n -> n.startsWith("A"))
                .take(1)
                .subscribe(Utils.subscriber("subscriber2 "));




    }
}
