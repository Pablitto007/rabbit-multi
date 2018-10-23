package receiver.worker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import library.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String in) throws InterruptedException {
        Observable<Product> obs = observe(in,  1);
        /*obs
                .distinct()
                .subscribe(this::doWork);*/
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String in) throws InterruptedException {
        Observable<Product> obs = observe(in, 2);
        obs
                .distinctUntilChanged((product, product2) -> product.equals(product2))
                .subscribe(this::doWork);
    }

    public Observable<Product> observe(String in, int instance) {
        return Observable.fromCallable(() -> receive(in, instance));
    }

    public Product receive(String in, int instance) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();

        Gson gson = new GsonBuilder().create();
        Product product = gson.fromJson(in, Product.class);
        /*System.out.println("instance " + instance +
                " [x] Received '" + product.toString() + "'");*/
        //doWork(product);
        watch.stop();
       /* System.out.println("instance " + instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");*/

        //Thread.sleep(2000);
        return product;
    }

    private void doWork(Product in) throws InterruptedException {
        System.out.println(in);
    }
}
