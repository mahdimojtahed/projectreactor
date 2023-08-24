package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.S03_Operators.Helper.OrderService;
import ir.dotin.reactiveprogramming.S03_Operators.Helper.UserService;
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
