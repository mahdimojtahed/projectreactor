package ir.dotin.reactiveprogramming.Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

public class L9_MonoFromRunnable {
    public static void main(String[] args) {

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        () -> {
                            System.out.println("Process is Done, Sending Emails .... ");
                        }
                );

    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            Utils.sleepInSeconds(3);
            System.out.println("Operations Is Done.");
        };
    }


}
