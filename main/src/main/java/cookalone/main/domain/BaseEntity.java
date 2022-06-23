//package cookalone.main.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import java.time.LocalDateTime;
//
///**
// * @MappedSuperClass : BaseEntity에서 일반 클래스를 상속 받기 위해선 해당 어노테이션을 작성해야 한다.
// * @Column(updatable = false) : entity가 update 될 때 created_at이 update되지 않게 한다.
// * @PrePersist : persist() 메소드 실행전 created_at, updated_at 을 현재 시각을 수정 시켜준다.
// * @PreUpdate : 변경 감지를 통해 update 쿼리가 실행 되었을 때 해당 어노테이션에서 preUpdate() 메소드를 통해 updated_at의 시간을 현재시간으로 수정 시켜준다.\
// *
// * @CreatedDate : Entity가 생성되어 저장될 때, 시간이 자동으로 저장된다.
// * @LastModifiedDate : 조회한 Entity의 값을 변경할 때, 시간이 자동으로 저장된다.
// */
//@Getter @Setter
//@MappedSuperclass
//public class BaseEntity {
//    @Column(updatable =false)
//    private LocalDateTime created_at;
//    private LocalDateTime updated_at;
//
//    @PrePersist
//    public void prePersist(){
//        LocalDateTime now = LocalDateTime.now();
//        created_at = now;
//        updated_at = now;
//    }
//    @PreUpdate
//    public void preUpdate() {
//        updated_at = LocalDateTime.now();
//    }
//}
