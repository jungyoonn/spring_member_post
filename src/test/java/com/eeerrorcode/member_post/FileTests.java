package com.eeerrorcode.member_post;

import java.io.File;
import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FileTests {
  @Test
  public void testDeleteFiles() {
    File file = new File("c:/upload/2024/12/19", "e67798e4-6f0a-45e5-8f2c-c1aad25edd46.bmp");
    file.delete();
  }

  @Test
  public void testListFiles() {
    File file = new File("c:/upload/2024/12/19");
    log.info(file.isDirectory());
    log.info(file.isFile());
    
    new ArrayList<>(Arrays.asList(file.listFiles(pathname -> pathname.getName().endsWith("jpg")))).forEach(log::info);
  }
}
