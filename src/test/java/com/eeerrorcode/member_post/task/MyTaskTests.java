package com.eeerrorcode.member_post.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MyTaskTests {
  @Autowired
  private MyTask myTask;

  @Test
  public void nowTimeTest() {
    myTask.printTime();
  }

  @Test
  @DisplayName("Attach 객체의 equals()와 hashcode()의 재정의 확인 용도")
  public void testAttachEqualsAndHashCode() {
    Attach attach1 = Attach.builder().uuid("1.jpg").build();
    Attach attach2 = Attach.builder().uuid("1.jpg").build();
    Attach attach3 = Attach.builder().uuid("2.jpg").build();
    Attach attach4 = attach1;

    log.info(attach1.equals(attach2));
    log.info(attach1.equals(attach3));
    log.info(attach2.equals(attach3));

    assertTrue(attach1.equals(attach2));
    assertTrue(!attach1.equals(attach3));
    assertTrue(!attach2.equals(attach3));

    assertEquals(attach1, attach2);
    assertSame(attach1, attach4); // 아예 싸그리 같아야 true

    Set<Attach> attachs = new HashSet<>(Stream.of(attach1, attach2, attach3, attach4).collect(Collectors.toSet()));
    // attachs.add(attach1);
    // attachs.add(attach2);
    // attachs.add(attach3);
    attachs.forEach(log::info);
    log.info(attachs);
    log.info(attachs.size());
  }
}
