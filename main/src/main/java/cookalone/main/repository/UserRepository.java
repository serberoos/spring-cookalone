package cookalone.main.repository;

import cookalone.main.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @PersistenceContext : entity manager를 만들어서 자동으로 주입해준다.
 * @RequiredArgsConstructor : @PersistenceContent로 EntityManager를 주입하는 것과 동일한 효과
 * List<User> findAllUser 전부 조회시에는 JPQL을 작성 해야 한다. | JPQL은 객체를 기준으로 퀴리를 날림.
 */
@Repository
@RequiredArgsConstructor
public class UserRepository { // JpaRepository 상속해서 쉽게 코딩 가능

    private final EntityManager em;

    public void saveUser(User user){ // 회원가입
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

}
