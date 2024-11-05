import java.io.*;
import java.net.*;

public class ServeurUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1234);

            while (true) {
                System.out.println("- Waiting for data");
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);
                String str = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message reçu : " + str);

                // Renvoie le même message au client
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                byte[] responseData = str.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(responsePacket); // Envoie la réponse

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
