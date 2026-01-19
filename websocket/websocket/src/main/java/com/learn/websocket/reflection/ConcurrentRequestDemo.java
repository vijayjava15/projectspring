package com.learn.websocket.reflection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentRequestDemo {

    private static final int TOTAL_REQUESTS = 100;
    private static final String TARGET_URL = "http://localhost:8080/book"; // Use any test URL

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20); // 20 threads

        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            final int requestId = i + 1;
            executor.submit(() -> sendRequest(requestId));
        }

        executor.shutdown();
    }

    private static void sendRequest(int id) {
        try {
            URL url = new URL(TARGET_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            con.disconnect();

            System.out.println("Request #" + id + " - Status: " + status);
        } catch (Exception e) {
            System.err.println("Request #" + id + " failed: " + e.getMessage());
        }
    }
}
