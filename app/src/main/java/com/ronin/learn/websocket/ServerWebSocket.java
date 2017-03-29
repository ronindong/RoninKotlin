package com.ronin.learn.websocket;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.ByteString;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ServerWebSocket {
    private MockWebServer mockWebServer = new MockWebServer();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void start() {
        mockWebServer.enqueue(new MockResponse().withWebSocketUpgrade(new WebSocketListener() {

            WebSocket webSocket = null;

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                this.webSocket = webSocket;
                System.out.println("server Open");
                System.out.println("server request headers:" + response.request().headers());
                System.out.println("server response headers:" + response.headers());
                System.out.println("server response :" + response);

            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                System.out.println("server OnMessage");



            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
            }
        }));

        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
