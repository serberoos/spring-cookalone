package cookalone.main.repository;

import cookalone.main.domain.Receipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @Modifying : @Query Annotation으로 작성된 변경, 삭제 쿼리 메소드를 사용할 때 필요.
 * @Query : JPQL 쿼리를 바로 작성할 수 있다.
 *
 */
@Repository
public interface ReceipeRepository extends JpaRepository<Receipe, Long> {

}
