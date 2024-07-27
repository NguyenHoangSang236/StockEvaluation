package com.stock_trading.evaluation.services.data;

import com.stock_trading.evaluation.entities.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {
    public static ResponseEntity<ApiResponse> map(ApiResponse response) {
        String code = response.getCode();

        return switch (code) {
            case "OK" -> ResponseEntity.ok(response);
//            case "INTERNAL_SERVER_ERROR" -> ResponseEntity.internalServerError().body(response);
//            case "BAD_REQUEST" -> ResponseEntity.badRequest().body(response);
//            case "UNAUTHORIZED", "NO_CONTENT" -> new ResponseEntity<>(response, status);
            default -> ResponseEntity.badRequest().body(response);
        };
    }
}
