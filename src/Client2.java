import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client2 {
    public static void main(String argv[]) throws Exception {
        String sentence_to_server;
        String sentence_from_server;
        //Tạo socket cho client kết nối đến server qua ID address và port number
        Socket clientSocket = new Socket("1.54.204.35", 8080);
        System.out.println("connected");
        //Tạo OutputStream nối với Socket
        ObjectOutputStream outToServer =
                new ObjectOutputStream(clientSocket.getOutputStream());
//        int[] array = {1, 2, 3};
        int n;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nhập vào số phần tử của mảng: ");
            n = scanner.nextInt();
        } while (n < 0);
        // khởi tạo và cấp phát bộ nhớ cho mảng
        int[] array = new int[n];
        System.out.println("Nhập các phần tử cho mảng: ");
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + i + ": ");
            array[i] = scanner.nextInt();
        }
        outToServer.writeObject(array);
        InputStream inputStream = clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        sentence_from_server = (String) objectInputStream.readObject();

        System.out.println("FROM SERVER: " + sentence_from_server);
        TimeUnit.MINUTES.sleep(1);
        clientSocket.close();
    }
}
