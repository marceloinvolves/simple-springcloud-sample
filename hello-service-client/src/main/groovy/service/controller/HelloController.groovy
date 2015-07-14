package service.controller

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.client.HelloServiceRepository

@RefreshScope
@RestController
@RequestMapping("/hello")
class HelloController {
    @Autowired
    HelloServiceRepository say

    @HystrixCommand(fallbackMethod = "defaultHello")
    @RequestMapping(produces = "application/json;charset=utf-8")
    String sayHello() {
        say.sayHello()
    }

    def defaultHello() {
        "No hello for you!"
    }
}