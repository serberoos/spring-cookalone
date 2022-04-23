package cookalone.main.domain;

import lombok.Generated;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    private Order order;

    private Address address;

    private DeliveryStatus status;

}
