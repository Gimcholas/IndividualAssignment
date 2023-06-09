import java.awt.event.*;
import javax.swing.*;
import java.util.*;  

import java.io.File;
import java.io.FileNotFoundException;

public class Login implements ActionListener{
    
    JFrame frame;
    JLabel Notification;
    JTextField Name;
    JLabel TextName;
    JPasswordField Password;
    JLabel TextPassword;
    JButton Login;

    List<Account> accounts = new ArrayList<Account>(); 

    Login(){

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setSize(500,270);

        // Add Notification
        Notification = new JLabel();
        Notification.setBounds(185, 180, 150, 30);
        frame.add(Notification);

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

    // create a list of accounts (includes their name password and roles so we can do checking for login)
    private void LoadAccounts(){
        
        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            File myObj = new File("Database/Accounts.md");
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
                    this.accounts.add(new Account(dataArray[1],dataArray[2],dataArray[3]));

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

    private boolean CheckAccount(String Name, String Password){

        return accounts.stream().anyMatch(acc -> acc.getName().equals(Name) && acc.getPassword().equals(Password));
    }

    // if Login button is clicked
    public void actionPerformed(ActionEvent e){

        LoadAccounts();

        // If Account exist
        if (CheckAccount(Name.getText(), String.valueOf(Password.getPassword()))){
            new StaffInterface(Name.getText());
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