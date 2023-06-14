package AdminInterfaces;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Interface;

public class AddStaff extends Interface implements ActionListener{
    JLabel Notification;
    JTextField Name;
    JLabel TextName;
    JComboBox StaffTypeDropdown;
    String[] StaffType = {"Admin","Staff"};
    JButton Create;

    public void MainMenu() {
        // Create JFrame
        frame = new JFrame();
        frame.setTitle("AddStaff");
        frame.setSize(500,270);

        // Add Notification
        Notification = new JLabel();
        Notification.setBounds(185, 180, 150, 30);
        frame.add(Notification);

        // Add Text
        TextName = new JLabel("Staff ID: ");
        TextName.setBounds(100, 50, 50, 30);
        frame.add(TextName);

        // Add text field to get name
        Name = new JTextField();
        Name.setBounds(200, 50, 150, 30);
        frame.add(Name);

        // Add Combo Box
        StaffTypeDropdown = new JComboBox<>(StaffType);
        StaffTypeDropdown.setBounds(200, 100, 100, 30);
        frame.add(StaffTypeDropdown);
        
        // Add Create button
        Create = new JButton("Create");
        Create.setBounds(200, 150, 100, 30);
        Create.addActionListener(this);
        frame.add(Create);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}

    public void actionPerformed(ActionEvent evt){

        if(Name.getText() != null){
            // Compile new entry
            String newEntry = "\n|" + (String)StaffTypeDropdown.getSelectedItem() + "|" + Name.getText() + "| |";            
            // Add Staff
            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\Accounts.md";

            try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            
            // Error
            catch (IOException e) {System.out.println("Error, Check file path");}            
            new AdminInterface().MainMenu();
            frame.dispose();
        }
		else{Notification.setText("Please provide a valid names");}
    }

}
