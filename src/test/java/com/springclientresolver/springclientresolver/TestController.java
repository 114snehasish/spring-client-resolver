package com.springclientresolver.springclientresolver;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {
    @GetMapping
    public ClientInfo getClientInfo(ClientInfo clientInfo) {
        return clientInfo;
    }
}
