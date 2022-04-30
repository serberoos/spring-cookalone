package cookalone.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cookalone.main.domain.status.DeliveryStatus;
import cookalone.main.domain.status.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name="USER_ID") //ID Column 이름
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String username;
    private String birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Embedded // 값 타입
    private Address address;

    /* 1. JsonIgnore: Json으로 데이터를 주고 받을 때, 해당 데이터는 응답값에 보이지 않음.
     * 2. OneToMany: 연관관계는 해당 Entity를 기준으로
     * 3. mappedby : USER FK는 ORDER 가 가지고 있다.(연관관계의 주인은 Order)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Receipe> receipeList = new ArrayList<>();

    private String createdDate;
    private String modifiedDate;

    public User(String email, String password, String nickname, String username, String birthDate, Gender gender, Address address){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
    }
}
