package ir.dotin.reactiveprogramming.Publishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class L04_PublishResubscribe {
    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        movieStream
                .subscribe(Utils.subscriber("mehdi"));
        Utils.sleepInSeconds(10);

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
