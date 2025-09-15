import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    boolean clientConnected = false;

    public server(int port){
        createServer(port);
    }
    public void createServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started on port: " + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Waiting for connection...");
        while (!clientConnected){
            try {
                socket = serverSocket.accept();
                System.out.println("Accepted connection from: " + socket.getInetAddress().getHostName());
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                output.println("Welcome to the server!");
                clientConnected = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        closeServer();
    }

    public void closeServer(){
        try {
            socket.close();
            serverSocket.close();
            System.out.println("Server Closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        server server = new server(5000);
    }
}
