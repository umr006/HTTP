import java.io.DataInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress address = Inet4Address.getByName("localhost");
        try (Socket socket = new Socket(address, 7767);
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var scanner = new Scanner(System.in)) {
            System.out.println("Client UP");
            while (scanner.hasNextLine()) {
                System.out.println("Введите запрос на сервер:");
                var request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Server responce: " + inputStream.readUTF());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
