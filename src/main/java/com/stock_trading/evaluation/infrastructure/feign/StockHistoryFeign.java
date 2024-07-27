package com.stock_trading.evaluation.infrastructure.feign;

import com.stock_trading.evaluation.entities.api.ApiResponse;
import com.stock_trading.evaluation.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "stockHistory", url = "https://iboard-api.ssi.com.vn", configuration = FeignConfig.class)
public interface StockHistoryFeign {
    @GetMapping(value = "/statistics/charts/history", consumes = {"*/*"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ApiResponse> stockChartHistory(@RequestParam Map<String, Object> requestBody);
}
