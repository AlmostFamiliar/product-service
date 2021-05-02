package com.github.almostfamiliar.product.web;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {

  public static String readStringFromResource(String filename) throws IOException {
    final File file = ResourceUtils.getFile("classpath:" + filename);
    return Files.readString(file.toPath());
  }
}
