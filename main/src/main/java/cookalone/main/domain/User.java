package cookalone.main.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String username;


    private Address address;

    private List<Order> orderList;

    private String createdDate;
    private String modifiedDate;
}
