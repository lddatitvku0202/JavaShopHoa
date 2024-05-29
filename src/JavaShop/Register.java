package JavaShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton registerButton;
    private Connection connection;
    private JLabel lblNewLabel;

    public Register() {
        setTitle("Register");
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

        lblNewLabel = new JLabel("REGISTRATION");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("UTM Cooper Black", Font.BOLD, 24));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        emailLabel.setForeground(Color.RED);

        emailField = new JTextField(10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.RED);

        usernameField = new JTextField(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.RED);
        passwordLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        passwordLabel.setFont(new Font("Stencil", Font.PLAIN, 18));

        passwordField = new JPasswordField(10);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.RED);
        genderLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        genderLabel.setFont(new Font("Stencil", Font.PLAIN, 18));

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        maleRadioButton.setForeground(Color.RED);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setForeground(Color.RED);
        femaleRadioButton.setFont(new Font("Stencil", Font.PLAIN, 18));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Stencil", Font.PLAIN, 18));
        registerButton.setBackground(UIManager.getColor("Button.background"));
        registerButton.setForeground(Color.RED);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblNewLabel)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(emailLabel)
                        .addComponent(usernameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(genderLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(emailField)
                        .addComponent(usernameField)
                        .addComponent(passwordField)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(maleRadioButton)
                            .addComponent(femaleRadioButton))))
                .addComponent(registerButton)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblNewLabel)
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField))
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLabel)
                    .addComponent(maleRadioButton)
                    .addComponent(femaleRadioButton))
                .addGap(30)
                .addComponent(registerButton)
        );

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Thuongle2");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối với cơ sở dữ liệu!");
        }

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                registerUser(username, password);
            }
        });
    }

    private void registerUser(String username, String password) {
        try {
            String query = "INSERT INTO user.login (`username`, `password`) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, encryptPassword(password));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(Register.this, "Người dùng đã đăng kí thành công!");
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
                if (hex.length() == 1)
                    hexString.append('0');
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
            Register app = new Register();
            app.setVisible(true);
        });
    }
}

