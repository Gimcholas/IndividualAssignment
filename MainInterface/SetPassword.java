package MainInterface;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Interface;

import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SetPassword extends Interface implements ActionListener{

    JLabel TextPassword;
    JTextField NewPassword;
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
        NewPassword = new JTextField();
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
        if(NewPassword.getText() == null || NewPassword.getText().isBlank()){
            Notification.setText("Password can't be empty");
            NewPassword.setText("");
        }

        // If valid password
        else{ResetPassword();}

    }

    //https://stackoverflow.com/a/37624091/15149509
    private void ResetPassword(){

        try{
            Path FILE_PATH = Paths.get("C:\\Users\\End User\\Documents\\MMU Stuff\\java\\IndividualAssignment\\Database\\Accounts.md");

            List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));

            String oldEntry = "|" + Role + "|" + Name + "| |";
            String newEntry = "|" + Role + "|" + Name + "|" + NewPassword.getText() + "|";

            System.out.println(newEntry);

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldEntry)) {
                    fileContent.set(i, newEntry);
                    break;
                }
            }

            Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
            new Login().MainMenu();
            frame.dispose();

        }
        
        catch (IOException e){System.out.println("Error, check file path");}

    }

}
