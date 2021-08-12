package hcy.gym.controller;

import hcy.gym.argumentresolver.Login;
import hcy.gym.domain.Post;
import hcy.gym.dto.member.MemberResponseDTO;
import hcy.gym.dto.page.PageRequestDTO;
import hcy.gym.dto.page.PageResponseDTO;
import hcy.gym.dto.post.PostModifyDTO;
import hcy.gym.dto.post.PostRequestDTO;
import hcy.gym.dto.post.PostResponseDTO;
import hcy.gym.repository.post.PostSearch;
import hcy.gym.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @ModelAttribute("memberResponseDTO")
    public MemberResponseDTO memberResponseDTO(@Login MemberResponseDTO loginMember) {
        return loginMember;
    }

    @GetMapping("/new")
    public String postForm(@ModelAttribute PostRequestDTO postRequestDTO) {
        return "posts/new";
    }

    @PostMapping("/new")
    public String postSave(@Validated @ModelAttribute PostRequestDTO postRequestDTO, BindingResult bindingResult,
                           @Login MemberResponseDTO loginMember) {

        log.info("Post Controller save request : {}", postRequestDTO);

        if (postRequestDTO.getContent() != null) {
            // 내용이 10자가 안되면 오류..
            if (postRequestDTO.getContent().length() < 10) {
                bindingResult.reject("contentLength");
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors : {}", bindingResult);
            return "posts/new";
        }

        postRequestDTO.setMemberId(loginMember.getId());
        Long result = postService.save(postRequestDTO);
        log.info("Post Save Complete : {}", result);

        return "redirect:/posts";
    }

    // post list view
    @GetMapping
    public String postList(@ModelAttribute("postSearch") PostSearch postSearch
                           ,@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

        log.info("Post Controller post list request : {}", pageRequestDTO);
        log.info("postSearch : {}", postSearch);

        PageResponseDTO<Post, PostResponseDTO> result = postService.getList(postSearch, pageRequestDTO);

        model.addAttribute("posts", result);

        return "posts/list";
    }

    // post view
    @GetMapping("/{id}")
    public String postView(@PathVariable("id") Long id, Model model) {

        log.info("Post Controller post view request : {}", id);

        PostResponseDTO result = postService.getOne(id);

        model.addAttribute("post", result);

        return "posts/read";
    }

    // modify post form
    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable("id") Long id, Model model) {

        log.info("Post Controller modify post form : {}", id);

        PostResponseDTO result = postService.getOne(id);

        model.addAttribute("postResponseDTO", result);

        return "posts/modify";
    }

    // modify post view
    @PostMapping("/modify/{id}")
    public String modifyPost(@PathVariable("id") Long id, @ModelAttribute PostModifyDTO postModifyDTO,
                             RedirectAttributes redirectAttributes) {

        log.info("Post Controller modify post request : {} {}", id, postModifyDTO);

        postModifyDTO.setId(id);

        postService.modify(postModifyDTO);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/posts/{id}";
    }

    // remove post
    @PostMapping("/remove/{id}")
    public String removePost(@PathVariable("id") Long id) {

        log.info("Post Controller remove post request : {}", id);

        postService.remove(id);

        return "redirect:/posts";
    }

}
