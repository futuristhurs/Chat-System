public class Main {
    public static void main(String[] args) {
        ChatUI chatUI = new ChatUI();
        ChatClient chatClient = new ChatClient();
        ChatServer chatServer = new ChatServer();

        chatClient.setListener(chatUI);
        chatServer.setListener(chatUI);

        chatServer.start();
        chatClient.start();
    }

}