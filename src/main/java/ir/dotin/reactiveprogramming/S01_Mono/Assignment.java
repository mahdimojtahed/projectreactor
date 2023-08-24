package ir.dotin.reactiveprogramming.S01_Mono;

import ir.dotin.reactiveprogramming.assignment.FileService;
import ir.dotin.reactiveprogramming.util.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assignment {
    private static final Path path = Paths.get("src/main/resources/assignment");
    private static final String content = "If You can read This, Your Method Works !";

    public static void main(String[] args) throws IOException {

        FileService.read(path, "mono_assignment.txt")
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete());

        FileService.write(path, "mono_created_file.txt", content)
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        () -> System.out.println("Finish Writing FIle"));

        FileService.write(path, "mono_file_to_delete.txt", content).subscribe();
        FileService.delete(path, "mono_file_to_delete.txt").subscribe();
    }

}
