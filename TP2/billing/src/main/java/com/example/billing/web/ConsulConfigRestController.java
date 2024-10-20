package com.example.billing.web;

import java.util.Map;

import com.example.billing.MyConsulConfig;
import com.example.billing.MyVaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConsulConfigRestController {

    @Autowired
    private MyConsulConfig myConsulConfig;
    @Autowired
    private MyVaultConfig myVaultConfig;

    /*@Value("${token.accessTokenTimeout}")
    private long accessTokenTimeout;
    @Value("${token.refreshTokenTimeout}")
    private long refreshTokenTimeout;*/

    @GetMapping("/myConfig")
    public Map<String, Object> myConfig(){

    return Map.of("consulConfig", myConsulConfig, "vaultConfig", myVaultConfig);
    }



}
