package ir.dotin.reactiveprogramming.S01_Mono;

import java.util.stream.Stream;

public class L1_Streams {
    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(1,2)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * 2;
                });

        System.out.println(integerStream); // streams are lazy - this will not do anything
        integerStream.forEach(System.out::println);

    }
}
