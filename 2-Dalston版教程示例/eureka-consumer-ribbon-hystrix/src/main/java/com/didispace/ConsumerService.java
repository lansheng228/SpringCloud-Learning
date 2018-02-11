package com.didispace;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String consumerDegrade() {
        return restTemplate.getForObject("http://eureka-client/dcDegrade", String.class);
    }

    public String fallback() {
        return "fallbck";
    }
}