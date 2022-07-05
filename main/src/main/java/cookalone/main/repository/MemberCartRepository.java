package cookalone.main.repository;

import cookalone.main.domain.MemberCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCartRepository extends JpaRepository<MemberCart, Long> {

}
