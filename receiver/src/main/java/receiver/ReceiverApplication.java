package receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"receiver"})
public class ReceiverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }
}
