package com.stock_trading.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.URISyntaxException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableFeignClients
public class EvaluationApplication {
    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        SpringApplication.run(EvaluationApplication.class, args);

//		RealtimeStockDataService crawler = new RealtimeStockDataService();
//		crawler.connectToStockRealtimeByListV2("FPT", "PNJ");
    }

}
