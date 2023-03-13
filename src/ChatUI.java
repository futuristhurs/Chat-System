import javax.swing.*;
import java.awt.*;

public class ChatUI extends JFrame implements ChatListener{
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private  ChatClient chatClient;

    public ChatUI(){
        super("Chat System");

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        messageField = new JTextField(20);
        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String message = messageField.getText().trim();
            if(!message.isEmpty()){
                chatClient.sendMessage(message);
                messageField.setText("");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chatScrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(messageField,BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        panel.add(messagePanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    @Override
    public void messageReceived(String message){
        chatArea.append(message + "\n");
    }
}
