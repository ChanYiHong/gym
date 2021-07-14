package hcy.gym.repository.membership;

import hcy.gym.domain.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipRepository extends JpaRepository<MemberShip, Long> {
}
