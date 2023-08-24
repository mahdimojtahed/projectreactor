package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.assignment.StockPricePublisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class L12_FluxAssignment {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch count  = new CountDownLatch(1);

        StockPricePublisher.getPrice()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(LocalDateTime.now() + " " +  ": Price : " + integer);
                        if (integer > 110 || integer < 90) {
                            subscription.cancel();
                            count.countDown();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        count.countDown();
                    }

                    @Override
                    public void onComplete() {
                        count.countDown();
                    }
                });

        count.await();

    }
}
