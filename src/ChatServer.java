import java.io.*;
import java.net.*;
import java.sql.SQLOutput;

public class ChatServer extends Thread {
    private ServerSocket serverSocket;
    private ChatListener listener;
    public void setListener(ChatListener listener){
    this.listener = listener;
    }
    @Override
    public void run(){
        try{
            serverSocket = new ServerSocket(8000);
            System.out.println("Chat server started on port 8000");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress().getHostName());
                ChatHandler handler = new ChatHandler(socket);
                handler.start();
            }
        }catch (IOException e) {
            System.err.println("Error starting chat server: " + e.getMessage());
        }
    }

    private class ChatHandler extends  Thread{
        private Socket socket;
        private BufferedReader reader;

        public ChatHandler(Socket socket){
            this.socket = socket;
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }catch (IOException e){
                System.err.println("Error creating");
            }
        }
        @Override
        public void run(){
            try{
                String message;
                while ((message = reader.readLine()) != null){
                    if (listener != null) {
                        listener.messageReceived(message);
                    }
                }
            }catch (IOException e) {
                System.err.println("Error handling chat message: " + e.getMessage());
            } finally {
                try {
                    reader.close();
                    socket.close();
                } catch (IOException e){
                    System.err.println("Error closing chat socket: " + e.getMessage());
                }
            }
        }
    }
}