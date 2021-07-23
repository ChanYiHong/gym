package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.classes.ClassesResponseDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.payment.PaymentInfoDTO;
import hcy.gym.service.classes.ClassesService;
import hcy.gym.service.payment.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/classes")
@RequiredArgsConstructor
@Slf4j
public class ClassesController {

    private final ClassesService classesService;
    private final PaymentService paymentService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    // 시간표 화면
    @GetMapping
    public String classes(@Login MemberResponseDTO loginMember, Model model) {

        makeClasses(model);

        // 멤버쉽 결제 정보
        PaymentInfoDTO paymentInfo = paymentService.getByMemberId(loginMember.getId());
        model.addAttribute("payment", paymentInfo);

        // 현재 날짜 정보
        makeNowDate(model);

        makeWeek(model);

        return "classes/table";
    }

    private void makeClasses(Model model) {
        // 1교시 부터 7교시
        List<ClassesResponseDTO> one = classesService.getByStartTime(LocalTime.of(9, 30));
        List<ClassesResponseDTO> two = classesService.getByStartTime(LocalTime.of(10, 35));
        List<ClassesResponseDTO> three = classesService.getByStartTime(LocalTime.of(11, 40));
        List<ClassesResponseDTO> four = classesService.getByStartTime(LocalTime.of(18, 00));
        List<ClassesResponseDTO> five = classesService.getByStartTime(LocalTime.of(19, 00));
        List<ClassesResponseDTO> six = classesService.getByStartTime(LocalTime.of(20, 00));
        List<ClassesResponseDTO> seven = classesService.getByStartTime(LocalTime.of(21, 00));

        model.addAttribute("one", one);
        model.addAttribute("two", two);
        model.addAttribute("three", three);
        model.addAttribute("four", four);
        model.addAttribute("five", five);
        model.addAttribute("six", six);
        model.addAttribute("seven", seven);
    }

    private void makeNowDate(Model model) {
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("year", now.getYear());
        model.addAttribute("month", now.getMonthValue());
        model.addAttribute("day", now.getDayOfMonth());
        model.addAttribute("hour", now.getHour());
        model.addAttribute("minute", now.getMinute());
    }

    private void makeWeek(Model model) {

        LocalDate now = LocalDate.now();

        // 시간표 날짜 설정. 현재 날짜 기준으로 이번주, 다음주만 가능하게
        WeekFields weekFields = WeekFields.of(Locale.KOREA);
        // 몇 주차 인지..
        int weekNum = now.get(weekFields.weekOfWeekBasedYear());

        // 이번주 계산.
        int dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate firstDayOfWeek = now.minusDays(dayOfWeek);

        List<WeekInfo> thisWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            thisWeek.add(new WeekInfo(firstDayOfWeek.plusDays(i)));
        }

        model.addAttribute("thisWeekNum", weekNum);
        model.addAttribute("thisWeek", thisWeek);

        // 다음주 계산.
        LocalDate firstDayOfNextWeek = now.plusDays((7 - dayOfWeek) + 1);
        int nextWeekNum = firstDayOfNextWeek.get(weekFields.weekOfWeekBasedYear());
        List<WeekInfo> nextWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            nextWeek.add(new WeekInfo(firstDayOfNextWeek.plusDays(i)));
        }

        model.addAttribute("nextWeekNum", nextWeekNum);
        model.addAttribute("nextWeek", nextWeek);
    }

    @Data
    @AllArgsConstructor
    static class WeekInfo {
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
    }

}
