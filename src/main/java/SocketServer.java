import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Scanner;

public class SocketServer {



    public static void main(String[] args) {


        try (var serverSocket = new ServerSocket(7767);
             var socket = serverSocket.accept();
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream());
                var scanner = new Scanner(System.in)) {
            System.out.println("Server UP");
            var request = inputStream.readUTF();

            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                var responce = scanner.nextLine();
                outputStream.writeUTF(responce);
                request = inputStream.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
