package JavaShop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Product productPanel;
    private Information informationPanel;
    private ClientChaterJpanel clientChaterJpanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 996, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_content = new JPanel();
        panel_content.setBounds(229, 99, 740, 620);
        contentPane.add(panel_content);
        panel_content.setLayout(null);

        JPanel panel_header = new JPanel();
        panel_header.setBackground(Color.GRAY);
        panel_header.setBounds(10, 10, 959, 85);
        contentPane.add(panel_header);
        panel_header.setLayout(null);

        JLabel lblNewLabel = new JLabel("FURAMA FLOWER SHOP");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(416, 19, 330, 55);
        panel_header.add(lblNewLabel);
        
        JPanel panel_logo = new JPanel();
        panel_logo.setBounds(0, 0, 128, 85);
        panel_header.add(panel_logo);

        // Add logo to panel_logo
        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(0, 0, 128, 85);
        ImageIcon logoIcon = new ImageIcon("D:\\java\\Project1\\JavaShopHoa\\img\\6.jpg");
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH)));
        panel_logo.setLayout(null);
        panel_logo.add(logoLabel);

        JPanel panel_menu = new JPanel();
        panel_menu.setBackground(Color.GRAY);
        panel_menu.setBounds(10, 99, 214, 619);
        contentPane.add(panel_menu);
        panel_menu.setLayout(null);

        JLabel customer = new JLabel("CUSTOMER SUPPORT\r\n\r\n");
        customer.setForeground(Color.RED);
        customer.setFont(new Font("Stencil", Font.PLAIN, 20));
        customer.setHorizontalAlignment(SwingConstants.LEFT);
        customer.setBounds(10, 120, 204, 42);
        panel_menu.add(customer);
        customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_content.removeAll();
                panel_content.add(clientChaterJpanel);
                panel_content.revalidate();
                panel_content.repaint();
            }
        });

        JLabel trip = new JLabel("PRODUCT");
        trip.setForeground(Color.RED);
        trip.setFont(new Font("Stencil", Font.PLAIN, 20));
        trip.setHorizontalAlignment(SwingConstants.LEFT);
        trip.setBounds(10, 21, 185, 42);
        panel_menu.add(trip);
        trip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_content.removeAll();
                panel_content.add(productPanel);
                panel_content.revalidate();
                panel_content.repaint();
            }
        });

        JLabel join = new JLabel("INFORMATION");
        join.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_content.removeAll();
                panel_content.add(informationPanel);
                panel_content.revalidate();
                panel_content.repaint();
            }
        });
        join.setForeground(Color.RED);
        join.setFont(new Font("Stencil", Font.PLAIN, 20));
        join.setHorizontalAlignment(SwingConstants.LEFT);
        join.setBounds(10, 73, 145, 37);
        panel_menu.add(join);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(Color.RED);
        separator_1.setBounds(10, 154, 185, 19);
        panel_menu.add(separator_1);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setBackground(Color.RED);
        separator_1_1.setBounds(0, 536, 214, 19);
        panel_menu.add(separator_1_1);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBackground(Color.RED);
        separator_1_2.setBounds(10, 105, 185, 19);
        panel_menu.add(separator_1_2);

        JSeparator separator_1_3 = new JSeparator();
        separator_1_3.setBackground(Color.RED);
        separator_1_3.setBounds(10, 56, 185, 19);
        panel_menu.add(separator_1_3);

        productPanel = new Product();
        productPanel.setBounds(0, 0, panel_content.getWidth(), panel_content.getHeight());

        informationPanel = new Information();
        informationPanel.setBounds(0, 0, panel_content.getWidth(), panel_content.getHeight());
        
        clientChaterJpanel = new ClientChaterJpanel();
        clientChaterJpanel.setBounds(0, 0, panel_content.getWidth(), panel_content.getHeight());

        panel_content.add(productPanel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.setBounds(10, 90, 959, 625);
        contentPane.add(panel);
    }
}

