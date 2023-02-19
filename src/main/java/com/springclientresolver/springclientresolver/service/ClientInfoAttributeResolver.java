package com.springclientresolver.springclientresolver.service;

import com.springclientresolver.springclientresolver.models.ClientInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Class that extract user agent string from HTTP request and uses any implementation of {@link ClientResolverService}
 * to prepare the {@link ClientInfo} object.
 * To use this with the default settings, Create a configuration class or add the method to your existing configuration
 * class.
 *
 * <pre>{@code
 * @Configuration
 * @EnableWebMvc
 * public class ResolverConfig implements WebMvcConfigurer {
 *     @Override
 *     public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
 *         argumentResolvers.add(ClientInfoAttributeResolver.getDefaults());
 *     }
 * }}
 * </pre>
 *
 * @param <T> is type of {@link ClientResolverService} that will be used as the user-agent parser. We can create any custom
 *            implementation of this as per our liking. By default, it uses {@link UaParserClientResolverService}.
 */
@RequiredArgsConstructor
public class ClientInfoAttributeResolver<T extends ClientResolverService> implements HandlerMethodArgumentResolver {

    /**
     * HTTP header name that holds the user agent string.
     */
    private static final String USER_AGENT_HEADER = "user-agent";

    /**
     * {@link ClientResolverService} under use.
     */
    private final T clientResolverService;

    /**
     * Used to make {@link ClientInfoAttributeResolver} be available as one of the {@link HandlerMethodArgumentResolver}
     * with {@link UaParserClientResolverService} as the default parser service.
     *
     * @return Bean that will be added to the list of {@link HandlerMethodArgumentResolver} in your configuration.
     * @see ClientInfoAttributeResolver for more details for the details on how to configure.
     */
    public static HandlerMethodArgumentResolver getDefaults() {
        return new ClientInfoAttributeResolver<>(
                new UaParserClientResolverService()
        );
    }

    /**
     * Used by Spring internally to determine if this Class can be used for a specific type of object intection in the
     * controller.
     *
     * @param parameter Current list of parameters injected in controller method.
     * @return true or false depending on the parameter type has the supported class or not.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(ClientInfo.class);
    }

    /**
     * Used to perform the actual transformation from HTTP request to {@link ClientInfo} model.
     *
     * @param parameter     Method Parameter
     * @param mavContainer  MVC Container
     * @param webRequest    HTTP request where we can get the provided User Agent String
     * @param binderFactory Binder Factory
     * @return {@link  Object} of type {@link ClientInfo}
     */
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
