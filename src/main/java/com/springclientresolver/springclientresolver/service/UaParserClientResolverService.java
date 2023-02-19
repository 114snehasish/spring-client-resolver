package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import org.springframework.util.StringUtils;
import ua_parser.Client;
import ua_parser.Parser;

/**
 * Default implementation of {@link ClientResolverService} that uses {@link Parser} to process incoming User Agent String.
 */
public class UaParserClientResolverService implements ClientResolverService {
    Parser uaParser;

    /**
     * Default Constructor which uses {@link Parser}
     */
    public UaParserClientResolverService() {
        uaParser = new Parser();
    }

    /**
     * Formats major and minor version strings in <code>major.minor</code> format.
     *
     * @param major major version string
     * @param minor minor version string
     * @return formatted single version string
     */
    private static String formatVersion(String major, String minor) {
        if (isNull(major))
            major = "0";
        if (isNull(minor))
            minor = "0";
        return String.format("%s.%s", major, minor);
    }

    private static boolean isNull(String string) {
        return !StringUtils.hasText(string) || "null".equalsIgnoreCase(string);
    }

    /**
     * Creates {@link ClientInfo} out of provided User Agent String
     *
     * @param userAgentString user agent string to be parsed into {@link ClientInfo}
     * @return parsed {@link ClientInfo}
     */
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
