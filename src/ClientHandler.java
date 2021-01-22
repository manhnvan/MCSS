import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream clientInputStream;
    private ObjectOutputStream clientOutputStream;

    public ClientHandler(Socket client) throws IOException{
        this.client = client;
        this.clientInputStream = new ObjectInputStream(client.getInputStream());
        this.clientOutputStream = new ObjectOutputStream(client.getOutputStream());
    }
    @Override
    public void run(){
        try{
            int[] requestData = (int[]) this.clientInputStream.readObject();

            int sum = 0;
            int multiply = 1;

            for (int i : requestData) {
                System.out.println(i);
                sum += i;
                multiply *= i;
            }
            String response = "sum: " + sum + " multiply: " + multiply + " !!!";
            System.out.println(response);
    //            //ghi dữ liệu ra socket
            clientOutputStream.writeObject(response);
            return;
        }
        catch (Exception e) {
            System.out.println("Exception occurred");
        }
        finally {
            try{
                clientOutputStream.close();
                clientInputStream.close();
            }
            catch (IOException e){
                System.out.println("Exception occurred");
            }
        }
    }
}
