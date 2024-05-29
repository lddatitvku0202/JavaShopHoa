package JavaShop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class ClientChaterJpanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Socket mngSocket = null;
	String mngIP = "";
	int mngPort = 0;
	String staffName = "";
	BufferedReader bf = null;
	DataOutputStream os = null;
	OutputThread t = null;
	private JLabel lblNewLabel;

	public ClientChaterJpanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnConnect = new JButton("Chat Support");
		btnConnect.setFont(new Font("Stencil", Font.PLAIN, 9));
		btnConnect.setForeground(new Color(204, 0, 0));
		btnConnect.setBackground(new Color(255, 255, 204));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mngPort = 12360;
				staffName = "KH";						
				try {
					mngSocket = new Socket("127.0.0.1", mngPort);
					if (mngSocket != null) {
						ChatPanel p = new ChatPanel(mngSocket, staffName, "Shop");
						add(p, BorderLayout.CENTER);
						p.appendMessage("System", "Đã kết nối tới nhân viên hỗ trợ...Hãy nhắn với chúng tôi!", false);
						remove(lblNewLabel); // Remove the initial label
						revalidate(); // Revalidate the layout
						repaint(); // Repaint the panel
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel.add(btnConnect);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		lblNewLabel = new JLabel("Chat với đội ngũ hỗ trợ của chúng tôi để mua hoa sớm nhất...Thank!!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.CENTER);
	}
}

