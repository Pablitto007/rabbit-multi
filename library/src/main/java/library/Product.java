package library;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {

    private final Integer productId;
    private final String desc;
    private final Category category;
    private final BigDecimal price;


    public enum Category {

        NORMAL(1), PROMOTION(2), SALE(3);

        private int categoryId;

        Category(int id) {
            this.categoryId = id;
        }
    }
}
