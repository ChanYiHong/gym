package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.dto.classes.ClassesResponseDTO;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.service.classes.ClassesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/classes")
@RequiredArgsConstructor
@Slf4j
public class ClassesController {

    private final ClassesService classesService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    // 시간표 화면
    @GetMapping
    public String classes(Model model) {

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

        return "classes/table";
    }

}
