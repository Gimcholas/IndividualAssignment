package AdminInterfaces;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Interface;
import Database.Database;

public class AddDiscount extends Interface implements ActionListener{
    
    JLabel Pair1,Pair2;
    JComboBox<String> Pair1Dropdown,Pair2Dropdown;
    JTextField DiscountAmountTextField;
    JLabel DiscountText;
    JButton Create;

    public void MainMenu() {
        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Add Discounts");
        frame.setSize(500,270);


        // Add Text
        Pair1 = new JLabel("Pair 1");
        Pair1.setBounds(140, 50, 150, 30);
        frame.add(Pair1);

        // Add Pair 1 dropdown
        Pair1Dropdown = new JComboBox<>();
        Pair1Dropdown.setBounds(210, 50, 150, 30);
        frame.add(Pair1Dropdown);

        // Add Text
        Pair2 = new JLabel("Pair 2");
        Pair2.setBounds(140, 90, 150, 30);
        frame.add(Pair2);

        // Add Pair 2 dropdown
        Pair2Dropdown = new JComboBox<>();
        Pair2Dropdown.setBounds(210, 90, 150, 30);
        frame.add(Pair2Dropdown);

        new Database().LoadDropdown(Pair1Dropdown,Pair2Dropdown);

        // Discount text
        DiscountText = new JLabel("Discount amount %");
        DiscountText.setBounds(150, 130, 110, 30);
        frame.add(DiscountText);

        // Add discount amount text field
        DiscountAmountTextField = new JTextField();
        DiscountAmountTextField.setBounds(270, 130, 50, 30);
        frame.add(DiscountAmountTextField);

        // Add Create button
        Create = new JButton("Create");
        Create.setBounds(200, 170, 100, 30);
        Create.addActionListener(this);
        frame.add(Create);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}

    public void actionPerformed(ActionEvent evt){

        // Compile new entry
        String newEntry = "\n|" + (String)Pair1Dropdown.getSelectedItem() + "|" + Pair2Dropdown.getSelectedItem() + "|" + DiscountAmountTextField.getText() + "|";            
        new Database().addNewBundledDiscount(newEntry);
        frame.dispose();

    }

}
