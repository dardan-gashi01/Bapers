import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Login extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel PanelLogin;
    private static JLabel success;


    public Login() {
        final Login login = this;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = passwordField1.getText();
                if(username.equals("user") && password.equals("12345")){
                    JFrame frame = new JFrame("Menu");
                    frame.setContentPane(new Menu().PanelMain);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    dispose();
                }else{
                    //JOptionPane.showMessageDialog(frame, "invalid");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame loginframe = new JFrame("Bapers System");
        loginframe.setContentPane(new Login().PanelLogin);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.pack();
        loginframe.setVisible(true);
    }


}

