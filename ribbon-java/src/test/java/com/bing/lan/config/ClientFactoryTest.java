package com.bing.lan.config;

import com.netflix.client.ClientFactory;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.config.ConfigurationManager;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by oopcoder at 2022/7/17 15:23 .
 */

public class ClientFactoryTest {

  @Before
  public void loadCascadedPropertiesFromResources() throws IOException {
    // 也可以不加载配置文件，那么下面获取的 clientConfig 就都是默认值，这一步的作用是自定义一些属性来覆盖默认值
    ConfigurationManager.loadCascadedPropertiesFromResources("ribbon-client");
  }

  @Test
  public void getNamedConfig() {
    // 通过客户端名称来获取 clientConfig，如果配置文件中如果有同名前缀配置，就会覆盖默认值

    // 有同名前缀配置，覆盖部分属性的客户端
    IClientConfig clientConfig = ClientFactory.getNamedConfig("MyRibbonClient");
    System.out.println("getNamedConfig(): " + clientConfig);

    // 有同名前缀配置，覆盖部分属性的客户端
    IClientConfig clientConfig2 = ClientFactory.getNamedConfig("MyRibbonClient2");
    System.out.println("getNamedConfig(): " + clientConfig2);

    // 无同名前缀配置，全部是默认值的客户端
    IClientConfig clientConfig3 = ClientFactory.getNamedConfig("aaaaa");
    System.out.println("getNamedConfig(): " + clientConfig3);

    // 无同名前缀配置，全部是默认值的客户端
    IClientConfig clientConfig4 = ClientFactory.getNamedConfig("bbbbb");
    System.out.println("getNamedConfig(): " + clientConfig4);
  }

  @Test
  public void getCustomNameSpaceConfig() {
    // 使用自定义的 IClientConfig

    // 有同名前缀配置，覆盖部分属性的客户端
    IClientConfig clientConfig = ClientFactory.getNamedConfig("MyCustomRibbonClient", MyClientConfig.class);
    System.out.println("getNamedConfig(): " + clientConfig);

    // 无同名前缀配置，全部是默认值的客户端
    IClientConfig clientConfig2 = ClientFactory.getNamedConfig("ccccc", MyClientConfig.class);
    System.out.println("getNamedConfig(): " + clientConfig2);
  }

  public static class MyClientConfig extends DefaultClientConfigImpl {

    public MyClientConfig() {
      // 自定义新的命名空间
      super("custom-namespace-ribbon");
    }
  }
}
