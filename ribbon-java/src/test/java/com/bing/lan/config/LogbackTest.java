package com.bing.lan.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by oopcoder at 2022/7/17 10:12 .
 */

public class LogbackTest {

  @Test
  public void testLog() {
    System.out.println("testLog(): =====================");

    Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");

    logger = LoggerFactory.getLogger(Integer.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");
  }
}
