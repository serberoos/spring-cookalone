package cookalone.main.domain;

import cookalone.main.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_IMG")
@Getter @Setter
public class ProductImg {

    @Id
    @Column(name="PRODUCT_IMG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public void updateProductImg(String oriImgName, String imgName, String imgUrl){
        System.out.println("12");
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
