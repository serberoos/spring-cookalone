package cookalone.main.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Receipe {
    @Id
    @GeneratedValue
    @Column(name = "RECEIPE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToMany(mappedBy = "receipe")
    private List<Review> reviewList = new ArrayList<>();

    private String titie;
    private String content;
    private String writer;
    private int viewCnt;
    private String createdDate;
    private String modifiedDate;

}
