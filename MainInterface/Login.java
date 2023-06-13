package MainInterface;
import java.awt.event.*;
import javax.swing.*;

import AdminInterfaces.AdminInterface;
import Classes.Account;
import Classes.Interface;
import StaffInterfaces.StaffInterface;

import java.util.*;  

import java.io.File;
import java.io.FileNotFoundException;

public class Login extends Interface implements ActionListener{
    
    JLabel Notification;
    JTextField Name;
    JLabel TextName;
    JPasswordField Password;
    JLabel TextPassword;
    JButton Login;

    List<Account> accounts = new ArrayList<Account>(); 

    public void MainMenu(){

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(500,270);

        // Add Notification
        Notification = new JLabel();
        Notification.setBounds(185, 180, 150, 30);
        frame.add(Notification);

        // Add Text
        TextName = new JLabel("Name : ");
        TextName.setBounds(100, 50, 50, 30);
        frame.add(TextName);

        // Add text field to get name
        Name = new JTextField();
        Name.setBounds(200, 50, 150, 30);
        frame.add(Name);

        // Add Text
        TextPassword = new JLabel("Password: ");
        TextPassword.setBounds(100, 100, 100, 30);
        frame.add(TextPassword);

        // Add text field to get pw
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

    // create a list of accounts (includes their name password and roles so we can do checking for login)
    private void LoadAccounts(){
        
        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            File myObj = new File("C:\\Users\\End User\\Documents\\MMU Stuff\\java\\IndividualAssignment\\Database\\Accounts.md");
            Scanner myReader = new Scanner(myObj);

            // First two lines are garbage
            int skipFirstTwoLines = 0;

            // Read line by line
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // Skip first two lines
                if(skipFirstTwoLines >= 2){

                    //https://stackoverflow.com/a/7935873/15149509
                    String[] dataArray = data.split("\\|");

                    // insert into account list
                    if(dataArray != null){this.accounts.add(new Account(dataArray[1],dataArray[2],dataArray[3]));}

                }
                
                skipFirstTwoLines ++;
            }

            myReader.close();
        }
        
        // error
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    // Check for the existance of the account, and return its type
    private int CheckAccount(String Name, String Password){

        // If admin, return 1
        if(accounts.stream().anyMatch(acc -> acc.getName().equals(Name) && acc.getPassword().equals(Password) && acc.getRole().equals("Admin"))){return 1;}
   
        // If Staff, return 2
        else if(accounts.stream().anyMatch(acc -> acc.getName().equals(Name) && acc.getPassword().equals(Password) && acc.getRole().equals("Staff"))){return 2;}

        // If new Admin account, return 3
        else if(accounts.stream().anyMatch(acc -> acc.getName().equals(Name) && acc.getPassword().equals(" ") && acc.getRole().equals("Admin"))){return 3;}

        // If new Staff account, return 4
        else if(accounts.stream().anyMatch(acc -> acc.getName().equals(Name) && acc.getPassword().equals(" ") && acc.getRole().equals("Staff"))){return 4;}

        // Not found, return -1
        return -1;
   
    }

    // if Login button is clicked
    public void actionPerformed(ActionEvent e){

        LoadAccounts();

        // If Account is Admin
        if (CheckAccount(Name.getText(), String.valueOf(Password.getPassword())) == 1){
            this.setName(Name.getText());
            this.setRole("Admin");
            new AdminInterface().MainMenu();
            frame.dispose();
        }

        // If Account is Staff
        else if(CheckAccount(Name.getText(), String.valueOf(Password.getPassword())) == 2){
            this.setName(Name.getText());
            this.setRole("Staff");
            new StaffInterface().MainMenu();
            frame.dispose();
        }

        // If Account is New Admin Account
        else if(CheckAccount(Name.getText(), String.valueOf(Password.getPassword())) == 3){
            this.setName(Name.getText());
            new SetPassword().MainMenu();
            frame.dispose();

        }

        // If Account is New Staff Account
        else if(CheckAccount(Name.getText(), String.valueOf(Password.getPassword())) == 4){
            this.setName(Name.getText());
            this.setRole("Staff");
            new SetPassword().MainMenu();
            frame.dispose();

        }

        // Account does not exist
        else{
            Name.setText("");
            Password.setText("");
            Notification.setText("Account does not exist");
        }
        
    }

}
