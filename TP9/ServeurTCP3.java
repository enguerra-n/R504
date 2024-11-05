import java.io.*;
import java.net.*;

public class ServeurTCP3 {
    public static void main(String[] args) {
        try {
            ServerSocket socketserver = new ServerSocket(2016);
            System.out.println("serveur en attente");

            // Boucle infinie pour que le serveur reste actif
            while (true) {
                Socket socket = socketserver.accept();
                System.out.println("Connexion d'un client");

                // Lire le message du client
                DataInputStream dIn = new DataInputStream(socket.getInputStream());
                String msg = dIn.readUTF();
                System.out.println("Message reçu: " + msg);

                // Inverser la chaîne
                String rev = new StringBuilder(msg).reverse().toString();

                // Envoyer la chaîne inversée au client
                DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                dOut.writeUTF(rev);

                socket.close();
            }

            // socketserver.close(); // Pas besoin de fermer le socketserver dans la boucle
        } catch (Exception ex) {
            System.out.println("Erreur!");
        }
    }
}
