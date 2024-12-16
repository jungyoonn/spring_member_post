package com.eeerrorcode.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eeerrorcode.member_post.service.MemberService;
import com.eeerrorcode.member_post.vo.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("member")
@Log4j2
@AllArgsConstructor
public class MemberController {

  // /member/signin

  // return type
  // String : '해당 위치의 jsp' (ex: /WEB-INF/views/member/signin.jsp)로 foward
  // void : 접속 requestURI 위치를 반환 foward

  private MemberService service;

  @RequestMapping(value = "signin", method=RequestMethod.GET)
  public void signin() {  }
  
  @PostMapping("signin")
  public String postSignin(
    Member member, @RequestParam(required = false, value = "remember-id")String remember,
    HttpSession session, RedirectAttributes rttr, HttpServletResponse resp
    ) {
    log.info("아이디 기억? " + remember);
    log.info("멤버? " + member);

    if(service.login(member.getId(), member.getPw())) {
      // 로그인 성공

      // 1. 세션에 member라는 이름의 attribute
      session.setAttribute("member", service.findBy(member.getId()));

      // 1-1. 아이디 저장 시 cookie에 remember-id를 지정
      Cookie cookie = new Cookie("remember-id", member.getId());
      cookie.setPath("/");
      if(remember != null) {
        cookie.setMaxAge(60 * 60 * 24 * 7);
      } else {
        cookie.setMaxAge(0);
      }
      resp.addCookie(cookie);

      // 2. redirect index
      return "redirect:/";
    } else {
      // 로그인 실패
      // return "redirect:signin?msg=failed";
      // rttr.addAttribute("msg", "failed");
      rttr.addFlashAttribute("msg", "failed");
      // flashAttribute : 일회성 attribute
      return "redirect:signin";
    }
  }
  
  @RequestMapping("logout")
  public String requestMethodName(HttpSession session) {
    session.invalidate();
      return "redirect:/";
  }
  
  @GetMapping("signup")
  public void signup() {
    log.info("signup");
  }
  
  @PostMapping("signup")
  public String postSignup(Member member) {
      service.register(member);
      return "redirect:signin";
  }
  
}
