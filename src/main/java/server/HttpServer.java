package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final int port;
    private final int poolSize;
    private boolean stopped;
    private final ExecutorService executorService;
    private int cntRequest = 0;

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.poolSize = poolSize;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!stopped) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            //TODO добавить логгирование
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (   socket;
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("CntRequest: " + cntRequest++);
            //sSystem.out.println("Request: " + new String(inputStream.readAllBytes()));
            byte[] body = Files.readAllBytes(Path.of("src/main/resources/pro100.html"));
            byte[] headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-lenght: %s
                    """.formatted(body.length).getBytes();
            dataOutputStream.write(headers);
            dataOutputStream.write(System.lineSeparator().getBytes());
            dataOutputStream.write(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9000, 10);
        httpServer.run();
    }

}
