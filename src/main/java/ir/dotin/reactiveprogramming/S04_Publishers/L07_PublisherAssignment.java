package ir.dotin.reactiveprogramming.S04_Publishers;

import ir.dotin.reactiveprogramming.S04_Publishers.Helper.OrderService;
import ir.dotin.reactiveprogramming.S04_Publishers.Helper.InventoryService;
import ir.dotin.reactiveprogramming.S04_Publishers.Helper.RevenueService;
import ir.dotin.reactiveprogramming.util.Utils;

public class L07_PublisherAssignment {

    public static void main(String[] args) {


        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();


        // revenue and inv observes the order stream.
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream().subscribe(Utils.subscriber("inventory"));
        revenueService.revenueStream().subscribe(Utils.subscriber("revenue"));

        Utils.sleepInSeconds(60);




    }

}
