package com.bing.lan;

import com.netflix.client.ClientFactory;
import com.netflix.client.IClient;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;


/**
 * Created by backend.
 */

public class RibbonClient {

  public static void main(String[] args) throws Exception {
    // 加载配置文件
    ConfigurationManager.loadCascadedPropertiesFromResources("ribbon-client");

    ILoadBalancer loadBalancer = ClientFactory.getNamedLoadBalancer("MyRibbonClient");
    IRule chooseRule = new RoundRobinRule();
    chooseRule.setLoadBalancer(loadBalancer);
    for (int i = 0; i < 10; i++) {
      Server server = chooseRule.choose(null);
      System.out.println("request ：" + server.getHostPort());
    }

    System.out.println("main(): ============");

    for (int i = 0; i < 10; i++) {
      Server server = loadBalancer.chooseServer(null);
      System.out.println("request ：" + server.getHostPort());
    }
  }
}
