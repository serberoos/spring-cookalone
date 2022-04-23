package cookalone.main.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private List<Product> productList;
    //parent : Category?
    // child List?
}
