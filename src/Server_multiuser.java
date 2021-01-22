import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server_multiuser extends Thread{

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    public static void main(String argv[]) throws Exception
    {
        String sentence_to_client;
        ServerSocket welcomeSocket = new ServerSocket(8080);

        while(true) {
            //chờ yêu cầu từ client
            Socket connectionSocket = welcomeSocket.accept();
            ClientHandler clientThread = new ClientHandler(connectionSocket);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
