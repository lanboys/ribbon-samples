package com.bing.lan.config;

import com.netflix.config.ConfigurationManager;

import org.apache.commons.configuration.AbstractConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by oopcoder at 2022/7/17 8:52 .
 */

public class ConfigurationManagerTest {

  AbstractConfiguration configInstance;

  @Before
  public void loadCascadedPropertiesFromResources() throws IOException {
    // 不加载自定义配置文件的话，里面就是一些系统属性值
    ConfigurationManager.loadCascadedPropertiesFromResources("load");
    configInstance = ConfigurationManager.getConfigInstance();
  }

  @Test
  public void getKeys() {
    // 不加前缀，遍历全部属性
    // for (Iterator<String> keys = configInstance.getKeys(); keys.hasNext(); ) {
    // 通过前缀遍历
    for (Iterator<String> keys = configInstance.getKeys("a"); keys.hasNext(); ) {
      String key = keys.next();
      Object property = configInstance.getProperty(key);

      System.out.println(">>>>>>>>>>>>> " + key + " = " + property);
    }
  }

  @Test
  public void setProperty() {
    configInstance.setProperty("g.h.r", "www");
    Object property = configInstance.getProperty("g.h.r");
    System.out.println(property);
  }
}
