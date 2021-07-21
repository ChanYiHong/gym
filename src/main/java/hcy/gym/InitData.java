package hcy.gym;

import hcy.gym.domain.*;
import hcy.gym.repository.classes.ClassesRepository;
import hcy.gym.repository.classtype.ClassTypeRepository;
import hcy.gym.repository.info.InfoRepository;
import hcy.gym.repository.member.MemberRepository;
import hcy.gym.repository.membership.MemberShipRepository;
import hcy.gym.repository.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.initMember();
        initDataService.initMemberShip();

        initDataService.initClassTypeAndTeacher();
//        initDataService.initClassTypeAndTeacherAndClasses();

        initDataService.initInfo();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitDataService {

        private final MemberRepository memberRepository;
        private final MemberShipRepository memberShipRepository;
        private final TeacherRepository teacherRepository;
        private final ClassTypeRepository classTypeRepository;
        private final ClassesRepository classesRepository;
        private final InfoRepository infoRepository;

        private final EntityManager em;

        @Transactional
        void initMember() {
            Member member = Member.builder()
                    .loginId("test")
                    .name("test1")
                    .password("1234")
                    .phoneNumber("01012345678")
                    .build();
            memberRepository.save(member);
        }


        @Transactional
        void initMemberShip() {

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 1개월")
                    .month(1)
                    .week(5)
                    .price(330000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 3개월")
                    .month(3)
                    .week(5)
                    .price(495000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 6개월")
                    .month(6)
                    .week(5)
                    .price(759000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 5회 12개월")
                    .month(12)
                    .week(5)
                    .price(1089000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 1개월")
                    .month(1)
                    .week(2)
                    .price(275000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 3개월")
                    .month(3)
                    .week(2)
                    .price(319000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 6개월")
                    .month(6)
                    .week(2)
                    .price(495000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 2회 12개월")
                    .month(12)
                    .week(2)
                    .price(726000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 1개월")
                    .month(6)
                    .week(2)
                    .price(297000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 3개월")
                    .month(3)
                    .week(3)
                    .price(396000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 6개월")
                    .month(6)
                    .week(3)
                    .price(539000)
                    .build());

            memberShipRepository.save(MemberShip.builder()
                    .name("주 3회 12개월")
                    .month(12)
                    .week(3)
                    .price(869000)
                    .build());

        }

        @Transactional
        void initTeacher() {
            teacherRepository.save(Teacher.builder()
                    .name("이채린")
                    .age(25)
                    .phoneNumber("01012123434")
                    .build());

            teacherRepository.save(Teacher.builder()
                    .name("최혜미")
                    .age(23)
                    .phoneNumber("01012125656")
                    .build());

            teacherRepository.save(Teacher.builder()
                    .name("박경란")
                    .age(28)
                    .phoneNumber("01012127878")
                    .build());
        }

        @Transactional
        void initClassType() {
            classTypeRepository.save(ClassType.builder()
                    .name("젠링").build());

            classTypeRepository.save(ClassType.builder()
                    .name("플라잉 LV.1").build());

            classTypeRepository.save(ClassType.builder()
                    .name("필라테스").build());

            classTypeRepository.save(ClassType.builder()
                    .name("S-테라피").build());

            classTypeRepository.save(ClassType.builder()
                    .name("기초플라잉").build());

            classTypeRepository.save(ClassType.builder()
                    .name("비키니 요가").build());

            classTypeRepository.save(ClassType.builder()
                    .name("빈야사").build());

            classTypeRepository.save(ClassType.builder()
                    .name("힐링 + 교정").build());

            classTypeRepository.save(ClassType.builder()
                    .name("힐링").build());

            classTypeRepository.save(ClassType.builder()
                    .name("인사이드 플로우").build());

            classTypeRepository.save(ClassType.builder()
                    .name("인스프릿").build());

            classTypeRepository.save(ClassType.builder()
                    .name("발레핏").build());

            classTypeRepository.save(ClassType.builder()
                    .name("사이드 코어").build());

            classTypeRepository.save(ClassType.builder()
                    .name("밸런스 교정").build());

            classTypeRepository.save(ClassType.builder()
                    .name("로우 플라잉").build());

            classTypeRepository.save(ClassType.builder()
                    .name("테라피").build());
        }

        @Transactional
        void initClassTypeAndTeacher() {
            initClassType();
            initTeacher();
            initClasses();
        }

        @Transactional
        void initClasses() {
            Teacher 이채린 = teacherRepository.getById(1L);
            Teacher 최혜미 = teacherRepository.getById(2L);
            Teacher 박경란 = teacherRepository.getById(3L);

            ClassType 젠링 = classTypeRepository.getById(1L);
            ClassType 플라잉LV1 = classTypeRepository.getById(2L);
            ClassType 필라테스 = classTypeRepository.getById(3L);
            ClassType S테라피 = classTypeRepository.getById(4L);
            ClassType 기초플라잉 = classTypeRepository.getById(5L);
            ClassType 비키니_요가 = classTypeRepository.getById(6L);
            ClassType 빈야사 = classTypeRepository.getById(7L);
            ClassType 힐링교정 = classTypeRepository.getById(8L);
            ClassType 힐링 = classTypeRepository.getById(9L);
            ClassType 인사이드_플로우 = classTypeRepository.getById(10L);
            ClassType 인스프릿 = classTypeRepository.getById(11L);
            ClassType 발레핏 = classTypeRepository.getById(12L);
            ClassType 사이드_코어 = classTypeRepository.getById(13L);
            ClassType 밸런스_교정 = classTypeRepository.getById(14L);
            ClassType 로우_플라잉 = classTypeRepository.getById(15L);
            ClassType 테라피 = classTypeRepository.getById(16L);

            /** 월요일 **/
            Classes 월1 = Classes.builder()
                    .classType(젠링)
                    .name("젠링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월2 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(이채린)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월3 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월4 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월5 = Classes.builder()
                    .classType(S테라피)
                    .name("S-테라피")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(19,00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월6 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(20,00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월7 = Classes.builder()
                    .classType(비키니_요가)
                    .name("비키니요가")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(21,00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            classesRepository.save(월1);
            classesRepository.save(월2);
            classesRepository.save(월3);
            classesRepository.save(월4);
            classesRepository.save(월5);
            classesRepository.save(월6);
            classesRepository.save(월7);


            /** 화요일 **/
            Classes 화1 = Classes.builder()
                    .classType(빈야사)
                    .name("빈야사")
                    .teacher(박경란)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화2 = Classes.builder()
                    .classType(힐링교정)
                    .name("힐링 + 교정")
                    .teacher(박경란)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화3 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(박경란)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화4 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(이채린)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화5 = Classes.builder()
                    .classType(빈야사)
                    .name("빈야사")
                    .teacher(이채린)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화6 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화7 = Classes.builder()
                    .classType(힐링)
                    .name("힐링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            classesRepository.save(화1);
            classesRepository.save(화2);
            classesRepository.save(화3);
            classesRepository.save(화4);
            classesRepository.save(화5);
            classesRepository.save(화6);
            classesRepository.save(화7);


            /** 수요일 **/

            Classes 수1 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수2 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수3 = Classes.builder()
                    .classType(인사이드_플로우)
                    .name("인사이드 플로우")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수4 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수5 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(박경란)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수6 = Classes.builder()
                    .classType(인스프릿)
                    .name("인스프릿")
                    .teacher(박경란)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수7 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();


            classesRepository.save(수1);
            classesRepository.save(수2);
            classesRepository.save(수3);
            classesRepository.save(수4);
            classesRepository.save(수5);
            classesRepository.save(수6);
            classesRepository.save(수7);


            /** 목요일 **/

            Classes 목1 = Classes.builder()
                    .classType(발레핏)
                    .name("발레핏")
                    .teacher(이채린)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목2 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(이채린)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목3 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목4 = Classes.builder()
                    .classType(사이드_코어)
                    .name("사이드코어")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목5 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목6 = Classes.builder()
                    .classType(밸런스_교정)
                    .name("밸런스교정")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목7 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1 ")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            classesRepository.save(목1);
            classesRepository.save(목2);
            classesRepository.save(목3);
            classesRepository.save(목4);
            classesRepository.save(목5);
            classesRepository.save(목6);
            classesRepository.save(목7);


            /** 금요일 **/

            Classes 금1 = Classes.builder()
                    .classType(테라피)
                    .name("테라피")
                    .teacher(박경란)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금2 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금3 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(박경란)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금4 = Classes.builder()
                    .classType(로우_플라잉)
                    .name("로우플라잉")
                    .teacher(이채린)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금5 = Classes.builder()
                    .classType(발레핏)
                    .name("발레핏")
                    .teacher(이채린)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금6 = Classes.builder()
                    .classType(젠링)
                    .name("젠링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금7 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();


            classesRepository.save(금1);
            classesRepository.save(금2);
            classesRepository.save(금3);
            classesRepository.save(금4);
            classesRepository.save(금5);
            classesRepository.save(금6);
            classesRepository.save(금7);



        }

        @Transactional
        void initInfo() {
            infoRepository.save(Info.builder()
            .name("비키니요가").build());

            infoRepository.save(Info.builder()
                    .name("벨런스교정 요가").build());

            infoRepository.save(Info.builder()
                    .name("테라피 요가").build());

            infoRepository.save(Info.builder()
                    .name("힐링+교정 요가").build());

            infoRepository.save(Info.builder()
                    .name("사이드코어 요가").build());

        }

        @Transactional
        void initClassTypeAndTeacherAndClasses() {

            Teacher 이채린 = Teacher.builder()
                    .name("이채린")
                    .age(25)
                    .phoneNumber("01012123434")
                    .build();

            Teacher 최혜미 = Teacher.builder()
                    .name("최혜미")
                    .age(23)
                    .phoneNumber("01012125656")
                    .build();

            Teacher 박경란 = Teacher.builder()
                    .name("박경란")
                    .age(28)
                    .phoneNumber("01012127878")
                    .build();

//            teacherRepository.save(이채린);
//            teacherRepository.save(최혜미);
//            teacherRepository.save(박경란);


            em.persist(이채린);
            em.persist(최혜미);
            em.persist(박경란);

            ClassType 젠링 = ClassType.builder()
                    .name("젠링").build();

            ClassType 플라잉LV1 = ClassType.builder()
                    .name("플라잉 LV.1").build();

            ClassType 필라테스 = ClassType.builder()
                    .name("필라테스").build();

            ClassType S테라피 = ClassType.builder()
                    .name("S-테라피").build();

            ClassType 기초플라잉 = ClassType.builder()
                    .name("기초플라잉").build();

            ClassType 비키니_요가 = ClassType.builder()
                    .name("비키니 요가").build();

            ClassType 빈야사 = ClassType.builder()
                    .name("빈야사").build();

            ClassType 힐링교정 = ClassType.builder()
                    .name("힐링 + 교정").build();

            ClassType 힐링 = ClassType.builder()
                    .name("힐링").build();

            ClassType 인사이드_플로우 = ClassType.builder()
                    .name("인사이드 플로우").build();

            ClassType 인스프릿 = ClassType.builder()
                    .name("인스프릿").build();

            ClassType 발레핏 = ClassType.builder()
                    .name("발레핏").build();

            ClassType 사이드_코어 = ClassType.builder()
                    .name("사이드 코어").build();

            ClassType 밸런스_교정 = ClassType.builder()
                    .name("밸런스 교정").build();

            ClassType 로우_플라잉 = ClassType.builder()
                    .name("로우 플라잉").build();

            ClassType 테라피 = ClassType.builder()
                    .name("테라피").build();
//
//
//            classTypeRepository.save(젠링);
//            classTypeRepository.save(플라잉LV1);
//            classTypeRepository.save(S테라피);
//            classTypeRepository.save(기초플라잉);
//            classTypeRepository.save(비키니_요가);
//            classTypeRepository.save(빈야사);
//            classTypeRepository.save(힐링교정);
//            classTypeRepository.save(힐링);
//            classTypeRepository.save(인사이드_플로우);
//            classTypeRepository.save(인스프릿);
//            classTypeRepository.save(발레핏);
//            classTypeRepository.save(사이드_코어);
//            classTypeRepository.save(밸런스_교정);
//            classTypeRepository.save(로우_플라잉);
//            classTypeRepository.save(테라피);
//
////            em.flush(); em.clear();
////
            em.persist(젠링);
            em.persist(플라잉LV1);
            em.persist(S테라피);
            em.persist(기초플라잉);
            em.persist(비키니_요가);
            em.persist(빈야사);
            em.persist(힐링교정);
            em.persist(힐링);
            em.persist(인사이드_플로우);
            em.persist(인스프릿);
            em.persist(발레핏);
            em.persist(사이드_코어);
            em.persist(밸런스_교정);
            em.persist(로우_플라잉);
            em.persist(테라피);
//
//
//            /** 월요일 **/
            Classes 월1 = Classes.builder()
                    .classType(젠링)
                    .name("젠링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월2 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(이채린)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월3 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월4 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월5 = Classes.builder()
                    .classType(S테라피)
                    .name("S-테라피")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(19,00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월6 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(20,00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            Classes 월7 = Classes.builder()
                    .classType(비키니_요가)
                    .name("비키니요가")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(21,00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.MON)
                    .maxMemberNumber(30)
                    .build();

            /** 화요일 **/
            Classes 화1 = Classes.builder()
                    .classType(빈야사)
                    .name("빈야사")
                    .teacher(박경란)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화2 = Classes.builder()
                    .classType(힐링교정)
                    .name("힐링 + 교정")
                    .teacher(박경란)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화3 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(박경란)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화4 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(이채린)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화5 = Classes.builder()
                    .classType(빈야사)
                    .name("빈야사")
                    .teacher(이채린)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화6 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();

            Classes 화7 = Classes.builder()
                    .classType(힐링)
                    .name("힐링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.TUE)
                    .maxMemberNumber(30)
                    .build();


            /** 수요일 **/

            Classes 수1 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수2 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수3 = Classes.builder()
                    .classType(인사이드_플로우)
                    .name("인사이드 플로우")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수4 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수5 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(박경란)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수6 = Classes.builder()
                    .classType(인스프릿)
                    .name("인스프릿")
                    .teacher(박경란)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            Classes 수7 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.WED)
                    .maxMemberNumber(30)
                    .build();

            /** 목요일 **/

            Classes 목1 = Classes.builder()
                    .classType(발레핏)
                    .name("발레핏")
                    .teacher(이채린)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목2 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1")
                    .teacher(이채린)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목3 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목4 = Classes.builder()
                    .classType(사이드_코어)
                    .name("사이드코어")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목5 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목6 = Classes.builder()
                    .classType(밸런스_교정)
                    .name("밸런스교정")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            Classes 목7 = Classes.builder()
                    .classType(플라잉LV1)
                    .name("플라잉 LV.1 ")
                    .teacher(최혜미)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.THU)
                    .maxMemberNumber(30)
                    .build();

            /** 금요일 **/

            Classes 금1 = Classes.builder()
                    .classType(테라피)
                    .name("테라피")
                    .teacher(박경란)
                    .startTime(LocalTime.of(9, 30))
                    .endTime(LocalTime.of(10, 20))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금2 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(박경란)
                    .startTime(LocalTime.of(10, 35))
                    .endTime(LocalTime.of(11, 25))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금3 = Classes.builder()
                    .classType(기초플라잉)
                    .name("기초플라잉")
                    .teacher(박경란)
                    .startTime(LocalTime.of(11, 40))
                    .endTime(LocalTime.of(12, 30))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금4 = Classes.builder()
                    .classType(로우_플라잉)
                    .name("로우플라잉")
                    .teacher(이채린)
                    .startTime(LocalTime.of(18, 00))
                    .endTime(LocalTime.of(18, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금5 = Classes.builder()
                    .classType(발레핏)
                    .name("발레핏")
                    .teacher(이채린)
                    .startTime(LocalTime.of(19, 00))
                    .endTime(LocalTime.of(19, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금6 = Classes.builder()
                    .classType(젠링)
                    .name("젠링")
                    .teacher(이채린)
                    .startTime(LocalTime.of(20, 00))
                    .endTime(LocalTime.of(20, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();

            Classes 금7 = Classes.builder()
                    .classType(필라테스)
                    .name("필라테스")
                    .teacher(이채린)
                    .startTime(LocalTime.of(21, 00))
                    .endTime(LocalTime.of(21, 50))
                    .week(Week.FRI)
                    .maxMemberNumber(30)
                    .build();
//
//
            classesRepository.save(월1);
            classesRepository.save(월2);

            classesRepository.save(월3);
            classesRepository.save(월4);
            classesRepository.save(월5);
            classesRepository.save(월6);
            classesRepository.save(월7);
//
            classesRepository.save(화1);
            classesRepository.save(화2);
            classesRepository.save(화3);
            classesRepository.save(화4);
            classesRepository.save(화5);
            classesRepository.save(화6);
            classesRepository.save(화7);

            classesRepository.save(수1);
            classesRepository.save(수2);
            classesRepository.save(수3);
            classesRepository.save(수4);
            classesRepository.save(수5);
            classesRepository.save(수6);
            classesRepository.save(수7);
//
            classesRepository.save(목1);
            classesRepository.save(목2);
            classesRepository.save(목3);
            classesRepository.save(목4);
            classesRepository.save(목5);
            classesRepository.save(목6);
            classesRepository.save(목7);
//
            classesRepository.save(금1);
            classesRepository.save(금2);
            classesRepository.save(금3);
            classesRepository.save(금4);
            classesRepository.save(금5);
            classesRepository.save(금6);
            classesRepository.save(금7);
//
//            em.flush(); em.clear();

        }
    }
}
