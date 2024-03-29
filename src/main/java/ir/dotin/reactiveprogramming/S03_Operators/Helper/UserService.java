package ir.dotin.reactiveprogramming.S03_Operators.Helper;

import reactor.core.publisher.Flux;

public class UserService {
    public static Flux<User> getUsers() {
        return Flux.range(1, 3)
                .map(User::new);
    }

}
