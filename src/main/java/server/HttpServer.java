package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            processSocket(socket);
        } catch (IOException e) {
            //TODO добавить логгирование
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (   socket;
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("lsdmglsdmglmsg");
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
            System.out.println("kjsnfdgjnsfgjnfsg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9000);
        httpServer.run();
    }

}
