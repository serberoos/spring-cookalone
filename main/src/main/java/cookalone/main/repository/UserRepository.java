package cookalone.main.repository;

import cookalone.main.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @PersistenceContext : entity manager를 만들어서 자동으로 주입해준다.
 * @RequiredArgsConstructor : @PersistenceContent로 EntityManager를 주입하는 것과 동일한 효과
 * List<User> findAllUser 전부 조회시에는 JPQL을 작성 해야 한다. | JPQL은 객체를 기준으로 퀴리를 날림.
 */
@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Long> { // JpaRepository 상속해서 쉽게 구현 가능
    /* 스프링 시큐리티 */
    Optional<User> findByEmail(String email);

    /* nickname으로 user객체 GET */
    User findByNickname(String nickname);

    /*
    private final EntityManager em;

    public void save(User user){ // 회원가입
        em.persist(user);
    }

    public User findOne(Long id){ // user id 로 user 조회
        return em.find(User.class, id);
    }
    public List<User> findAll() { // 모든 user 조회
        return em.createQuery("select u from User u", User.class)
                .getResultList();

    }
    public List<User> findByNickname(String nickname){ //nickname으로 User 조회
        return em.createQuery("select u from User u where u.nickname =:nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
    public List<User> findByEmail(String email){ //email으로 User 조회
        return em.createQuery("select u from User u where u.email =:email", User.class)
                .setParameter("email", email)
                .getResultList();
    }
    */
}
