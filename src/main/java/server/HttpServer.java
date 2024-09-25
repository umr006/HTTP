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
    private final ExecutorService pool;
    private boolean stopped;
    private int cnt_request = 0;
    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (!stopped) {
                Socket socket = serverSocket.accept();
                System.out.println("Получено запросов: " + cnt_request++);
                pool.submit(() -> processSocket(socket));
            }

        } catch (IOException e) {
            //TODO добавить логгирование
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (   socket;
                pool;
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
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
        HttpServer httpServer = new HttpServer(9000, 100);
        httpServer.run();
    }

}
