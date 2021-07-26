package hcy.gym.repository.reservation;

import hcy.gym.domain.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @EntityGraph(attributePaths = {"teacher"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r, c from Reservation r left join r.classes c left join r.member m where m.id = :id")
    List<Object[]> findByMemberId(@Param("id") Long memberId);

}
