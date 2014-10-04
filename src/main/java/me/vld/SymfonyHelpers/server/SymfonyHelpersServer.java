package me.vld.SymfonyHelpers.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import me.vld.SymfonyHelpers.SymfonyHelpersOpen;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AveVlad
 */
public class SymfonyHelpersServer {
    private int port;

    public SymfonyHelpersServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        System.out.println("server run 123");
        System.out.println("");
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
            String route = params.get("route");
            String type = params.get("type");
            SymfonyHelpersOpen.findFile(route, type);
            String response = "true";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        public Map<String, String> queryToMap(String query) {
            Map<String, String> result = new HashMap<String, String>();
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
            return result;
        }

    }
}