package com.springclientresolver.springclientresolver.controller;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public ClientInfo getClientInfo(ClientInfo clientInfo) {
        return clientInfo;
    }
}
