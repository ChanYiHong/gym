package hcy.gym.repository.classes;

import hcy.gym.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {

    @Query("select c, t from Classes c left join c.teacher t where c.startTime = :startTime")
    List<Object[]> findByStartTime(@Param("startTime") LocalTime startTime);

}
