package com.slmanju.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedEchoServer {

    private static final int PORT = 8080;
    private static final int BUFFER_LENGTH = 1024;

    public static void main(String[] args) throws IOException {
        new MultithreadedEchoServer().serve();
    }
    
    public void serve() throws IOException {
        System.out.println("server started");
        ServerSocket serverSocket = new ServerSocket(PORT);
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InetSocketAddress clientSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();

            System.out.println("Connection from port=" + clientSocketAddress.getPort() + ", host=" + clientSocketAddress.getHostName());

            new Thread(() -> {
                try (InputStream inputStream = clientSocket.getInputStream();
                     OutputStream outputStream = clientSocket.getOutputStream();) {
                    byte[] buffer = new byte[BUFFER_LENGTH];
                    while (clientSocket.isConnected()) {
                        int length = inputStream.read(buffer);
                        if (length > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
