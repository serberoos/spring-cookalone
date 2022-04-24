package cookalone.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cookalone.main.domain.status.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* 1. NoArgsConstructor : protected 매개변수 없는 생성자 생성
 * 2. Table : DB에서 order은 예약어이므로 @Table을 이용해 테이블명을 재 설정.
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /* 1. FetchType.LAZY : 되도록 지연로딩을 사용(LAZY) | @ManyToOne, @OneToOne
     * 2. JoinColumn : 다른 Entity에 접근하기 위해 테이블 명을 알려줘야 함.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private User user;


    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    /* 1. mappedby : ORDER FK는 ORDER_PRODUCT 가 가지고 있다.(연관관계의 주인은 Order)
    *  2. CascadeType.ALL : 상위 엔티티에서 하위 엔티티로 모든 작업을 전파 ( 부모와 생명주기를 같이한다.)
    */
    @JsonIgnore
    @OneToMany(mappedBy= "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
