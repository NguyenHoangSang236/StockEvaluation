package com.stock_trading.evaluation.services.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock_trading.evaluation.utils.network.StockWebSocketClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class RealtimeStockDataService {
    @Autowired
    StockWebSocketClient client;

    public void connectToStockRealtimeByListV2(String... stockCode) throws URISyntaxException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> map = new HashMap<>();
            map.put("type", "sub");
            map.put("topic", "stockRealtimeByListV2");
            map.put("variables", Arrays.stream(stockCode).toList());
            map.put("component", "priceTableEquities");

            client = new StockWebSocketClient(new URI("wss://iboard-pushstream.ssi.com.vn/realtime"));
            client.connectBlocking();
            client.send(objectMapper.writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
