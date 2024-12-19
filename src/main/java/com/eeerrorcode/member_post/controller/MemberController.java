package com.eeerrorcode.member_post.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
  InternalResourceViewResolver resolver;

  @GetMapping("mv")
  public ModelAndView mv(ModelAndView mav) {
    mav.addObject("test", "abcd");
    mav.setViewName("common/index");
    log.info(mav);
    return mav;
  }
  

  @RequestMapping(value = {"", "*"}, method = RequestMethod.GET) @ResponseBody
  public Member all() {
    // DispatcherServlet servlet;
    log.info(resolver);
    log.info(resolver.getAttributesMap());
    log.info(resolver.getOrder());
    return new Member();
  }
  
  // @RequestMapping(value = {"", "*"}, method = RequestMethod.PUT) @ResponseBody
  // public Member all2() {
  //   log.info("에에에");
  //   return new Member();
  // }

  @RequestMapping(value = "signin", method=RequestMethod.GET)
  public void signin() {  }
  
  @PostMapping("signin")
  public String postSignin(
    Member member, @RequestParam(required = false, value = "remember-id")String remember,
    HttpSession session, RedirectAttributes rttr, HttpServletResponse resp, @RequestParam("url") @Nullable String url
    ) throws UnsupportedEncodingException {
    log.info("아이디 기억? " + remember);
    log.info("멤버? " + member);
    log.info(member);

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
      log.info("이얍 url!!!!!! " + url);
      // if(req.getQueryString() !=  null) {
      //   log.info("이얍 쿼리스트링! " + req.getQueryString());
      //   String url2 = req.getQueryString();
        
      //   log.info("이얍 짤린 쿼리스트링! " + url2.substring(url2.indexOf("=") + 1));
      //   log.info("이얍 uri!!! " + req.getRequestURI());
      //   url2 = url2.substring(url2.indexOf("=") + 1);
      //   return "redirect:" + url2;
      // }
      String redirectUrl = "/";
      if(url != null) {
        redirectUrl = url;
      }
      return "redirect:" + redirectUrl;
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
