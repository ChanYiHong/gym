package hcy.gym.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/** 강좌와 맴버간 다 : 다 관계를 풀기 위한 클래스 **/
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"classes", "member"})
public class Reservation extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime classTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
