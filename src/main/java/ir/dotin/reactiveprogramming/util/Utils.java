package ir.dotin.reactiveprogramming.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;


public class Utils {

    private static Logger logger;


    private static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received: " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> System.out.println("Error: " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepInSeconds(int seconds) {
        sleepInMillis(seconds * 1000);
    }

    public static void sleepInMillis(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }

    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }


}
