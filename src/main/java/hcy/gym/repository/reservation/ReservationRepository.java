package hcy.gym.repository.reservation;

import hcy.gym.domain.Classes;
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


    /** 예약 내역을 통해 해당 클래스의 예약 인원을 나타냄 **/
    @Query("select count(r) from Reservation r where r.classes = :classes")
    Integer findCountOfClassMember(@Param("classes") Classes classes);

}
