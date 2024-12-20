import java.io.*;
import java.net.*;

public class ClientTCP2 {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Veuillez fournir un message en argument.");
                return;
            }
            Socket socket = new Socket("localhost", 2016);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF(args[0]);
            socket.close();
        } catch (Exception ex) {
            System.out.println("Erreur!");
        }
    }
}
