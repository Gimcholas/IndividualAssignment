import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminInterface {

    JFrame frame;
    JLabel WelcomeMessage;
    JButton AddStaff;
    JButton AddDiscounts;
    JButton AddItems;
    JButton Logout;

    AdminInterface(String Name){
        frame = new JFrame();
        frame.setTitle("Admin ID: " + Name);
        frame.setSize(1000,800);
        
        // Welcome message
        WelcomeMessage = new JLabel("Welcome " + Name);
        WelcomeMessage.setFont(new Font(WelcomeMessage.getName(), Font.PLAIN, 30));
        System.out.println((int)WelcomeMessage.getPreferredSize().getHeight()+1);
        WelcomeMessage.setBounds(360 , 30, (int)WelcomeMessage.getPreferredSize().getWidth()+10, (int)WelcomeMessage.getPreferredSize().getHeight()+1);
        frame.add(WelcomeMessage);

        // Add Staff
        AddStaff = new JButton("Add Staff");
        AddStaff.setBounds(20, 150, 100, 30);
        frame.add(AddStaff);

        // Add Discounts
        AddDiscounts = new JButton("Add Discounts");
        AddDiscounts.setBounds(20, 200, 100, 30);
        frame.add(AddDiscounts);

        // Add Items
        AddItems = new JButton("Add Items");
        AddItems.setBounds(20, 250, 100, 30);
        frame.add(AddItems);

        // Logout
        Logout = new JButton("Logout");
        Logout.setBounds(20, 700, 100, 30);
        frame.add(Logout);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
