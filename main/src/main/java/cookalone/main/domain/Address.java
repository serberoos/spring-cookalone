package cookalone.main.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Embeddable // value type
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected 생성자
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;

}


