package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class ClientInfoAttributeResolver<T extends ClientResolverService> implements HandlerMethodArgumentResolver {

    private static final String USER_AGENT_HEADER = "user-agent";
    private final T clientResolverService;

    public static HandlerMethodArgumentResolver getDefaults() {
        return new ClientInfoAttributeResolver<>(
                new UaParserClientResolverService()
        );
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(ClientInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest != null)
            return clientResolverService.resolveClient(httpServletRequest.getHeader(USER_AGENT_HEADER));
        return null;
    }
}
