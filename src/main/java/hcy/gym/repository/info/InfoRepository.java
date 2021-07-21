package hcy.gym.repository.info;

import hcy.gym.domain.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfoRepository extends JpaRepository<Info, Long> {

    @Query("select i from Info i")
    Page<Info> findAll(Pageable pageable);

}
