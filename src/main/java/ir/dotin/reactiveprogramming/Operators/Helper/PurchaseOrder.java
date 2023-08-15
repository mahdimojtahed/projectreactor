package ir.dotin.reactiveprogramming.Operators.Helper;


import ir.dotin.reactiveprogramming.util.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private String price;
    private int userID;

    public PurchaseOrder(int userID) {
        this.userID = userID;
        this.item = Utils.faker().commerce().productName();
        this.price = Utils.faker().commerce().price();
    }
}
