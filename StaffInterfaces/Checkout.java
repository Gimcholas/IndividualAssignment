package StaffInterfaces;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Items;
import MainInterface.Login;


public class Checkout{

    List<Items> ListOfItems;
    JFrame frame;
    JPanel Bill;
    
    Checkout(List<Items> ListOfItems,String DiscountAmount,String BeforeDiscount, String AfterDiscount){

        // Create Frame
        frame = new JFrame();
        frame.setTitle("Bill");
        frame.setSize(500,800);

        // import list of items
        this.ListOfItems = ListOfItems;

        Bill = new JPanel();
        Bill.setSize(frame.getWidth(), frame.getHeight()-25);
        Bill.setBackground(Color.lightGray);
        InsertBill(ListOfItems, DiscountAmount, BeforeDiscount, AfterDiscount);
        frame.add((Bill));

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    private void InsertBill(List<Items> ListOfItems,String DiscountAmount,String BeforeDiscount, String AfterDiscount){

        Bill.removeAll();

        int GridRows=0;

        Bill.add(new JLabel("Item",JLabel.CENTER));

        Bill.add(new JLabel("Quantity",JLabel.CENTER));
        Bill.add(new JLabel("Price",JLabel.CENTER));
        Bill.add(new JLabel("Total",JLabel.CENTER));


        for (Items items : ListOfItems) {

            if(items.getCount() > 0){

                Bill.add(new JLabel(items.getName(),JLabel.CENTER));
                Bill.add(new JLabel(String.valueOf(items.getCount()),JLabel.CENTER));
                Bill.add(new JLabel(String.valueOf(items.getPrice()),JLabel.CENTER));
                Bill.add(new JLabel(String.valueOf(items.getCount()*items.getPrice()),JLabel.CENTER));

                deductStock(items, items.getCount());
                GridRows++;

            }
            
        }

        Bill.add(new JLabel("Before Discount: ",JLabel.CENTER));
        Bill.add(new JLabel(BeforeDiscount,JLabel.CENTER));
        for (int i = 0; i < 2; i++) {Bill.add(new JLabel());}

        Bill.add(new JLabel("Discount amount: ",JLabel.CENTER));
        Bill.add(new JLabel(DiscountAmount + "%",JLabel.CENTER));
        for (int i = 0; i < 2; i++) {Bill.add(new JLabel());}

        Bill.add(new JLabel("After Discount: ",JLabel.CENTER));
        Bill.add(new JLabel(AfterDiscount,JLabel.CENTER));
        for (int i = 0; i < 2; i++) {Bill.add(new JLabel());}


        Bill.setLayout(new GridLayout(GridRows + 4, 4));

        frame.revalidate();
        frame.repaint();

    }

    private void deductStock(Items items,int deductAmount){

        try{
            Path FILE_PATH = Paths.get("C:\\Users\\End User\\Documents\\MMU Stuff\\java\\IndividualAssignment\\Database\\Items.md");

            List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));

            int newAmount = items.getQuantity()-deductAmount;

            String oldEntry = "|" + items.getName() + "|" + (int)items.getPrice() + "|" + items.getQuantity() + "|" + items.getType() + "|";
            String newEntry = "|" + items.getName() + "|" + (int)items.getPrice() + "|" + newAmount + "|" + items.getType() + "|";

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldEntry)) {
                    fileContent.set(i, newEntry);
                    break;
                }
            }

            Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);

        }
        
        catch (IOException e){System.out.println("Error, check file path");}

    }

}
