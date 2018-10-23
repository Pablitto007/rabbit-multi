package sender.data;

import library.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleDataProvider {


    public static List<Product> createProductsList(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(new Random().nextInt(5), "Blue hat", Product.Category.NORMAL, new BigDecimal(33.50));
        Product product2 = new Product(new Random().nextInt(5), "White t-shirt", Product.Category.SALE, new BigDecimal(24.50));
        products.add(product1);
        products.add(product2);
        return products;

    }
}
