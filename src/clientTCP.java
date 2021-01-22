import javax.swing.*;
import java.io.*;
import java.net.Socket;
public class clientTCP {
    public static void main(String argv[]) throws Exception {
        String sentence_from_server;

        //Tạo socket cho client kết nối đến server qua ID address và port number
        Socket clientSocket = new Socket("localhost", 8080);

        System.out.println("connected");

        // get the output stream from the socket.
        OutputStream outputStream = clientSocket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        int[] array = {1, 2, 3, 4, 5, 6};

        objectOutputStream.writeObject(array);

        //Đọc tin từ Server thông qua InputSteam đã nối với socket
        System.out.println("okie");

        // get the input stream from the connected socket
        InputStream inputStream = clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        sentence_from_server = (String) objectInputStream.readObject();

        //print kết qua ra màn hình
        System.out.println("FROM SERVER: " + sentence_from_server);

        //Đóng liên kết socket
        clientSocket.close();
    }
}