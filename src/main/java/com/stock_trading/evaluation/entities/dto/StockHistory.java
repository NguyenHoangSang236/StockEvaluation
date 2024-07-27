package com.stock_trading.evaluation.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StockHistory {
    Date time;
    double close;
    double open;
    double high;
    double low;
    long volume;
}
