package ir.dotin.reactiveprogramming.S10_Context;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class L01_ContextDemo {
    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(Context.of("users","Mojtaba afsane"))
                .contextWrite(Context.of("user","somaye"))
                .contextWrite(Context.of("user","mehdi"))
                .subscribe(Utils.subscriber());

    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")){
                return Mono.just("welcome " + ctx.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }
}
