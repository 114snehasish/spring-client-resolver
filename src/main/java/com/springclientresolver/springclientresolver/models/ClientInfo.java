package com.springclientresolver.springclientresolver.models;

import lombok.Builder;
import lombok.Data;

/**
 * Model that can be injected to Controllers where Client Information is needed to have business functionality.
 *
 * @author Snehasish Chakraborty
 */
@Data
@Builder
public class ClientInfo {
    /**
     * Defines the browser/user agent name.
     */
    private String name;
    /**
     * Defines the browser/user agent version.
     */
    private String version;
    /**
     * Defines the client Operating System.
     */
    private String os;
    /**
     * Defines the client OS version.
     */
    private String osVersion;
    /**
     * Defines the client type such as desktop mobile etc.
     */
    private String type;

}
