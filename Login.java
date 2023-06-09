import java.awt.event.*;
import javax.swing.*;

public class Login implements ActionListener{
    
    JFrame frame;
    JTextField Name;
    JLabel TextName;
    JPasswordField Password;
    JLabel TextPassword;
    JButton Login;

    Login(){

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("MiniGrocery");
        frame.setSize(500,250);

        // Add Text
        TextName = new JLabel("Staff ID: ");
        TextName.setBounds(100, 50, 50, 30);
        frame.add(TextName);

        // Add text field to get name and pw
        Name = new JTextField();
        Name.setBounds(200, 50, 150, 30);
        frame.add(Name);

        // // Add Text
        TextPassword = new JLabel("Password: ");
        TextPassword.setBounds(100, 100, 100, 30);
        frame.add(TextPassword);

        // Add text field to get name and pw
        Password = new JPasswordField();
        Password.setBounds(200, 100, 150, 30);
        frame.add(Password);


        // Add Login button
        Login = new JButton("Login");
        Login.setBounds(200, 150, 100, 30);
        Login.addActionListener(this);
        frame.add(Login);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        frame.dispose();
        new CustomerInterface();
    }

}
