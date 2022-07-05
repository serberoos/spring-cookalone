package cookalone.main.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class MemberCart {

    @Id
    @Column(name = "MEMBER_CART_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;
}
