package ir.dotin.reactiveprogramming.Publishers.Helper;

import ir.dotin.reactiveprogramming.util.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private Double price;
    private String category;
    private int quantity;
    public PurchaseOrder() {
        this.item = Utils.faker().commerce().productName();
        this.price = Double.valueOf(Utils.faker().commerce().price());
        this.category = Utils.faker().commerce().department();
        this.quantity = Utils.faker().random().nextInt(1, 10);
    }
}

