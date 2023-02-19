package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;

/**
 * Base contract for any Client Resolver Service to be used with {@link ClientInfoAttributeResolver}
 */
public interface ClientResolverService {
    ClientInfo resolveClient(String userAgentString);
}
