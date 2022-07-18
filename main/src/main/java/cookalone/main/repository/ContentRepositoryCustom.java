package cookalone.main.repository;

import cookalone.main.domain.dto.search.ProductSearchDto;
import cookalone.main.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);
}
