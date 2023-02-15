package com.springclientresolver.springclientresolver.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientInfo {
    private String name;
    private String version;
    private String os;
    private String osVersion;
    private String type;

}
