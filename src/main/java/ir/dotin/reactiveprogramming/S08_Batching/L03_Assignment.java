package ir.dotin.reactiveprogramming.S08_Batching;

import ir.dotin.reactiveprogramming.S08_Batching.helper.BookOrder;
import ir.dotin.reactiveprogramming.S08_Batching.helper.RevenueReport;
import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class L03_Assignment {

    public static void main(String[] args) {
        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );

        bookStream()
                .filter(bookOrder -> allowedCategories.contains(bookOrder.getCategory()))
                .doOnNext(bookOrder -> System.out.println(bookOrder))
                .buffer(Duration.ofSeconds(5))
                .doOnNext(bookOrders -> System.out.println(bookOrders))
                .map(L03_Assignment::revenueCalculator)
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(80);

    }

    private static RevenueReport revenueCalculator(List<BookOrder> bookOrderList) {
        Map<String, Double> collect = bookOrderList.stream()
                .collect(Collectors.groupingBy(BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)));

        return new RevenueReport(collect);
    }

    private static Flux<BookOrder> bookStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder());
    }


}
