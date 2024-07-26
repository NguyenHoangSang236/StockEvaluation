package com.stock_trading.evaluation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockHistory {
    @JsonProperty(value = "t")
    long time;

    @JsonProperty(value = "c")
    long close;

    @JsonProperty(value = "o")
    long open;

    @JsonProperty(value = "h")
    long high;

    @JsonProperty(value = "l")
    long low;

    @JsonProperty(value = "v")
    long volume;
}
