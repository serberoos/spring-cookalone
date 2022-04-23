package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    //stockQuantity?
    private List<ProductCategory> productCategoryList;



}
