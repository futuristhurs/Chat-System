import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private ChatListener listener;
    private Scanner scanner;

    public void setListener(ChatListener listener){
    this.listener = listener;
    }
    @Override
    public void run(){
        try{
            socket = new Socket("localhost", 8000);
            System.out.println("Connected to chat server on port 8000");
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            scanner = new Scanner(System.in);
            while(true){
                String message = scanner.nextLine();
                writer.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to chat server: " + e.getMessage());
        }
    }
    public void sendMessage(String message) {
        writer.println(message);
    }
}