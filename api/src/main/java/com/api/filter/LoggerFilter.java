package com.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);


        //request 정보
        var headerNames = req.getHeaderNames();
        var reqHeaderValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            //headerKey : value , user-agent : ???
            reqHeaderValues.append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] , ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info("request>>>> uri : {} , method: {},  header : {} , body : {}", uri, method, reqHeaderValues, requestBody);

        //response 정보
        var resHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            resHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] , ");

        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("response>>>> uri : {} , method: {},  header : {} , body : {}", uri, method, resHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
