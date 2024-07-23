package com.stock_trading.evaluation;

import com.stock_trading.evaluation.network.StockWebSocketClient;
import com.stock_trading.evaluation.services.DataCrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableFeignClients
public class EvaluationApplication {
	public static void main(String[] args) throws InterruptedException, URISyntaxException {
		SpringApplication.run(EvaluationApplication.class, args);

		DataCrawlingService crawler = new DataCrawlingService();
		crawler.connectToStockRealtimeByListV2("FPT", "PNJ");
	}

}
