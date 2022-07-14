package cookalone.main.repository;

import cookalone.main.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    List<ProductImg> findByProductIdOrderByIdAsc(Long ProductId);

    ProductImg findByProductIdAndRepimgYn(Long ProductId, String repimgYn);
}
