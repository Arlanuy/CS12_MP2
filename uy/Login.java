package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Login extends JFrame {
    private JPanel         backgroundpanel;
    private JPanel         panel;
    private JPanel         panel2;
    private JTextField     usernameBox;
    private JPasswordField passwordBox;
    private JButton        button;
    private int            height = 319;
    private int            width  = 452;
    private ImageIcon      ii     = new ImageIcon("0-5");
    private JLabel         image;

    public Login() {
        setLayout(null);
        setTitle("Login");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        buildPanel();

        add(backgroundpanel);
        setVisible(true);
    }

    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          new Login();;
        }
      });

    }

    private void buildPanel() {
        usernameBox = new JTextField(20);
        passwordBox = new JPasswordField(20);
        button = new JButton("Login");
        image = new JLabel(ii);

        backgroundpanel = new JPanel();
        panel = new JPanel();
        panel2 = new JPanel();

        backgroundpanel.add(panel);
        backgroundpanel.add(panel2);
        backgroundpanel.add(image);

        panel.setBackground(Color.red);
        panel.setBounds(0, 0, 10, 10);
        panel.setOpaque(false);

        panel2.setBackground(Color.blue);
        panel2.setBounds(0, 0, 10, 10);
        panel2.setOpaque(false);

        panel.add(passwordBox);
        panel2.add(button);

        backgroundpanel.setOpaque(false);
        backgroundpanel.isOptimizedDrawingEnabled();
        backgroundpanel.setBounds(0, 0, width, height);
    }
}
