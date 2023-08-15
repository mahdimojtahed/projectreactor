package ir.dotin.reactiveprogramming.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileService {

    public static Mono<String> read(Path path, String fileName) {
        return Mono.fromSupplier(() -> fileReader(path, fileName));
    }

    public static Mono<Void> write(Path path, String fileName, String content) {
        return Mono.fromRunnable(() -> fileWriter(path, fileName, content));
    }

    public static Mono<Void> delete(Path path, String fileName) {
        return Mono.fromRunnable(() -> fileDelete(path, fileName));
    }

    private static String fileReader(Path filePath, String fileName) {
        try {
            return Files.readString(filePath.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    private static void fileWriter(Path filePath, String fileName, String content) {
        try {
            Files.writeString(filePath.resolve(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fileDelete(Path filePath, String fileName) {
        try {
            Files.delete(filePath.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Callable<BufferedReader> openReader(Path path) {
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return (br, sink) -> {
            try {
                String line = br.readLine();
                if (Objects.isNull(line))
                    sink.complete();
                else
                    sink.next(line);
            } catch (IOException e) {
                sink.error(e);
            }
            return br;
        };
    }

    private Consumer<BufferedReader> closeReader() {
        return br -> {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
    public Flux<String> read(Path path) {
        return Flux.generate(
                openReader(path),
                read(),
                closeReader());
    }

}
