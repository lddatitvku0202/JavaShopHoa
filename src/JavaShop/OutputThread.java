package JavaShop;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class OutputThread extends Thread {
    Socket socket;
    JTextPane txtMessages;
    String sender;
    String receiver;

    public OutputThread(Socket socket, JTextPane txtMessages, String sender, String receiver) {
        this.socket = socket;
        this.txtMessages = txtMessages;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = bf.readLine()) != null) {
                appendMessage(receiver, message, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendMessage(String sender, String message, boolean isSender) {
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