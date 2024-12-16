package com.eeerrorcode.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.dto.PageDto;
import com.eeerrorcode.member_post.service.PostService;
import com.eeerrorcode.member_post.vo.Post;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("post")
@AllArgsConstructor
public class PostController {
  private PostService service;

  @GetMapping("list")
  public void list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
    model.addAttribute("pageDto", new PageDto(cri, service.count(cri)));
  }
  
  @GetMapping("view")
  public void view(@ModelAttribute("cri") Criteria cri, Long pno, Model model) {
    model.addAttribute("post", service.view(pno));
  }

  @GetMapping("write")
  public void write(@ModelAttribute("cri") Criteria cri) {}
  
  @PostMapping("write")
  public String postWrite(Post post, Criteria cri) {
    post.setCno(cri.getCategory());
    service.write(post);
    return "redirect:list?" + cri.getQs2();
  }
  
}
