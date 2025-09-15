import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUI extends JFrame implements ActionListener {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatUI() {
        setTitle("💬 Chat App");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 245));
        chatArea.setForeground(new Color(50, 50, 50));
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        // Input field and send button
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputField.addActionListener(e -> sendButton.doClick());

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        sendButton.addActionListener(this);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sendButton) {
            String message = inputField.getText();
            chatArea.append("You: " + message + "\n");
            inputField.setText("");
        }
    }

    public static void main(String[] args){
        // Swing is not thread safe so we call this method
        SwingUtilities.invokeLater(ChatUI::new);
    }
}
