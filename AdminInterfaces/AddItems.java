package AdminInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Interface;

public class AddItems extends Interface implements ActionListener{

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

        LoadDropdown();

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
        if(!noDuplicateName.contains(NewItem.getText()) && NewType != null && Create.getText() == "Create new Type"){addItem(buffer + NewType.getText() + "|");}

        // Check if item already exist and compile new entry
        else if(!noDuplicateName.contains(NewItem.getText()) && NewType != null){addItem("\n|" + NewItem.getText() + "|" + NewPrice.getText() + "|" + NewQuantity.getText() + "|" + TypeDropdown.getSelectedItem() + "|");}
        
        // Check if new type
        else if(!noDuplicateName.contains(NewItem.getText()) && TypeDropdown.getSelectedItem() == "New Type"){getNewType("\n|" + NewItem.getText() + "|" + NewPrice.getText() + "|" + NewQuantity.getText() + "|");}

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

    private void addItem(String newEntry){

        String s = System.getProperty("user.dir");
        Path currentRelativePath = Paths.get(s);
        s = currentRelativePath.toString()+"\\Database\\Items.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            

        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}
        frame.dispose();

    }

    private void LoadDropdown(){

        List<String> noDuplicateType = new ArrayList<String>();


        TypeDropdown.addItem("New Type");

        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\Items.md";

            File myObj = new File(s);
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

                    // insert into Dropdown and update noDuplicateType list
                    
                    if(dataArray != null && !noDuplicateType.contains(dataArray[4])){
                        TypeDropdown.addItem(dataArray[4]);
                        noDuplicateType.add(dataArray[4]);
                        noDuplicateName.add(dataArray[1]);
                    }

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

    public static void main(String[] args) {
        new AddItems().MainMenu();   
    }

}
