package jpabook.jpashop.global;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 우선 필터를 통해 HttpServletResponse, HttpServletRequest 클래스로 들어온 request와 response를
 * ContentCachingRequestWrapper와 ContentCachingResponseWrapper로 래핑해주어야 한다.
 * 왜냐하면 HttpServletRequest 그대로 request.getReader 함수를 호출하거나 안에 있는 데이터를 읽으려고 하면,
 * 단 한번만 읽을 수 있도록 톰캣에서 만들어두었기 때문이다.
 * wrappingResponse.copyBodyToResponse()를 통해 body값을 copy해서 캐시로 저장함으로 다시 읽을 수 있다.
 */
@Component
public class CustomServletWrappingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(wrappingRequest, wrappingResponse);
        wrappingResponse.copyBodyToResponse();
    }
}