package com.stock_trading.evaluation.entities.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockSymbolEvaluationRequest {
    @JsonProperty(value = "from_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date fromDate;

    @JsonProperty(value = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date toDate;

    @JsonProperty(value = "stock_symbol")
    String symbol;
}
