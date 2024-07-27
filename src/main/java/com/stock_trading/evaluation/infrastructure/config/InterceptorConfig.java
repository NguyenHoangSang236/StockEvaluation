package com.stock_trading.evaluation.infrastructure.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("origin", "https://iboard.ssi.com.vn");
        template.header("Host", " iboard-api.ssi.com.vn");
        template.header("Sec-Fetch-Dest", "document");
        template.header("Sec-Fetch-Mode", "navigate");
        template.header("Sec-Fetch-Site", "none");
        template.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Safari/605.1.15");
        template.header("Accept", "application/json;charset=utf-8");
    }
}
