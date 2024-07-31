package com.stock_trading.evaluation.usecases.evaluation;

import com.stock_trading.evaluation.core.UseCase;
import com.stock_trading.evaluation.entities.api.ApiResponse;
import com.stock_trading.evaluation.entities.api.StockSymbolEvaluationRequest;
import org.springframework.stereotype.Component;

@Component
public class EvaluateStockSymbolUseCase extends UseCase<EvaluateStockSymbolUseCase.InputValue, ApiResponse> {


    @Override
    public ApiResponse execute(InputValue input) {
        return null;
    }

    public record InputValue(StockSymbolEvaluationRequest request) implements UseCase.InputValue {
    }
}
