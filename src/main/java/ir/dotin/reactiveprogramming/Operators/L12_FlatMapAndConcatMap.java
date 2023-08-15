package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.Operators.Helper.OrderService;
import ir.dotin.reactiveprogramming.Operators.Helper.UserService;
import ir.dotin.reactiveprogramming.util.Utils;

public class L12_FlatMapAndConcatMap {

    public static void main(String[] args) {


        UserService.getUsers()
//                .map(user -> OrderService.getOrders(user.getUserID())) // check this
                .flatMap(user -> OrderService.getOrders(user.getUserID()))
//                .concatMap(user -> OrderService.getOrders(user.getUserID()))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60);

    }

}
