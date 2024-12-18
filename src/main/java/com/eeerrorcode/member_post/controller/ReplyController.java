package com.eeerrorcode.member_post.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.eeerrorcode.member_post.dto.ReplyCri;
import com.eeerrorcode.member_post.service.ReplyService;
import com.eeerrorcode.member_post.vo.Member;
import com.eeerrorcode.member_post.vo.Reply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("reply")
@AllArgsConstructor
@Log4j2
public class ReplyController {
  private ReplyService service;

  // 목록 조회
  @GetMapping({"list/{pno}", "list/{pno}/{lastRno}", "list/{pno}/{lastRno}/{amount}"})
  public Map<?, ?> list(@SessionAttribute(required = false) Member member, @PathVariable Long pno, 
  ReplyCri cri, @PathVariable(required = false) Optional<Long> lastRno, @PathVariable(required = false) Optional<Integer> amount) {
    cri.setAmount(amount.orElse(10));
    // cri.setAmount(amount.orElseGet(() -> 10)); orElseGet으로도 위와 같이 할 수 있다
    cri.setLastRno(lastRno.orElse(0L));
    log.info(cri);
    return service.list(pno, cri, member);
  }
  
  // 단일 조회
  // @GetMapping("view/{rno}")
  // public Reply rp(@PathVariable(required = false) Long rno) {
  //   log.info(service.findBy(rno));
  //   return service.findBy(rno);
  // }
  @Operation(summary = "reply single select", description = "@PathVariable인 rno 값을 입력 받고 해당 댓글을 json으로 리턴")
  @GetMapping("{rno}")
  public ResponseEntity<?> view(@PathVariable(required = false) Long rno) {
    return ResponseEntity.ok().body(service.findBy(rno));
  }

  // 등록
  @Operation(summary = "댓글 작성", description = "댓글 작성을 위해 필요한 값을 전달받음 content, writer, rno",
  responses = {
    @ApiResponse(responseCode = "200", description = "작성 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
    @ApiResponse(responseCode = "500", description = "작성 실패")
    }
  )
  @PostMapping
  public ResponseEntity<?> write(@RequestBody Reply reply) {
    // @RequestBody는 파라미터로 수집된 값들을 json 몸통 형태로 바인딩 하여 받아오는 어노테이션
    // RequestBody를 안 쓰면 일반 쿼리스트링 형태로 받아오게 된다
    log.info(reply);
    // service.write(reply);
    return service.write(reply) > 0 ? ResponseEntity.ok().body("success") : ResponseEntity.internalServerError().build();
  }

  // 수정
  @PutMapping
  public ResponseEntity<?> modify(@RequestBody Reply reply) {
    service.modify(reply);
    return ResponseEntity.ok().body("success");
  }

  // 삭제
  @DeleteMapping("{rno}")
  public ResponseEntity<?> remove(@PathVariable Long rno) {
    service.remove(rno);
    return ResponseEntity.ok().body("success");
  }
}
