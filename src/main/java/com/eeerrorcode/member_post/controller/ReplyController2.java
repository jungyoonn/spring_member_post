package com.eeerrorcode.member_post.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eeerrorcode.member_post.dto.Criteria;
import com.eeerrorcode.member_post.service.PostService;
import com.eeerrorcode.member_post.vo.MyVo;
import com.eeerrorcode.member_post.vo.Post;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;


// @Controller
// @RestController // Controller 역할을 하면서 소유한 모든 메서드의 반환 형태가 @ResponseBody를 강제함
// 따라서 Controller + ResponseBody = RestController
// @RequestMapping("reply")
@Log4j2
public class ReplyController2 {
  @Autowired
  private PostService service;

  // @ResponseBody
  // @RequestBody
  // @PathVariable
  @RequestMapping("test")
  public String test() {
    log.info("도착 지점 확인 reply/test");
    return "hello";
  }

  // void String ResponseEntity
  // entity : 개체
  @RequestMapping("re")
  public ResponseEntity<String> re() {
    // return ResponseEntity.ok("my re");
    // return ResponseEntity.status(200).build();
    // return ResponseEntity.notFound().build();
    return new ResponseEntity<String>("본문", HttpStatus.NOT_FOUND);
  }

  @GetMapping(value = "arr", produces = "text/xml")
  public int[] getMethodName() {
      return new int[] {3,4,5,6};
  }
  
  @GetMapping("list")
  public List<String> list() {
    List<String> list = new ArrayList<>();
    list.add("가");
    list.add("나");
    list.add("다");
    list.add("라");
    return list;
  }
  
  @GetMapping("map")
  public Map<Integer, String> map() {
    Map<Integer, String> map = new HashMap<Integer,String>();
    map.put(1, "가");
    map.put(2, "나");
    map.put(3, "다");
    map.put(4, "라");

    return map;
  }

  @GetMapping("students")
  public List<?> students() {
    List<Map<?, ?>> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();

    map.put("no", 1);
    map.put("name", "요미");
    map.put("score", 90);
    list.add(map);

    map = new HashMap<>();
    map.put("no", 2);
    map.put("name", "쿠키");
    map.put("score", 70);
    list.add(map);

    return list;
  }
  
  @GetMapping("post")
  public Post post() {
    Post post = service.findBy(152L);
    return post;
  }
  
  @GetMapping("posts")
  public List<Post> posts() {
    List<Post> list = new ArrayList<>();
    Criteria cri = new Criteria();
    list.addAll(service.list(cri));

    return list;
  }

  @GetMapping("mypost")
  public Post myPost(Post post) {
    // 쿼리스트링에 파라미터를 수집해서 보여 줄 수도 있다
    return post;
  }
  
  
  @GetMapping("p1")
  public int[] p1(@RequestParam("arr") int[] arr) {
    return arr;
  }
  
  @GetMapping("p2")
  public List<?> p2(@RequestParam("arr") List<?> list) {
    return list;
  }
  
  @InitBinder
  public void init(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy_MM_dd"), false));
  }

  @GetMapping("myvo")
  public MyVo mv(MyVo myVo) {
      return myVo;
  }
  
}
