package com.eeerrorcode.member_post.aop;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.eeerrorcode.member_post.exception.NotMyPostException;
import com.eeerrorcode.member_post.exception.UnsignedAuthException;
import com.eeerrorcode.member_post.vo.Member;
import com.eeerrorcode.member_post.vo.Post;

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

  @Before("@annotation(com.eeerrorcode.member_post.aop.annotation.SigninCheck)")
  public void signinCheck(JoinPoint jp) throws IOException {
    log.info(req.getRequestURL());
    log.info(req.getRequestURI());
    log.info(req.getQueryString());
    if(session.getAttribute("member") == null) {
      String url = "/member/signin?url=" + URLEncoder.encode(req.getRequestURI() + "?" + req.getQueryString(), "utf-8");
      resp.sendRedirect("/msg?msg=" + URLEncoder.encode("로그인이 필요한 서비스입니다.", "utf-8") + "&url=" + url);
    }
  }

  @Before("@annotation(com.eeerrorcode.member_post.aop.annotation.MyPost)")
  public void myPost(JoinPoint joinPoint){
    log.info("진입했음");
    Object o = session.getAttribute("member");

    if(o == null) {
      throw new UnsignedAuthException("로그인이 필요한 서비스입니다");
    }
    
    String id = ((Member) o).getId();

    Object[] args = joinPoint.getArgs();
    for(Object obj : args) {
      if(obj instanceof Post && !((Post)obj).getWriter().equals(id)) {
        throw new NotMyPostException("본인이 작성한 게시글이 아닙니다");
      }
    }
    log.info(Arrays.toString(args));
    log.info(id);

    log.info("메서드 끝");
  }
}
