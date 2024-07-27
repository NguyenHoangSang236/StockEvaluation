package com.stock_trading.evaluation.usecases.stock_history;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.stock_trading.evaluation.core.UseCase;
import com.stock_trading.evaluation.entities.api.ApiResponse;
import com.stock_trading.evaluation.entities.api.StockHistoryRequest;
import com.stock_trading.evaluation.entities.dto.StockHistory;
import com.stock_trading.evaluation.infrastructure.feign.StockHistoryFeign;
import com.stock_trading.evaluation.utils.common.ValueParsingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ViewStockChartHistoryUseCase extends UseCase<ViewStockChartHistoryUseCase.InputValue, ApiResponse> {
    @Autowired
    StockHistoryFeign stockHistoryFeign;

    @Autowired
    ValueParsingUtil valueParsingUtil;


    @Override
    public ApiResponse execute(InputValue input) {
        try {
            StockHistoryRequest request = input.request();
            List<StockHistory> stockHistoryList = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();

            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("resolution", "1D");
            requestBodyMap.put("symbol", request.getSymbol().trim().toUpperCase());
            requestBodyMap.put("from", valueParsingUtil.convertFromDateToSeconds(request.getFromDate()));
            requestBodyMap.put("to", valueParsingUtil.convertFromDateToSeconds(request.getToDate()));

            ResponseEntity<ApiResponse> response = stockHistoryFeign.stockChartHistory(requestBodyMap);

            if (response.getStatusCode() == HttpStatusCode.valueOf(200)) {
                ApiResponse apiResponse = objectMapper.convertValue(response.getBody(), ApiResponse.class);
                Map map = objectMapper.convertValue(apiResponse.getData(), Map.class);

                List<Long> timeList = objectMapper.convertValue(map.get("t"), typeFactory.constructCollectionType(List.class, Long.class));
                List<Double> openList = objectMapper.convertValue(map.get("o"), typeFactory.constructCollectionType(List.class, Double.class));
                List<Double> closeList = objectMapper.convertValue(map.get("c"), typeFactory.constructCollectionType(List.class, Double.class));
                List<Double> highList = objectMapper.convertValue(map.get("h"), typeFactory.constructCollectionType(List.class, Double.class));
                List<Double> lowList = objectMapper.convertValue(map.get("l"), typeFactory.constructCollectionType(List.class, Double.class));
                List<Long> volumeList = objectMapper.convertValue(map.get("v"), typeFactory.constructCollectionType(List.class, Long.class));

                for (int i = 0; i < timeList.size(); i++) {
                    stockHistoryList.add(
                            new StockHistory(
                                    valueParsingUtil.convertFromSecondsToDate(timeList.get(i)),
                                    openList.get(i),
                                    closeList.get(i),
                                    lowList.get(i),
                                    highList.get(i),
                                    volumeList.get(i)
                            )
                    );
                }

                apiResponse.setData(stockHistoryList);

                return apiResponse;
            } else {
                return response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ApiResponse.builder()
                    .code("FAILED")
                    .error(e.getMessage())
                    .message("Server error")
                    .build();
        }
    }

    public record InputValue(StockHistoryRequest request) implements UseCase.InputValue {
    }
}
