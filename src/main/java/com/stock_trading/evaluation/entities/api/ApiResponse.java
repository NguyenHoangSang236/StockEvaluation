package com.stock_trading.evaluation.entities.api;

import com.stock_trading.evaluation.core.UseCase;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements UseCase.OutputValue {
    String code;
    String message;
    Object data;
    int paging;
    String status;
    String error;
}
