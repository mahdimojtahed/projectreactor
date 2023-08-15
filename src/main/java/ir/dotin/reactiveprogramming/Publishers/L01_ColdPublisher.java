package ir.dotin.reactiveprogramming.Publishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class L01_ColdPublisher {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2));

        movieStream
                .subscribe(Utils.subscriber("mehdi"));
        Utils.sleepInSeconds(5);

        movieStream
                .subscribe(Utils.subscriber("mojtaba"));
        Utils.sleepInSeconds(60);

    }


    // Acts like Netflix
    private static Stream<String> getMovie() {
        System.out.println("Got The movie streaming request");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7",
                "Scene 8",
                "Scene 9",
                "Scene 10"
        );
    }


}
