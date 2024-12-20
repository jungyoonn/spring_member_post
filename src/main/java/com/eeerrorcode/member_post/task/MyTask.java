package com.eeerrorcode.member_post.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@EnableScheduling
public class MyTask {

  // @Scheduled가 지정된 메서드는 파라미터 x, 반환 값 x
  @Async
  @Scheduled(cron = "0/5 * * * * *")
  public void printTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd - hh:mm:ss:SSS");
    Date date = new Date();
    log.info(format.format(date));
  }
}
