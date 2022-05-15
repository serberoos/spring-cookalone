package cookalone.main.domain.dto.receipe;

import cookalone.main.domain.Receipe;
import cookalone.main.domain.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * request, response DTO 클래스들을 하나로 묶어 InnerStaticClass로 한번에 관리합니다.
 */
public class ReceipeDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

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

        private User user;
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
                    .user(user)
                    .build();
            return receipe;
        }
    }
    @Getter
    public static class Response implements Serializable {
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
        public Response(Receipe receipe){
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
}


