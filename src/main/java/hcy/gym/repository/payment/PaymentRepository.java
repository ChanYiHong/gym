package hcy.gym.repository.payment;

import hcy.gym.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p, ms from Payment p left join p.memberShip ms left join p.member m where m.id = :id")
    List<Object[]> findByMemberId(@Param("id") Long memberId);

}
