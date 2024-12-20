package com.eeerrorcode.member_post.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eeerrorcode.member_post.mapper.AttachMapper;
import com.eeerrorcode.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@EnableScheduling
@Component
@Log4j2
public class FileDeleteTask {
  @Autowired
  private AttachMapper mapper;

  @Scheduled(cron = "0 35 14 * * *")
  public void deleteFiles() {
    String date = "2024/12/19";

    List<Attach> files = new ArrayList<>(Arrays.asList(new File("c:/upload", date).listFiles())
    .stream()
    .map(Attach::fromFile)
    .toList());

    List<Attach> dbs = new ArrayList<>(mapper.selectListByPath(date));
    List<Attach> thumbs = new ArrayList<>(dbs);
    thumbs = dbs.stream().filter(Attach::isImage)
    
    .map(a -> Attach.builder().uuid("t_" + a.getUuid()).build())
    .toList();
    dbs.addAll(thumbs);
    files.removeAll(dbs);

    files.stream().peek(a -> a.setPath(date)).map(Attach::toFile).forEach(File::delete);

    log.info(files.size() + "개의 파일 삭제");

    // stream의 최종 연산 : forEach(파라미터 consumer), count(스트림의 개수 리턴, 파라미터 없음), Collect(Collectors.toList()) <- 얘는 그냥 외우기

    // stream의 중간 연산 : 최종 연산이 뒤에 안 붙으면 중간 연산이 먹지 않음, 반환 타입이 모두 스트림
    // filter(파라미터 predicaate), map(파라미터 function), peek(파라미터 consumer)
  }
}
