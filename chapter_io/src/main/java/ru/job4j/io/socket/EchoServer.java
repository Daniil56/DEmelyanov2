package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        boolean available = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (available) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                    String message;
                    while (!(message =  in.readLine()).isEmpty()) {
                        System.out.println(message);
                        String[] split = message.split("=");
                        if (split.length > 1) {
                            available = !split[1].split(" ")[0].equals("Bye");
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
