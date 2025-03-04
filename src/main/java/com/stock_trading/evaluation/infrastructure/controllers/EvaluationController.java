package com.stock_trading.evaluation.infrastructure.controllers;

import com.stock_trading.evaluation.core.UseCaseExecutor;
import com.stock_trading.evaluation.entities.api.ApiResponse;
import com.stock_trading.evaluation.entities.api.StockHistoryRequest;
import com.stock_trading.evaluation.entities.api.StockSymbolEvaluationRequest;
import com.stock_trading.evaluation.services.data.ResponseMapper;
import com.stock_trading.evaluation.usecases.evaluation.EvaluateStockSymbolUseCase;
import com.stock_trading.evaluation.usecases.stock_history.ViewStockChartHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/evaluation", consumes = {"*/*"}, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class EvaluationController {
    final UseCaseExecutor executor;
    final EvaluateStockSymbolUseCase evaluateStockSymbolUseCase;

    @GetMapping("/chartData")
    public CompletableFuture<ResponseEntity<ApiResponse>> getChartData(
            @RequestParam(value = "from_date") String fromDate,
            @RequestParam(value = "to_date") String toDate,
            @RequestParam(value = "symbol") String symbol
    ) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        StockSymbolEvaluationRequest request = StockSymbolEvaluationRequest.builder()
                .fromDate(dateFormat.parse(fromDate))
                .toDate(dateFormat.parse(toDate))
                .symbol(symbol)
                .build();

        return executor.execute(
                evaluateStockSymbolUseCase,
                new EvaluateStockSymbolUseCase.InputValue(request),
                ResponseMapper::map
        );
    }
}
