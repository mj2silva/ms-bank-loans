package com.ms.loans.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
    private static final String CORRELATION_ID_HEADER_NAME = "X-MsBank-Correlation-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        var correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);

        if (correlationId == null) {
            log.warn("Request path: {}. No correlation id found in header", request.getRequestURI());
        } else {
            log.info("Request path: {}. Correlation Id: {}", request.getRequestURI(), correlationId);
        }

        return true;
    }
}
