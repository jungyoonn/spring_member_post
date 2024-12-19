package com.eeerrorcode.member_post.aop.advice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eeerrorcode.member_post.exception.NotMyPostException;
import com.eeerrorcode.member_post.exception.UnsignedAuthException;

@ControllerAdvice
public class MyControllerAdvice {
  @ExceptionHandler({UnsignedAuthException.class, NotMyPostException.class})
  public String unsignedAuthException(Exception ex) throws UnsupportedEncodingException { // 우리가 발생시킨 예외 객체가 파라미터에 담김
    // String url = "/msg?msg=" + ex.getMessage() + "&url=/member/signin";
    String msg = URLEncoder.encode(ex.getMessage(), "utf-8");
    // url = URLEncoder.encode(url, "utf-8");
    return "redirect:/msg?msg=" + msg + "&url=/member/signin";
  }
}
