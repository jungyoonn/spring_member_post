package com.eeerrorcode.member_post.aop;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.eeerrorcode.member_post.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@AllArgsConstructor
@Log4j2
public class AuthAspect {
  private HttpSession session;
  private HttpServletResponse resp;
  private HttpServletRequest req;

  @Before("@annotation(com.eeerrorcode.member_post.aop.MyPost)")
  public void myPost(JoinPoint joinPoint, MyPost myPost){
    Object o = session.getAttribute("member");
    String id = ((Member) o).getId();

    Object[] args = joinPoint.getArgs();
    String writeParam = myPost.value();
    log.info(Arrays.toString(args));
    log.info(id);
    log.info(writeParam);

    // if(o == null || ((Member)o).getId().equals(post.getWriter())) {
    //   throw new RuntimeException("본인 게시글 아님");
    // }
  }

  @Before("@annotation(com.eeerrorcode.member_post.aop.SigninCheck)")
  public void signinCheck(JoinPoint jp) throws IOException {
    log.info(req.getRequestURL());
    log.info(req.getRequestURI());
    log.info(req.getQueryString());
    if(session.getAttribute("member") == null) {
      String url = "/member/signin?url=" + URLEncoder.encode(req.getRequestURI() + "?" + req.getQueryString(), "utf-8");
      resp.sendRedirect("/msg?msg=" + URLEncoder.encode("로그인이 필요한 서비스입니다.", "utf-8") + "&url=" + url);
    }
  }
}
