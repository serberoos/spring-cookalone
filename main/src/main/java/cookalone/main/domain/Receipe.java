package cookalone.main.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @EntityListener(AuditingEntityListener.class) Auditing 기능을 쓰기 위함. :: @CreatedDate @ModifiedDate
 * @NoArgsConstructor(access = AccessLevel.PROTECTED) : 인수 없는 생성자를 protected 모드로 추가한다.( JPA 관례 )
 * @Builder 패턴과 @NoArgsConstructor를 함께 쓰면 오류가 발생한다. => AllArgsConstroutor를 추가한다.
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Receipe {
    @Id
    @GeneratedValue
    @Column(name = "RECEIPE_ID")
    private Long id;

    private String title;
    private String summaryContent;
    private String materialContent;
    private String cookingProcessContent;
    private String cautionContent;

    private String writer;
    private int viewCnt;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Member member;

    @OneToMany(mappedBy = "receipe")
    private List<Review> reviewList = new ArrayList<>();



    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
