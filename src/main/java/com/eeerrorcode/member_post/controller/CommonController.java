package com.eeerrorcode.member_post.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class CommonController {
  
  @GetMapping({"/", "/index"})
  public String index() {
    return "common/index";
  }

  @RequestMapping(value = "msg")
  public String msg(@ModelAttribute("msg") String msg, @Nullable String url, Model model) throws UnsupportedEncodingException {
    if(url != null) {
      int idx = url.indexOf("?url=") + 5;
      url = url.substring(0, idx) + URLEncoder.encode(url.substring(idx), "utf-8");
    }
    model.addAttribute("url", url);
    return "common/msg";
  }
}
