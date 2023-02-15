package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua_parser.Client;
import ua_parser.Parser;

@Service
public class UaParserClientResolverService implements ClientResolverService {
    Parser uaParser;

    public UaParserClientResolverService() {
        uaParser = new Parser();
    }

    private static String formatVersion(String major, String minor) {
        if (isNull(major))
            major = "0";
        if (isNull(minor))
            minor = "0";
        return String.format("%s.%s", major, minor);
    }

    private static boolean isNull(String major) {
        return !StringUtils.hasText(major) || "null".equalsIgnoreCase(major);
    }

    @Override
    public ClientInfo resolveClient(String userAgentString) {
        Client c = uaParser.parse(userAgentString);
        return ClientInfo.builder()
                .name(c.userAgent.family)
                .version(formatVersion(c.userAgent.major, c.userAgent.minor))
                .os(c.os.family)
                .osVersion(formatVersion(c.os.major, c.os.minor))
                .type(c.device.family)
                .build();

    }
}
