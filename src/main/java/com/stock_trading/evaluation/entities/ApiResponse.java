package com.stock_trading.evaluation.entities;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    String code;
    String message;
    Object data;
    int paging;
    String status;
}
