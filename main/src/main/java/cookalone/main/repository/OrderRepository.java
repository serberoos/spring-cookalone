package cookalone.main.repository;

import cookalone.main.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    /* JPQL을 이용해 간단한 조회 수행 */
    @Query("select o from Order o " +
            "where o.member.nickname = :nickname " +
            "order by o.orderDate desc")
    List<Order> findOrders(@Param("nickname") String nickname, Pageable pageable);

    @Query("select count(o) from Order o " +
            "where o.member.nickname = :nickname")
    Long countOrder(@Param("nickname") String nickname);
}
