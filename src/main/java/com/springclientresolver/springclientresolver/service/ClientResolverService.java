package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;

/**
 * Base contract for any Client Resolver Service to be used with {@link ClientInfoAttributeResolver}
 */
public interface ClientResolverService {
    /**
     * Contract for resolving string into {@link ClientInfo}
     *
     * @param userAgentString User Agent String
     * @return {@link ClientInfo}
     */
    ClientInfo resolveClient(String userAgentString);
}
