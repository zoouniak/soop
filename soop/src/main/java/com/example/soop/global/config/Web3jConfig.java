package com.example.soop.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
@RequiredArgsConstructor
public class Web3jConfig {
    @Value("${web3.private-key}")
    private String PRIVATE_KEY;
    @Value("${web3.ganache-url}")
    private String GANACHE_URL;


    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(GANACHE_URL));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(PRIVATE_KEY);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new DefaultGasProvider();
    }
}
