import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
public class serverTCP {
    public static void main(String argv[]) throws Exception
    {
        String sentence_to_client;
        ServerSocket welcomeSocket = new ServerSocket(8080);

        while(true) {
            //chờ yêu cầu từ client
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
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            sentence_to_client = "sum: " + sum + " multiply: " + multiply + " !!!";
            System.out.println(sentence_to_client);
            //ghi dữ liệu ra socket
            objectOutputStream.writeObject(sentence_to_client);
            return;
        }
    }
}

