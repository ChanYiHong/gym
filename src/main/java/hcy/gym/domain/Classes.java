package hcy.gym.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"classType", "teacher"})
public class Classes extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassType classType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    // 수업 시작 시간.
    private LocalTime startTime;

    // 수업 끝나는 시간.
    private LocalTime endTime;

    // 요일.
    @Enumerated
    private Week week;

    // 수업 정원
    private Integer maxMemberNumber;


    // 다 대 다 풀어내기.

}
