package cookalone.main.domain.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/* 1. DiscriminatorValue: Json으로 데이터를 주고 받을 때, 해당 데이터는 응답값에 보이지 않음.
 */
@Entity
@Getter
@Setter
@DiscriminatorValue("M")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Millkit extends Product {

    private String allergyInfo;

}
