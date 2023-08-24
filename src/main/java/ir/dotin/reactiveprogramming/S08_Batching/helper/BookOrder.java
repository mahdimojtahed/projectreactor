package ir.dotin.reactiveprogramming.S08_Batching.helper;

import com.github.javafaker.Book;
import ir.dotin.reactiveprogramming.util.Utils;
import lombok.ToString;

@ToString
public class BookOrder {

    private String title;
    private String author;
    private String category;
    private Double price;

    public BookOrder() {
        Book book = Utils.faker().book();
        this.title = book.title();
        this.author = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Utils.faker().commerce().price());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }
}
