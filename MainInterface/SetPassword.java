package MainInterface;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import Classes.Interface;
import Database.Database;

import java.awt.event.*;

public class SetPassword extends Interface implements ActionListener{

    JLabel TextPassword;
    JPasswordField NewPassword;
    JLabel Notification;
    JButton SetPassword;

    public void MainMenu() {

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("SetPassword - " + Name);
        frame.setSize(500,270);

        // Add Notification
        Notification = new JLabel();
        Notification.setBounds(185, 180, 150, 30);
        frame.add(Notification);

        // Add Text
        TextPassword = new JLabel("New Password: ");
        TextPassword.setBounds(100, 50, (int)TextPassword.getPreferredSize().getWidth()+1, 30);
        frame.add(TextPassword);

        // Add text field to get new password
        NewPassword = new JPasswordField();
        NewPassword.setBounds(200, 50, 150, 30);
        frame.add(NewPassword);

        // Add SetPassword button
        SetPassword = new JButton("SetPassword");
        SetPassword.setBounds(200, 150, (int)SetPassword.getPreferredSize().getWidth()+1, 30);
        SetPassword.addActionListener(this);
        frame.add(SetPassword);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent evt){

        // Error checking password
        if(String.valueOf(NewPassword.getPassword()) == null || String.valueOf(NewPassword.getPassword()).isBlank()){
            Notification.setText("Password can't be empty");
            NewPassword.setText("");
        }

        // If valid password
        else{new Database().ResetPassword(Role,Name,String.valueOf(NewPassword.getPassword()));}

    }



}
