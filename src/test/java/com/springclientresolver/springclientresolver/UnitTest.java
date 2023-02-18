package com.springclientresolver.springclientresolver;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import com.springclientresolver.springclientresolver.service.ClientInfoAttributeResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UnitTest {
    @Mock
    MethodParameter parameter;
    @Mock
    ModelAndViewContainer mavContainer;
    @Mock
    NativeWebRequest webRequest;
    @Mock
    WebDataBinderFactory binderFactory;
    @Mock
    HttpServletRequest httpServletRequest;

    private HandlerMethodArgumentResolver setupTest(String userAgentString) {
        HandlerMethodArgumentResolver resolver = ClientInfoAttributeResolver.getDefaults();
        when(webRequest.getNativeRequest(HttpServletRequest.class)).thenReturn(httpServletRequest);
        when(httpServletRequest.getHeader(any())).thenReturn(userAgentString);
        return resolver;
    }

    @Test
    void resolveArgument() throws Exception {
        HandlerMethodArgumentResolver resolver = setupTest("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36");
        ClientInfo clientInfo = ((ClientInfo) resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
        assertNotNull(clientInfo);
    }


    @Test
    void unknownVersion() throws Exception {
        HandlerMethodArgumentResolver resolver = setupTest("Java/1.6.0_26");
        ClientInfo clientInfo = ((ClientInfo) resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
        assertNotNull(clientInfo);
    }

    @Test
    void resolveArgumentNullCase() throws Exception {
        HandlerMethodArgumentResolver resolver = ClientInfoAttributeResolver.getDefaults();
        ClientInfo clientInfo = ((ClientInfo) resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory));
        assertNull(clientInfo);
    }
}
