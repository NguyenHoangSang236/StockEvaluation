package com.stock_trading.evaluation.network;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class StockWebSocketClient extends WebSocketClient {
    public StockWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public StockWebSocketClient() throws URISyntaxException {
        super(new URI("wss://iboard-pushstream.ssi.com.vn/realtime"));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connected");
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("Disconnected");
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
