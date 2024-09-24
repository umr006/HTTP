import java.io.IOException;
import java.net.*;
import java.net.spi.InetAddressResolver;

public class DataGramRunner {

    
    
    
    
    
    public static void main(String[] args) throws SocketException {
        dataSoc();

    }

    private static void dataSoc() {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            InetAddress localhost = Inet4Address.getByName("localhost");
            var bytes = "Hello UPD!".getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, localhost, 7777);
            datagramSocket.send(packet);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
