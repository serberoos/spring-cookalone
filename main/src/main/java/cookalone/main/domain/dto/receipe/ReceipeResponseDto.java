package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.User;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ReceipeResponseDto implements Serializable {
    private Long id;

    private String title;
    private String summaryContent;
    private String materialContent;
    private String cookingProcessContent;
    private String cautionContent;
    private String writer;
    private int viewCnt;

    private User user;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    /* Entity -> Dto */
    public ReceipeResponseDto(Receipe receipe){
        this.id = receipe.getId();
        this.title = receipe.getTitle();
        this.summaryContent = receipe.getSummaryContent();
        this.materialContent = receipe.getMaterialContent();
        this.cookingProcessContent = receipe.getCookingProcessContent();
        this.cautionContent = receipe.getCautionContent();
        this.writer = receipe.getWriter();
        this.viewCnt = receipe.getViewCnt();

        this.user = receipe.getUser();

        this.createDate = receipe.getCreatedDate();
        this.modifiedDate = receipe.getModifiedDate();

    }
}
