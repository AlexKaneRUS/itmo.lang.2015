package ru.ifmo.lang.Sadovnikov.t07;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by alexkane on 4/20/15.
 */
public class WhiteboardServer {
    private static String message = "";

    public static void main(String[] args) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/get", new WhiteboardHandler());
        server.createContext("/post", new WhiteboardHandler());
        server.start();
    }

    private static class WhiteboardHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            if (httpExchange.getHttpContext().getPath().endsWith("/get")) {
                httpExchange.sendResponseHeaders(200, URLEncoder.encode(message, "Unicode").length());
                final OutputStream responseBody = httpExchange.getResponseBody();
                responseBody.write(URLEncoder.encode(message, "Unicode").getBytes());
                responseBody.close();
            } else {
                final String query = httpExchange.getRequestURI().getQuery();
                message = URLDecoder.decode(query.substring(query.indexOf("=") + 1, query.length()), "UTF-8");
                final String thisMessage = "Your request has been received";
                httpExchange.sendResponseHeaders(200, thisMessage.length());
                final OutputStream responseBody = httpExchange.getResponseBody();
                responseBody.write(thisMessage.getBytes());
                responseBody.close();
            }
        }
    }
}
