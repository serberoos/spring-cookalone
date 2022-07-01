package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceipeRequestDto {

    /* 레시피 Service 요청(Request) DTO 클래스 */
    private Long id;

    @NotEmpty(message = "제목을 입력하세요.")
    private String title;

    @NotEmpty(message = "개요 란을 입력하세요.")
    private String summaryContent;

    @NotEmpty(message = "재료 란을 입력하세요.")
    private String materialContent;

    @NotEmpty(message = "요리과정을 입력하세요.")
    private String cookingProcessContent;

    private String cautionContent;

    private String writer;
    private int viewCnt;

    private Member member;
    private String createDate;
    private String modifiedDate;

    public Receipe toEntity() {
        Receipe receipe = Receipe.builder()
                .id(id)
                .title(title)
                .summaryContent(summaryContent)
                .materialContent(materialContent)
                .cookingProcessContent(cookingProcessContent)
                .cautionContent(cautionContent)
                .writer(writer)
                .viewCnt(viewCnt)
                .member(member)
                .build();
        return receipe;
    }
}
