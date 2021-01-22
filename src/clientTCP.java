import javax.swing.*;
import java.io.*;
import java.net.Socket;
public class clientTCP {
    public static void main(String argv[]) throws Exception {
        while (true) {
            String sentence_from_server;
            Socket clientSocket = new Socket("localhost", 8081);
            System.out.println("connected");

            // get the output stream from the socket.
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            int[] array = {1, 2, 3, 4, 5, 6};

            objectOutputStream.writeObject(array);

            // get the input stream from the connected socket
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            sentence_from_server = (String) objectInputStream.readObject();
            System.out.println("FROM SERVER: " + sentence_from_server);
            clientSocket.close();
        }
    }
}