import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class DataGramServer {


    public static void main(String[] args) {
        try (DatagramSocket datagramServer = new DatagramSocket(7777)) {
            byte[] bytes = new byte[512];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            datagramServer.receive(packet);
            System.out.println(Arrays.toString(bytes));
            System.out.println(new String(bytes));
            System.out.println(Arrays.toString(packet.getData()));
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
