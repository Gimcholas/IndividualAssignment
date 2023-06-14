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
import javax.swing.JTextField;

import Classes.Interface;

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

        LoadDropdown();

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

        String s = System.getProperty("user.dir");
        Path currentRelativePath = Paths.get(s);
        s = currentRelativePath.toString()+"\\Database\\Discounts.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            

        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}
        frame.dispose();

    }


    private void LoadDropdown(){

        List<String> noDuplicate = new ArrayList<String>();

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

                    // insert into Dropdown and update noDuplicate list
                    
                    if(dataArray != null && !noDuplicate.contains(dataArray[4])){
                        Pair1Dropdown.addItem(dataArray[4]);
                        Pair2Dropdown.addItem(dataArray[4]);
                        noDuplicate.add(dataArray[4]);
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
}
