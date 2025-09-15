import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {
    private Socket clientSocket = null;

    public client(String ipAddr, int port){
        createClient(ipAddr, port);
    }
    public void createClient(String ipAddr, int port){
        try {
            clientSocket = new Socket(ipAddr, port);
            System.out.println("Connected to: " + clientSocket.getInetAddress().getHostName());

            BufferedReader input;
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (input.readLine() != null){
                String serverResponse = input.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            System.err.println("Could not connect to the server.");
        }
    }
    public void closeConnection(){
        try {
            clientSocket.close();
            System.out.println("Closed connection: " + clientSocket.getInetAddress().getHostName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        client client = new client("127.0.0.1", 5000);
    }
}
