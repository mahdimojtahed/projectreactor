package ir.dotin.reactiveprogramming.S04_Publishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class L05_AutoConnect {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(L05_AutoConnect::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(1);
//                .autoConnect(0);

        // we don't need the subscribers to start listening . try to use 0 minimum subscriber and sleep the thread
        // before any subscriber join :
        Utils.sleepInSeconds(3);

        movieStream
                .subscribe(Utils.subscriber("mehdi"));
//        Utils.sleepInSeconds(5); // try this
        Utils.sleepInSeconds(10);

        System.out.println("Mojtaba is about to join ... ");

        movieStream
                .subscribe(Utils.subscriber("mojtaba"));
        Utils.sleepInSeconds(60);

    }


    // Acts like Movie Theater
    private static Stream<String> getMovie() {
        System.out.println("Got The movie streaming request");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }


}
