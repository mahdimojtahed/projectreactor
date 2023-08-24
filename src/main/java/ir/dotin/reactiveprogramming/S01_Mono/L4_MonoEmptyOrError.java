package ir.dotin.reactiveprogramming.S01_Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

public class L4_MonoEmptyOrError {

    public static void main(String[] args) {

        userRepository(3)
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );

    }

    private static Mono<String> userRepository(int userID) {
        if (userID == 1) {
            return Mono.just(Utils.faker().name().firstName());
        } else if(userID == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in Range"));
        }
    }
}
