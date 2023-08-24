package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.assignment.FileService;
import ir.dotin.reactiveprogramming.util.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class L20_FluxAssignment2 {

    public static void main(String[] args) {


        FileService fileService = new FileService();
        Path path = Paths.get("src/main/resources/assignment/file01.txt");
        fileService.read(path)
                .take(5)
                .subscribe(Utils.subscriber());


    }
}
