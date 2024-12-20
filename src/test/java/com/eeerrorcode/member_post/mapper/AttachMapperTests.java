package com.eeerrorcode.member_post.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AttachMapperTests {
  @Autowired
  private AttachMapper mapper;

  @Test
  public void testSelectAttach() {
    log.info(mapper.selectListByPath("2024/12/19"));

    String date = "2024/12/19";
    List<Attach> list = mapper.selectListByPath(date);
    list.forEach(log::info);
    log.info("=============================================");
    List<File> files = new ArrayList<>(Arrays.asList(new File("c:/upload", date).listFiles()));
    files.forEach(log::info);
    log.info(files.size());
    
    log.info("=============================================");
    List<File> list2 = list.stream().map(Attach::toFile).toList();
    log.info(list2.size());

    files.removeAll(list2);
    files.forEach(log::info);
    log.info(files.size());
  }

  @Test
  public void fromFileTest() {
    String date = "2024/12/19";
    List<Attach> files = new ArrayList<>(Arrays.asList(new File("c:/upload", date).listFiles())
    .stream()
    .map(Attach::fromFile)
    .toList());

    files.forEach(log::info);

    List<Attach> dbs = new ArrayList<>(mapper.selectListByPath(date));
    List<Attach> thumbs = new ArrayList<>(dbs);
    thumbs = dbs.stream().filter(Attach::isImage)
    .map(a -> Attach.builder().uuid("t_" + a.getUuid()).build())
    .toList();

    log.info(thumbs);
    log.info(thumbs.size());

    dbs.addAll(thumbs);
    log.info("dbs는 ::: " + dbs);
    log.info(dbs.size());

    log.info("=============================================");
    log.info(files.size());
    files.removeAll(dbs);
    files.forEach(log::info);
    log.info(files.size());
  }

  @Test
  public void testBi() {
    Map<String, Integer> map = new HashMap<>();
    map.put("A", 2);
    map.put("B", 3);
    map.put("C", 4);

    map.replaceAll((k, v) -> {
      return v * v;
    });

    map.forEach((k, v) -> {
      log.info(k + " ::: " + v);
    });
    map.forEach(log::info);
  }
}
