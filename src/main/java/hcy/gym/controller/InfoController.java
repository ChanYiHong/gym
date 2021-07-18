package hcy.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/way")
    public String wayToCome() {
        return "info/wayToCome";
    }

}
