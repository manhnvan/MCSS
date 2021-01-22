import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
public class serverTCP {
    public static void main(String argv[]) throws Exception {
        String sentence_to_client;
        ServerSocket welcomeSocket = new ServerSocket(8081);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();

            InputStream inputStream = connectionSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            int[] requestData = (int[]) objectInputStream.readObject();

            System.out.println(requestData);

            int sum = 0;
            int multiply = 1;

            for (int i : requestData) {
                sum += i;
                multiply *= i;
            }

            // get the output stream from the socket.
            OutputStream outputStream = connectionSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            sentence_to_client = "sum: " + sum + " multiply: " + multiply + " !!!";
            System.out.println(sentence_to_client);
            //ghi dữ liệu ra socket
            objectOutputStream.writeObject(sentence_to_client);
            connectionSocket.close();
        }
    }
}

