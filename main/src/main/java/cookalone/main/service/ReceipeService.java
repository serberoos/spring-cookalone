package cookalone.main.service;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.dto.receipe.ReceipeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReceipeService {
    public Long saveReceipe(ReceipeDto.Request receipeDto, String nickname);
    public ReceipeDto.Response getReceipeDetail(Long id);
    public List<Receipe> searchReceipe(String keyword);
    public boolean deleteReceipe(Long id);
    public Page<Receipe> getReceipePages(Pageable pageable);
}
