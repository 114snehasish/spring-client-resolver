package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;

public interface ClientResolverService {
    ClientInfo resolveClient(String userAgentString);
}
