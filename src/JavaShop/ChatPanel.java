package JavaShop;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ChatPanel extends JPanel {
    Socket socket = null;
    BufferedReader bf = null;
    DataOutputStream os = null;
    OutputThread t = null;
    String sender;
    String receiver;
    JTextPane txtMessages;

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public ChatPanel(Socket s, String sender, String receiver) {
        setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 153, 255));
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);

        JTextPane txtMessage = new JTextPane();
        scrollPane.setViewportView(txtMessage);

        JButton btnSend = new JButton("Send");
        btnSend.setFont(new Font("Stencil", Font.PLAIN, 14));
        btnSend.setForeground(new Color(204, 0, 0));
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtMessage.getText().trim().length() == 0) return;
                try {
                    os.writeBytes(txtMessage.getText());
                    os.write(13);
                    os.write(10);
                    os.flush();
                    appendMessage(sender, txtMessage.getText(), true);
                    txtMessage.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(btnSend);

        JScrollPane scrollPane_1 = new JScrollPane();
        add(scrollPane_1, BorderLayout.CENTER);

        txtMessages = new JTextPane();
        txtMessages.setEditable(false);
        scrollPane_1.setViewportView(txtMessages);

        socket = s;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new DataOutputStream(socket.getOutputStream());
            t = new OutputThread(s, txtMessages, receiver, receiver);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JTextPane getTxtMessages() {
        return this.txtMessages;
    }

    public void appendMessage(String sender, String message, boolean isSender) {
        StyledDocument doc = txtMessages.getStyledDocument();
        SimpleAttributeSet left = new SimpleAttributeSet();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(left, Color.BLUE);
        StyleConstants.setForeground(right, Color.RED);
        
        SimpleAttributeSet attributeSet = isSender ? right : left;

        try {
            doc.insertString(doc.getLength(), sender + ": " + message + "\n", attributeSet);
            doc.setParagraphAttributes(doc.getLength(), 1, attributeSet, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}



