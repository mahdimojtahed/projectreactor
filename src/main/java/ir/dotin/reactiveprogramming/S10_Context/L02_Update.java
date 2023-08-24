package ir.dotin.reactiveprogramming.S10_Context;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class L02_Update {

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
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
