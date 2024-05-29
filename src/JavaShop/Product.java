package JavaShop;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Product extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Product() {
		setLayout(null);

		JLabel lbl_1 = new JLabel("");
		lbl_1.setBounds(111, 67, 150, 160);
		add(lbl_1);
		String img_1 = "D:\\java\\Project1\\JavaShopHoa\\img\\1.jpg";
		lbl_1.setIcon(new ImageIcon(new ImageIcon(img_1).getImage().getScaledInstance(lbl_1.getWidth(),
				lbl_1.getHeight(), java.awt.Image.SCALE_SMOOTH)));

		JLabel name_1 = new JLabel("Loading...");
		name_1.setForeground(Color.RED);
		name_1.setBackground(Color.WHITE);
		name_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		name_1.setHorizontalAlignment(SwingConstants.CENTER);
		name_1.setBounds(111, 237, 150, 22);
		add(name_1);

		JLabel lbl_2 = new JLabel("");
		lbl_2.setBounds(481, 68, 150, 160);
		add(lbl_2);
		String img_2 = "D:\\java\\Project1\\JavaShopHoa\\img\\2.jpg";
		lbl_2.setIcon(new ImageIcon(new ImageIcon(img_2).getImage().getScaledInstance(lbl_2.getWidth(),
				lbl_2.getHeight(), java.awt.Image.SCALE_SMOOTH)));

		JLabel name_2 = new JLabel("Loading...");
		name_2.setForeground(Color.BLUE);
		name_2.setHorizontalAlignment(SwingConstants.CENTER);
		name_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		name_2.setBounds(481, 237, 150, 22);
		add(name_2);

		JLabel lbl_3 = new JLabel("");
		lbl_3.setBounds(111, 344, 150, 160);
		add(lbl_3);
		String img_3 = "D:\\java\\Project1\\JavaShopHoa\\img\\3.jpg";
		lbl_3.setIcon(new ImageIcon(new ImageIcon(img_3).getImage().getScaledInstance(lbl_3.getWidth(),
				lbl_3.getHeight(), java.awt.Image.SCALE_SMOOTH)));

		JLabel name_3 = new JLabel("Loading...");
		name_3.setForeground(Color.YELLOW);
		name_3.setHorizontalAlignment(SwingConstants.CENTER);
		name_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		name_3.setBounds(476, 514, 150, 22);
		add(name_3);

		JLabel lbl_4 = new JLabel("");
		lbl_4.setBounds(481, 344, 150, 160);
		add(lbl_4);
		String img_4 = "D:\\java\\Project1\\JavaShopHoa\\img\\4.jpg";
		lbl_4.setIcon(new ImageIcon(new ImageIcon(img_4).getImage().getScaledInstance(lbl_4.getWidth(),
				lbl_4.getHeight(), java.awt.Image.SCALE_SMOOTH)));

		JLabel name_4 = new JLabel("Loading...");
		name_4.setForeground(Color.CYAN);
		name_4.setHorizontalAlignment(SwingConstants.CENTER);
		name_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		name_4.setBounds(114, 514, 150, 22);
		add(name_4);

		fetchAndSetName(name_1, 1);
		fetchAndSetName(name_2, 2);
		fetchAndSetName(name_3, 3);
		fetchAndSetName(name_4, 4);
		
		BackgroundPanel panel = new BackgroundPanel("D:\\java\\Project1\\JavaShopHoa\\img\\5.jpg");
		panel.setBounds(0, 0, 739, 571);
		add(panel);
	}

	private void fetchAndSetName(JLabel label, int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoa", "root", "Thuongle2");

			String sql = "SELECT hoa.name FROM hoa WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				label.setText(name);
			} else {
				label.setText("Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			label.setText("Error");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Class to create a background panel
	class BackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;

		public BackgroundPanel(String fileName) {
			backgroundImage = new ImageIcon(fileName).getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

