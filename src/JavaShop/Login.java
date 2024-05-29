package JavaShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Connection connection;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;

    public Login() {
        setTitle("Login or Register");
        setSize(500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("UTM Cooper Black", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.RED);

        usernameField = new JTextField(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.RED);

        passwordField = new JPasswordField(10);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        loginButton.setBackground(UIManager.getColor("Button.background"));
        loginButton.setForeground(Color.RED);

        lblNewLabel_1 = new JLabel("Bạn chưa có tài khoản? Đăng ký");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

        lblNewLabel_2 = new JLabel("Tại đây");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_2.setForeground(Color.RED);
        lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Register register = new Register();
                register.setVisible(true);
            }
        });

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblNewLabel)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(usernameLabel)
                        .addComponent(passwordLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameField)
                        .addComponent(passwordField)))
                .addComponent(loginButton)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblNewLabel_1)
                    .addComponent(lblNewLabel_2))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblNewLabel)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField))
                .addGap(30)
                .addComponent(loginButton)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewLabel_1)
                    .addComponent(lblNewLabel_2))
        );

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "Thuongle2");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối với cơ sở dữ liệu!");
        }

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                authenticateUser(username, password);
            }
        });
    }

    private void authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM user.login WHERE `username`=? AND `password`=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, encryptPassword(password));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Home home = new Home();
                home.setVisible(true);
                this.dispose();
                System.out.println("HELLO SERVER");
            } else {
                JOptionPane.showMessageDialog(Login.this, "Sai username hoặc password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void registerUser(String username, String password) {
        try {
            String query = "INSERT INTO user.login (`username`, `password`) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, encryptPassword(password));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(Login.this, "Người dùng đã đăng kí thành công!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return password;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login app = new Login();
            app.setVisible(true);
        });
    }
}


