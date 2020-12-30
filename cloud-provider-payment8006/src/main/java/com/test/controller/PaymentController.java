package com.test.controller;

import com.test.entities.CommonResult;
import com.test.entities.Payment;
import com.test.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value="/payment/consul")
    public String PaymentConsul(){

        return "SpringCloud with Consul："+serverPort+"\t"+ UUID.randomUUID().toString();
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("____插入结果:"+ result);
        if (result>0){
            return new CommonResult(200,"插入成功，端口号："+serverPort,result);
        }else{
            return new CommonResult(400,"插入失败，端口号："+serverPort,null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("____查询结果"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功，端口号："+serverPort,payment);
        }else{
            return new CommonResult(400,"没有对应记录，查询id："+id+"，端口号："+serverPort,null);
        }

    }

}
