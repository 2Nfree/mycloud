package com.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//ribbon 负载均衡规则配置类
@Configuration
public class MyselfRule {
    @Bean
    public IRule iRule(){
        return new RandomRule();//定义为随机
    }
}
