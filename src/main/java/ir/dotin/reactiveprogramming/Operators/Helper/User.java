package ir.dotin.reactiveprogramming.Operators.Helper;

import ir.dotin.reactiveprogramming.util.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private int userID;
    private String name;

    public User(int userID) {
        this.userID = userID;
        this.name = Utils.faker().funnyName().name();
    }
}
