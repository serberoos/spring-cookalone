package cookalone.main.repository;

import cookalone.main.domain.dto.main.MainProductFormDto;
import cookalone.main.domain.dto.search.ContentSearchDto;
import cookalone.main.domain.dto.search.SearchProductFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentRepositoryCustom {

    Page<SearchProductFormDto> getSearchProductPage(ContentSearchDto contentSearchDto, Pageable pageable);

    Page<MainProductFormDto> getMainProductPage(ContentSearchDto contentSearchDto, Pageable pageable);
}
