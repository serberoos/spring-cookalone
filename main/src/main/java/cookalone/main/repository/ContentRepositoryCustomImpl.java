package cookalone.main.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cookalone.main.domain.QProductImg;
import cookalone.main.domain.dto.main.MainProductFormDto;
import cookalone.main.domain.dto.main.QMainProductFormDto;
import cookalone.main.domain.dto.search.ContentSearchDto;
import cookalone.main.domain.dto.search.QSearchProductFormDto;
import cookalone.main.domain.dto.search.SearchProductFormDto;
import cookalone.main.domain.product.QProduct;
import cookalone.main.domain.status.ProductSellStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ContentRepositoryCustomImpl implements ContentRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ContentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ProductSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QProduct.product.productSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression createDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QProduct.product.createdDate.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("productName", searchBy)) {
            return QProduct.product.productName.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QProduct.product.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<SearchProductFormDto> getSearchProductPage(ContentSearchDto contentSearchDto, Pageable pageable) {
        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;
        QueryResults<SearchProductFormDto> results = queryFactory
                .select(
                        new QSearchProductFormDto(
                                product.id,
                                product.productName,
                                product.productDetails,
                                productImg.imgUrl,
                                product.price,
                                product.productSellStatus,
                                product.createdBy
                        )
                )
                .from(productImg)
                .join(productImg.product, product)
                .where(productImg.repimgYn.eq("Y"))
                .where(createDtsAfter(contentSearchDto.getSearchDateType()),
                        searchSellStatusEq(contentSearchDto.getSearchSellStatus()),
                        searchByLike(contentSearchDto.getSearchBy(),
                                contentSearchDto.getSearchQuery()))

                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
// ==
//                .selectFrom(QProduct.product)
//                .where(createDtsAfter(productSearchDto.getSearchDateType()),
//                        searchSellStatusEq(productSearchDto.getSearchSellStatus()),
//                        searchByLike(productSearchDto.getSearchBy(),
//                                productSearchDto.getSearchQuery()))
//                .orderBy(QProduct.product.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//==

        List<SearchProductFormDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression productNmLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QProduct.product.productName.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainProductFormDto> getMainProductPage(ContentSearchDto contentSearchDto, Pageable pageable) {
        QProduct product = QProduct.product;
        QProductImg productImg = QProductImg.productImg;

        QueryResults<MainProductFormDto> results = queryFactory
                .select(
                        new QMainProductFormDto(
                                product.id,
                                product.productName,
                                product.productDetails,
                                productImg.imgUrl,
                                product.price)
                )
                .from(productImg)
                .join(productImg.product, product)
                .where(productImg.repimgYn.eq("Y"))
                .where(productNmLike(contentSearchDto.getSearchQuery()))
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainProductFormDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }


}
