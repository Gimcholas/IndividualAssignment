package StaffInterfaces;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;

public class DiscountInterface extends StaffInterface{


    // Pick discount type
    JButton confirmButton;
    JComboBox<String> DiscountTypeDropdown;
    String[] DiscountType = {"Royalty Discount ","Bundled discount "};

    // Royalty discount
    JTextField RoyaltyNumberText;
    JPanel DiscountPanel;
    JComboBox<String> RoyaltyDropdown;
    String[] RoyaltyType = {"3","5","10"};


    // Bundled Discount
    JComboBox<String> BundleDropdown;

    // Notification errorMessage
    JPanel errorPanel;
    JLabel errorMessage;

    // Cancel button
    JButton cancelButton;

    // Reference to set discount
    StaffInterface staffInterface;

    DiscountInterface(StaffInterface staffInterface){this.staffInterface = staffInterface;}

    public void MainMenu() {

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Discounts");
        frame.setSize(500,350);


        // Create selection panel
        DiscountPanel = new JPanel();
        DiscountPanel.setBounds(0, 125, 500, 35);
        frame.add(DiscountPanel);

        // Dropdown menu
        DiscountTypeDropdown = new JComboBox<>(DiscountType);
        DiscountPanel.add(DiscountTypeDropdown);

        // Confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setName("PickDiscount");
        confirmButton.addActionListener(this);
        DiscountPanel.add(confirmButton);

        // Notification
        errorMessage = new JLabel();

        // Notification panel
        errorPanel = new JPanel();
        errorPanel.setBounds(0,210,500,30);
        errorPanel.add(errorMessage);
        frame.add(errorPanel);

        // Cancel
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210,250,(int)cancelButton.getPreferredSize().getWidth() + 1, (int)cancelButton.getPreferredSize().getHeight() + 1);
        cancelButton.addActionListener(this);
        frame.add(cancelButton);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void RoyaltyCardDiscount(){

        // Clear frame
        DiscountPanel.removeAll();

        // Add text
        DiscountPanel.add(new JLabel("Royalty Number: "));

        // Create Text Field
        RoyaltyNumberText = new JTextField(13);
        DiscountPanel.add(RoyaltyNumberText);

        // confirm button
        confirmButton.setName("RoyaltyDiscount");
        DiscountPanel.add(confirmButton);


        //https://stackoverflow.com/a/10367754/15149509
        DiscountPanel.revalidate();
        DiscountPanel.repaint();


    }

    private void BundledDiscount(){

        // Clear frame
        DiscountPanel.removeAll();

        BundleDropdown = new Database().loadBundledDiscount();

        // add choices
        DiscountPanel.add(BundleDropdown);

        // add confirm button
        confirmButton.setName("BundledDiscount");
        DiscountPanel.add(confirmButton);

        //https://stackoverflow.com/a/10367754/15149509
        DiscountPanel.revalidate();
        DiscountPanel.repaint();
    }


    // Select Royalty discount amount
    private void SelectRoyaltyDiscountAmount(String RoyaltyCardNumber){

        
        // Clear frame
        DiscountPanel.removeAll();

        // Add description
        DiscountPanel.add(new JLabel("5% = 5 points"));

        // Create dropdown to select discount amount
        RoyaltyDropdown = new JComboBox<>(RoyaltyType);
        DiscountPanel.add(RoyaltyDropdown);

        DiscountPanel.add(new JLabel("Current points: " + new Database().getPoints(RoyaltyCardNumber)));

        // Confirm button
        confirmButton.setName("Select Discount Amount");
        DiscountPanel.add(confirmButton);


        //https://stackoverflow.com/a/10367754/15149509
        DiscountPanel.revalidate();
        DiscountPanel.repaint();

    }

    public void actionPerformed(ActionEvent e){

        JButton btn = (JButton)e.getSource();

        // if Choice is Royalty discount, create royalty discount panel
        if(btn.getText() == "Confirm" && btn.getName() == "PickDiscount"){
            if((String)DiscountTypeDropdown.getSelectedItem() == "Royalty Discount " ){RoyaltyCardDiscount();}
            else{BundledDiscount();}
        }

        else if(btn.getText() == "Confirm" && btn.getName() == "RoyaltyDiscount"){

            // Check card existance
            if(new Database().cardExist(RoyaltyNumberText.getText())){

                SelectRoyaltyDiscountAmount(RoyaltyNumberText.getText());
            
            }
            
            // error message
            else{errorMessage.setForeground(Color.red);errorMessage.setText("Error, card does not exist");}

        }

        else if(btn.getName() == "BundledDiscount"){

            String[] dataArray = BundleDropdown.getSelectedItem().toString().split(" for ");
            String[] discountAmount = dataArray[1].split("%");

            dataArray = BundleDropdown.getSelectedItem().toString().split(" ");
            staffInterface.setBundledDiscount(dataArray[0], dataArray[2], discountAmount[0]);
            frame.dispose();

        }

        else if(btn.getText() == "Confirm" && btn.getName() == "Select Discount Amount"){

            // try to deduct from card
            if(new Database().scanRoyaltyCard(RoyaltyNumberText.getText(),(String)RoyaltyDropdown.getSelectedItem())){
                staffInterface.setDiscount((String)RoyaltyDropdown.getSelectedItem());
                frame.dispose();
            }

            // if not sufficient points
            else{errorMessage.setText("Not Enough Points !");}
            
        }

        
        // Cancel button
        else if(btn.getText() == "Cancel"){frame.dispose();}

    }

}
