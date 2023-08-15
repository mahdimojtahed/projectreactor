package ir.dotin.reactiveprogramming.Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class L7_PipelineBuildVsExecution {

    public static void main(String[] args) {

        // this method only builds the pipeline and will take few moments to complete because
        // we are only building the pipeline.
//        getName();
//        getName();
//        getName();

        // this method will take 3 seconds to complete, because we are now subscribing and Executing pipeline
//        getName().subscribe(Utils.onNext());

        // External Tip : what we saw was completely non-async and blocking.
        getName();
        String name = getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block(); // this is blocking the thread
        System.out.println(name);
        getName();
        Utils.sleepInSeconds(4);

    }

    private static Mono<String> getName() {
        System.out.println("Entered GetName method ...");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating Name ...");
            Utils.sleepInSeconds(3);
            return Utils.faker().name().fullName();
        }).map(String::toUpperCase);
    }


}
