package hcy.gym.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"memberShip"})
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String name;

    private String password;

    private String phoneNumber;

    // 회원권 결제 정보 (1:1) 연관 관계 주인은 회원.
    @OneToOne(fetch = FetchType.LAZY)
    private Payment payment;



}
