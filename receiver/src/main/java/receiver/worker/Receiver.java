package receiver.worker;

import io.reactivex.Observable;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(Message in) throws InterruptedException {
        Observable<Message> obs = observe(in);
        obs
                //.distinct()
                .subscribe(this::doWork);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(Message in) throws InterruptedException {
        Observable<Message> obs = observe(in);
        obs
                //.distinctUntilChanged((product, product2) -> product.equals(product2))
                .subscribe(this::doWork);
    }

    public Observable<Message> observe(Message in) {
        return Observable.fromCallable(() -> receive(in));
    }

    public Message receive(Message in) throws InterruptedException {
        /*StopWatch watch = new StopWatch();
        watch.start();

        Gson gson = new GsonBuilder().create();
        Product product = gson.fromJson(in, Product.class);
        *//*System.out.println("instance " + instance +
                " [x] Received '" + product.toString() + "'");*//*
        //doWork(product);
        watch.stop();
       *//* System.out.println("instance " + instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");*//*

        //Thread.sleep(2000);
        return product;*/
        return in;
    }

    private void doWork(Message in) throws InterruptedException {
        System.out.println("Message bytes: " + in.getBody().length);
    }
}
