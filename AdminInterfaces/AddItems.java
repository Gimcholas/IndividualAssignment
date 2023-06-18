package AdminInterfaces;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;

public class AddItems extends AdminInterface{

    JPanel MainPanel;
    JLabel ItemName,Price,Quantity,Type;
    JComboBox<String> TypeDropdown;
    JTextField NewItem,NewPrice,NewQuantity,NewType;
    String buffer;
    List<String> noDuplicateName = new ArrayList<String>();
    JButton Create;

    public void MainMenu() {

        // Create JFrame
        frame = new JFrame();
        frame.setTitle("Add Items");
        frame.setSize(500,350);

        MainPanel = new JPanel();
        MainPanel.setBounds(0, 0, 500, 350);
        MainPanel.setLayout(null);
        frame.add(MainPanel);

        // Add Text
        ItemName = new JLabel("Item Name:");
        ItemName.setBounds(140, 50, 100, 30);
        MainPanel.add(ItemName);

        // Add TextField
        NewItem = new JTextField();
        NewItem.setBounds(210, 50, 100, 30);
        MainPanel.add(NewItem);

        // Add Text
        Price = new JLabel("Price:");
        Price.setBounds(140, 90, 100, 30);
        MainPanel.add(Price);

        // Add TextField
        NewPrice = new JTextField();
        NewPrice.setBounds(210, 90, 100, 30);
        MainPanel.add(NewPrice);

        // Add Text
        Quantity = new JLabel("Quantity:");
        Quantity.setBounds(140, 130, 100, 30);
        MainPanel.add(Quantity);

        // Add TextField
        NewQuantity = new JTextField();
        NewQuantity.setBounds(210, 130, 100, 30);
        MainPanel.add(NewQuantity);

        // Add Text
        Type = new JLabel("Type:");
        Type.setBounds(140, 210, 100, 30);
        MainPanel.add(Type);

        // Add dropdown
        TypeDropdown = new JComboBox<>();
        TypeDropdown.setBounds(180, 210, 150, 30);
        MainPanel.add(TypeDropdown);

        new Database().LoadDropdown(TypeDropdown,noDuplicateName);

        // Add Create button
        Create = new JButton("Create");
        Create.setBounds(210, 250, 100, 30);
        Create.addActionListener(this);
        MainPanel.add(Create);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}

    public void actionPerformed(ActionEvent evt){

        // Get new type and set new entry
        if(!noDuplicateName.contains(NewItem.getText()) && NewType != null && Create.getText() == "Create new Type"){
            new Database().addItem(buffer + NewType.getText() + "|");
            frame.dispose();
        }

        // Check if new type
        else if(!noDuplicateName.contains(NewItem.getText()) && TypeDropdown.getSelectedItem() == "New Type"){getNewType("\n|" + NewItem.getText() + "|" + NewPrice.getText() + "|" + NewQuantity.getText() + "|");}

        // Check if item already exist and compile new entry
        else if(!noDuplicateName.contains(NewItem.getText())){
            new Database().addItem("\n|" + NewItem.getText() + "|" + NewPrice.getText() + "|" + NewQuantity.getText() + "|" + TypeDropdown.getSelectedItem() + "|");
            frame.dispose();
        }

        // item already exist
        else{System.out.println("Item Already Exist");}

    }

    private void getNewType(String buffer){

        MainPanel.removeAll();

        this.buffer = buffer;

        // Add Text
        Type = new JLabel("New Type:");
        Type.setBounds(140, 90, 100, 30);
        MainPanel.add(Type);

        // Add TextField
        NewType = new JTextField();
        NewType.setBounds(210, 90, 100, 30);
        MainPanel.add(NewType);

        // Add Create button
        Create = new JButton("Create new Type");
        Create.setBounds(210, 250, 100, 30);
        Create.addActionListener(this);
        MainPanel.add(Create);
        
        MainPanel.revalidate();
        MainPanel.repaint();

    }

}
