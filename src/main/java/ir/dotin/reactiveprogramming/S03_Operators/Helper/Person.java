package ir.dotin.reactiveprogramming.S03_Operators.Helper;

import ir.dotin.reactiveprogramming.util.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = Utils.faker().name().firstName();
        this.age = Utils.faker().random().nextInt(1, 30);
    }
}
