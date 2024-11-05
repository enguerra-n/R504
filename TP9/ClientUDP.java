import java.io.*;
import java.net.*;

public class ClientUDP {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();

            System.out.println("adresse=" + addr.getHostName());

            String message = "Hello World";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, addr, 1234);
            DatagramSocket sock = new DatagramSocket();
            sock.send(packet);

            // Réception de la réponse du serveur
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            sock.receive(responsePacket);


            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Réponse du serveur : " + response);

            sock.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
