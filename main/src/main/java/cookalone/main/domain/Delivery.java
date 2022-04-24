package cookalone.main.domain;

import cookalone.main.domain.status.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="DELIVERY_ID")
    private Long id;

    /* 1. mappedby : DELIVERY FK는 ORDER 가 가지고 있다.(연관관계의 주인은 Order)
     */
    @OneToOne(mappedBy="delivery", fetch=FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
