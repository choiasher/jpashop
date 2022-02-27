package jpabook.jpashop.global;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        final ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);
        final ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(response);

        if (log.isDebugEnabled() && cachingRequest.getContentType() != null && isJson(cachingRequest)) {
            JsonNode json = objectMapper.readTree(cachingRequest.getContentAsByteArray());
            Map<String, String[]> parameterMap = cachingRequest.getParameterMap();
            log.debug("request parameter map : {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(parameterMap));
            log.debug("request body : {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        }

        if (log.isDebugEnabled() && cachingResponse.getContentType() != null && isJson(cachingResponse)) {
            JsonNode json = objectMapper.readTree(cachingResponse.getContentAsByteArray());
            log.debug("response body : {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        }
    }

    private boolean isJson(ContentCachingRequestWrapper cachingRequest) {
        return cachingRequest.getContentType().contains("application/json");
    }

    private boolean isJson(ContentCachingResponseWrapper cachingResponse) {
        return cachingResponse.getContentType().contains("application/json");
    }
}
