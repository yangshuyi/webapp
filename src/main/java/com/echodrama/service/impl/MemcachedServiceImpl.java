package com.echodrama.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.echodrama.service.MemcachedService;
import org.springframework.stereotype.Service;


/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/8/14
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 * <p/>
 * http://www.360doc.com/content/11/0512/10/18042_116131751.shtml - Spring的Context初始化之后Bean的init的场景解决方法
 */
@Service("memcachedService")
public class MemcachedServiceImpl implements MemcachedService {
    //    @Autowired
    //    private MemcachedClient memcachedClient;

    @PostConstruct
    public void init() throws Exception {
//           memcachedClient.
//        MemcachedClientBuilder builder = new MyMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11212"), new int[]{1});
//
//        builder.setConnectionPoolSize(5); //设置连接池
//        builder.setSocketOption(StandardSocketOption.SO_RCVBUF, 32 * 1024); // 设置接收缓存区为32K，默认16K
//        builder.setSocketOption(StandardSocketOption.SO_SNDBUF, 16 * 1024); // 设置发送缓冲区为16K，默认为8K
//        builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false); // 启用nagle算法，提高吞吐量，默认关闭
//
//        builder.getConfiguration().setSessionIdleTimeout(10000);  // 设置为10秒,连接超过5秒没有任何IO操作发生即认为空闲并发起心跳检测
//        builder.getConfiguration().setStatisticsServer(false); // 禁止统计连接是否空闲
//
//        MemcachedClient client = builder.build();
//        client.setEnableHeartBeat(false);   //关闭心跳检测，减小系统开销
//
//        client.set("APP_NAME", 0, "EchoDrama");


    }

    @PreDestroy
    public void destroy() {
//        memcachedClient.shutdown();
    }
}
