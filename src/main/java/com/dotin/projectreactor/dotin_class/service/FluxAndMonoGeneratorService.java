package com.dotin.projectreactor.dotin_class.service;

import com.dotin.projectreactor.dotin_class.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static reactor.core.publisher.Mono.delay;

@Slf4j
public class FluxAndMonoGeneratorService {

    public static void main(String[] args) {
//        namesFlux().subscribe(i -> System.out.println("Name " + i));
//        nameMono().subscribe(i -> System.out.println("Name " + i));
//        namesFlux_map(3).subscribe();
//        namesFlux_immutability().subscribe(i -> System.out.println(i));
//        namesMono_map_filter(3).subscribe(System.out::println);
//        namesMono_flatMap(3).subscribe();
//        namesMono_flatMapMany(3).subscribe();
//        namesFlux_flatmap(3).subscribe();
//        namesFlux_transform(7).subscribe();
//        namesFlux_transform_switchIfEmpty(3).subscribe();
//        explore_concat().subscribe(System.out::println);
//        explore_concatWith().subscribe(System.out::println);
//        explore_merge().subscribe(System.out::println);
//        explore_mergeWith().subscribe(System.out::println);
//        explore_zip().subscribe(System.out::println);
//        explore_zip_1().subscribe(System.out::println);
//        exception_flux().subscribe();
//        explore_OnErrorReturn().subscribe();
//        explore_OnErrorResume(new IllegalStateException()).subscribe();
//        explore_OnErrorResume(new NullPointerException()).subscribe();
//        explore_OnErrorContinue().subscribe();
//        explore_OnErrorMap(new NullPointerException()).subscribe();
//        explore_doOnError().subscribe();
        explore_Mono_OnErrorReturn().subscribe();


    }

    public static Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Mehdi", "Mahdie", "Mojtaba"));
    }

    public static Mono<String> nameMono() {
        return Mono.just("alex");
    }

    public static Flux<String> namesFlux_map(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + "-" + s)
                .doOnNext(name -> {
                    System.out.println("Name is : " + name);
                    name.toLowerCase();
                })
                .doOnSubscribe(s -> {
                    System.out.println("Subscription is : " + s);
                })
                .doOnComplete(() -> {
                    System.out.println("Inside the complete callback");
                })
                .doFinally(signalType -> {
                    System.out.println("inside doFinally : " + signalType);
                })
                .log(); // db or a remote service call
    }

    public static Flux<String> namesFlux_immutability() {
        // flux and mono is immutable, it means once it instantiated it cannot
        // be modified
        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public static Mono<String> namesMono_map_filter(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);
    }

    public static Mono<List<String>> namesMono_flatMap(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(FluxAndMonoGeneratorService::splitStringMono)
                .log();
    }

    private static Mono<List<String>> splitStringMono(String name) {
        var charArray = name.split("");
        var charList = List.of(charArray); //ALEX -> A, L, E, X
        return Mono.just(charList);
    }

    public static Flux<String> namesMono_flatMapMany(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(FluxAndMonoGeneratorService::splitString)
                .log();  //Mono<List of A, L, E  X>
    }

    public static Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public static Flux<String> namesFlux_flatmap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(FluxAndMonoGeneratorService::splitString) // A,L,E,X,C,H,L,O,E
                .log();
    }

    // TODO: 5/16/2023 ASK what is this
    public static Flux<String> namesFlux_flatmap_async(int stringLength) {
        Flux<String> test1 = Flux.fromArray(new String[]{"test"});
        Flux<String> test2 = Flux.fromArray(new String[]{"test"});
        test1.mergeWith(test2);
        Flux.merge(test1, test2);
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapSequential(FluxAndMonoGeneratorService::splitString_withDelay)
                .log();
    }

    public Flux<String> namesFlux_concatmap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(FluxAndMonoGeneratorService::splitString_withDelay)
                .log();
    }

    public static Flux<String> splitString_withDelay(String name) {
        var charArray = name.split("");
        var delay = 1000;

        Flux<String> stringFlux = Flux.fromArray(charArray);
        return stringFlux
                .delayElements(Duration.ofMillis(delay));
    }

    public static Flux<String> namesFlux_transform(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(FluxAndMonoGeneratorService::splitString)
                .defaultIfEmpty("default")
                .log();
    }

    public static Flux<String> namesFlux_transform_switchIfEmpty(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name ->
                name.map(String::toUpperCase)
                        .filter(s -> s.length() > stringLength)
                        .flatMap(FluxAndMonoGeneratorService::splitString);

        var defaultFlux = Flux.fromIterable(List.of("elm1", "elm2"))
                .transform(filterMap);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .filter(x -> x.length() > 7)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public static Flux<String> explore_concat() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return Flux.concat(abcFlux, defFlux);
    }

    public static Flux<String> explore_concatWith() {

        var abcFlux = Flux.just("A", "B", "C");

        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.concatWith(defFlux);

    }

    public static Flux<String> explore_concatWith_mono() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");
        return aMono.concatWith(bMono);
    }

    public static Flux<String> explore_merge() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return Flux.merge(abcFlux, defFlux).log();
    }

    public static Flux<String> explore_mergeWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return abcFlux.mergeWith(defFlux).log();
    }

    public static Flux<String> explore_mergeWith_mono() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");
        return aMono.mergeWith(bMono).log();
    }

    // TODO: 5/16/2023 ASK difference between merge and merge sequential
    public static Flux<String> explore_mergeSequential() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return Flux.mergeSequential(abcFlux, defFlux).log();
    }

    public static Flux<String> explore_zip() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return Flux.zip(abcFlux, defFlux, (first, second) -> first + second)
                .log();
    }

    public static Flux<String> explore_zip_1() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        var _123Flux = Flux.just("1", "2", "3");
        var _456Flux = Flux.just("4", "5", "6");

        return Flux.zip(abcFlux, defFlux, _123Flux, _456Flux)
                .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4())
                .log();

    }

    public static Flux<String> explore_zipWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        return abcFlux.zipWith(defFlux, (first, second) -> first + second)
                .log();
    }

    public static Mono<String> explore_ZipWith_mono() {
        var aMono = Mono.just("A"); //A
        var bMono = Mono.just("B"); //B
        return aMono.zipWith(bMono)
                .map(t2 -> t2.getT1() + t2.getT2()) //AB
                .log();
    }

    public static Flux<String> exception_flux() {
        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWith(Flux.just("D"))
                .log();
    }

    public static Flux<String> explore_OnErrorReturn() {
        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occurred")))
                .onErrorReturn("D")
                .log();
    }

    public static Flux<String> explore_OnErrorResume(Exception e) {
        var recoveryFlux = Flux.just("D", "E", "F");
        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(e))
                .onErrorResume(ex -> {
                    log.error("Exception is ", ex);
                    if (ex instanceof IllegalStateException)
                        return recoveryFlux;
                    else
                        return Flux.error(ex);
                })
                .log();
    }

    public static Flux<String> explore_OnErrorContinue() {

        return Flux.just("A", "B", "C")
                .map(name -> {
                    if (name.equals("B"))
                        throw new IllegalStateException("Exception Occurred");
                    return name;
                })
                .concatWith(Flux.just("D"))
                .onErrorContinue((ex, name) -> {
                    log.error("Exception is ", ex);
                    log.info("name is {}", name);
                })
                .log();
    }

    // TODO: 5/16/2023 how on error map works ?
    public static Flux<String> explore_OnErrorMap(Exception e) {
        return Flux.just("A")
                .concatWith(Flux.error(e))
                .onErrorMap((ex) -> {
                    log.error("Exception is ", ex);
                    return new ReactorException(ex, ex.getMessage());
                })
                .log();
    }

    // TODO: 5/16/2023 how to on error works
    public static Flux<String> explore_doOnError() {
        return Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occurred")))
                .doOnError(ex -> {
                    log.error("Exception is ", ex);
                })
                .log();
    }

    public static Mono<Object> explore_Mono_OnErrorReturn() {
        return Mono.just("A")
                .map(value -> {
                    throw new RuntimeException("Exception Occurred");
                })
                .onErrorReturn("abc")
                .log();
    }

    // TODO: 5/16/2023 ASk differenec between generate and create
    public static Flux<Integer> explore_generate() {
        return Flux.generate(() -> 1, (state, sink) -> {
                    sink.next(state * 3);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }
        );
    }

    public static List<String> names() {
        delay(Duration.ofSeconds(1000));
        return List.of("alex", "ben", "chloe");
    }

    public Flux<String> explore_create() {
        return Flux.create(sink -> {
          /*  names()
                    .forEach(sink::next);*/
            CompletableFuture
                    .supplyAsync(() -> names())
                    .thenAccept(names -> {
                        names.forEach((name) -> {
                            sink.next(name);
                            // sink.next(name);
                        });
                    })
                    .thenRun(() -> sendEvents(sink));
        });
    }

    public Flux<String> explore_push() {
        return Flux.push(sink -> {
          /*  names()
                    .forEach(sink::next);*/
            CompletableFuture
                    .supplyAsync(() -> names())
                    .thenAccept(names -> {
                        names.forEach((name) -> {
                            sink.next(name);
                        });
                    })
                    .thenRun(() -> sendEvents(sink));
        });
    }

    public Mono<String> explore_create_mono() {
        return Mono.create(sink -> {
            sink.success("alex");
        });
    }

    public void sendEvents(FluxSink<String> sink) {

        CompletableFuture
                .supplyAsync(() -> names(), Executors.newSingleThreadExecutor())
                .thenAccept(names -> {
                    names.forEach(sink::next);
                })
                .thenRun(() -> {
                    sink.complete();
                });
    }

    public Flux<String> explore_handle(){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .handle((name,sink)->{
                    if(name.length()>3){
                        sink.next(name.toUpperCase());
                    }
                });
    }


}
