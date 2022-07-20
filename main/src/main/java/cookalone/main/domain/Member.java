package cookalone.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cookalone.main.domain.status.Gender;
import cookalone.main.domain.status.Role;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @EntityListener(AuditingEntityListener.class) Auditing 기능을 쓰기 위함. :: @CreatedDate @ModifiedDate
 * @NoArgsConstructor(access = AccessLevel.PROTECTED) : 인수 없는 생성자를 protected 모드로 추가한다.( JPA 관례 )
 * @Builder 패턴과 @NoArgsConstructor를 함께 쓰면 오류가 발생한다. => AllArgsConstroutor를 추가한다.
 */

@Builder
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID") //ID Column 이름
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreatedDate
    private LocalDateTime termsAgreeDate;

    /* 1. JsonIgnore: Json으로 데이터를 주고 받을 때, 해당 데이터는 응답값에 보이지 않음.
     * 2. OneToMany: 연관관계는 해당 Entity를 기준으로
     * 3. mappedby : USER FK는 ORDER 가 가지고 있다.(연관관계의 주인은 Order)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Receipe> receipeList = new ArrayList<>();


    @Column(name= "created_date", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name= "modified_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /* 회원 정보 수정을 위한 set method*/
    public void modify(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }

    /* 소셜로그인 시 이미 등록된 회원이라면 수정날짜만 업데이트 하고
     * 기존 데이터는 그대로 보존하도록 예외처리 */
    public Member updateModifiedDate() {
        this.modifiedDate = LocalDateTime.now();
        return this;
    }
    public String getRoleValue(){
        return this.role.getValue();
    }

}
