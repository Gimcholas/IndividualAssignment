import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

public class AdminInterface extends Interface implements ActionListener{

    JLabel WelcomeMessage;
    JButton AddStaff;
    JButton AddDiscounts;
    JButton AddItems;
    JButton Logout;
    String Name;

    public void MainMenu(){

        frame = new JFrame();
        frame.setTitle("Admin ID: " + Name);
        frame.setSize(1000,800);
        
        // Welcome message
        WelcomeMessage = new JLabel("Welcome " + Name);
        WelcomeMessage.setFont(new Font(WelcomeMessage.getName(), Font.PLAIN, 30)); // Set font
        WelcomeMessage.setBounds(360 , 30, (int)WelcomeMessage.getPreferredSize().getWidth()+10, (int)WelcomeMessage.getPreferredSize().getHeight()+1);
        frame.add(WelcomeMessage);

        // Add Staff
        AddStaff = new JButton("Add Staff");
        AddStaff.setBounds(20, 150, (int)AddStaff.getPreferredSize().getWidth()+1, 30);
        AddStaff.addActionListener(this);
        frame.add(AddStaff);

        // Add Discounts
        AddDiscounts = new JButton("Add Discounts");
        AddDiscounts.setBounds(20, 200, (int)AddDiscounts.getPreferredSize().getWidth()+1, 30);
        AddDiscounts.addActionListener(this);
        frame.add(AddDiscounts);

        // Add Items
        AddItems = new JButton("Add Items");
        AddItems.setBounds(20, 250, (int)AddItems.getPreferredSize().getWidth()+1, 30);
        AddItems.addActionListener(this);
        frame.add(AddItems);

        // Logout
        Logout = new JButton("Logout");
        Logout.setBounds(20, 700, (int)Logout.getPreferredSize().getWidth()+1, 30);
        Logout.addActionListener(this);
        frame.add(Logout);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton)e.getSource();
        
        // Change interface
        if(btn.getText() == "Add Staff"){new AddStaff().MainMenu();}
        else if(btn.getText() == "Add Discount"){new AddDiscount().MainMenu();}
        else if(btn.getText() == "Add Items"){new AddItems().MainMenu();}
        else if(btn.getText() == "Logout"){new Login().MainMenu();}
        else{System.out.println("Error, button not assigned an interface");}

        frame.dispose();

    }

    public void setName(String Name){this.Name = Name;}

}
