package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        boolean available = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (available) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                    String message;
                    StringBuilder response = new StringBuilder();

                    while ((message = in.readLine()) != null && !message.isEmpty()) {
                        String[] split = message.split("=");
                        if (split.length == 2) {
                            message = split[1].split(" ")[0];
                            if ("Exit".equals(message)) {
                                available = false;
                                response.append("Goodbye my dear friend").append("\r\n\r\n");
                            } else {
                                response.append("Hello dear ").append(message).append("\r\n\r\n");
                            }
                            break;
                        }
                    }
                    out.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
                    out.flush();
                    out.write(response.toString().getBytes(StandardCharsets.UTF_8));
                    out.flush();
                }
            }
        }
    }
}
