package cookalone.main.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    @Column(name ="REVIEW_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="RECEIPE_ID")
    private Receipe receipe;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Member member;

    private String reviewContent;
    private String createdDate;
    private String modifiedDate;

}
