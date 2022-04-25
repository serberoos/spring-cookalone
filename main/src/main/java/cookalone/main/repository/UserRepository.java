package cookalone.main.repository;

import cookalone.main.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/* 1. Repository : Repository
 * 2. PersistenceContext : entity manager를 만들어서 자동으로 주입해준다.
 * 3. List<User> findAllUser 전부 조회시에는 JPQL을 작성 해야 한다. | JPQL은 객체를 기준으로 퀴리를 날림.
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveUser(User user){ // 회원가입
        em.persist(user);
    }

    public User findOneUser(Long id){ // user id 로 user 조회
        return em.find(User.class, id);
    }
    public List<User> findAllUser() { // 모든 user 조회
        return em.createQuery("select u from User u", User.class)
                .getResultList();

    }
    public List<User> findByNickName(String nickName){ //nickname으로 User 조회
        return em.createQuery("select u from User u where u.nickname =:nickName", User.class)
                .setParameter("nickName", nickName)
                .getResultList();
    }

}
