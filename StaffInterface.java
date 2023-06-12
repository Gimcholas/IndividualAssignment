import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class StaffInterface extends Interface implements ActionListener{

    // Items
    int GridRow = 11,GridColumn = 1;
    int PageNumber = 1;
    JLabel ItemsPanelText;
    JPanel ItemsPanel;
    JPanel ItemListPanel;
    List<Items> ListOfItems = new ArrayList<Items>();
    JLabel NameText, PriceText, QuantityText;
    JPanel TopPanel;

    JLabel[] ItemCount = new JLabel[GridRow-1];

    
    // Search panel
    JPanel searchPanel;
    JButton SearchButton;
    JTextField SearchWord;

    // Next Previous
    JPanel NavigationPanel;
    JButton NextPage, PreviousPage;
    JLabel CurrentPage;

    // Bills
    JLabel BillPanelText;
    JPanel BillPanel;
    JButton CheckOutButton;
    JLabel DiscountAmount;
    JLabel NetPayable;

    public void MainMenu(){

        // Create Frame
        frame = new JFrame();
        frame.setTitle("Staff ID: " + Name);
        frame.setSize(1000,800);


        // Items Panel
        ItemsPanel = new JPanel();
        ItemsPanel.setBackground(Color.GRAY);
        ItemsPanel.setBounds(0, 0, 700, 725);
        ItemsPanel.setLayout(null);
        frame.add(ItemsPanel);

        // Items Text
        ItemsPanelText = new JLabel("Items:");
        ItemsPanelText.setFont(new Font(ItemsPanelText.getName(), Font.PLAIN, 30)); // Set font
        ItemsPanelText.setHorizontalAlignment(JLabel.CENTER);
        ItemsPanelText.setBounds(330 , 0, (int)ItemsPanelText.getPreferredSize().getWidth()+10, (int)ItemsPanelText.getPreferredSize().getHeight()+1);
        ItemsPanel.add(ItemsPanelText);

        // Item List Top Panel
        NameText = new JLabel("Item: ");
        NameText.setHorizontalAlignment(JLabel.CENTER);

        PriceText = new JLabel("Price:");
        PriceText.setHorizontalAlignment(JLabel.CENTER);

        QuantityText = new JLabel("Quantity");
        QuantityText.setHorizontalAlignment(JLabel.CENTER);

        TopPanel = new JPanel(new GridLayout(1, 7));
        TopPanel.add(NameText);
        TopPanel.add(PriceText);
        TopPanel.add(QuantityText);
        for (int i = 0; i < 4; i++) {TopPanel.add(new JLabel());}
        TopPanel.setSize(700, (int)TopPanel.getPreferredSize().getHeight());
        TopPanel.setBackground(null);

        // Item List Panel
        ItemListPanel = new JPanel(new GridLayout(GridRow,GridColumn));
        ItemListPanel.setBounds(0, (int)ItemsPanelText.getPreferredSize().getHeight()+1, 700, 700);
        ItemListPanel.setBackground(null);
        ItemsPanel.add(ItemListPanel);


        // Search Button
        SearchButton = new JButton("Search for Items: ");
        SearchButton.addActionListener(this);

        // Search Text Field
        SearchWord = new JTextField(15);

        // Search Panel
        searchPanel = new JPanel();
        searchPanel.add(SearchButton);
        searchPanel.add(SearchWord);
        searchPanel.setBackground(Color.GRAY);
        searchPanel.setBounds(0, 725, 400, 75);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(searchPanel);

        // Navigation Button
        NextPage = new JButton("Next >");
        CurrentPage = new JLabel(String.valueOf(PageNumber));
        PreviousPage = new JButton("< Previous");
        
        // Navigation panel
        NavigationPanel = new JPanel();
        NavigationPanel.setBounds(400, 725, 300, 75);
        NavigationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        NavigationPanel.setBackground(Color.GRAY);
        NavigationPanel.add(PreviousPage);
        NavigationPanel.add(CurrentPage);
        NavigationPanel.add(NextPage);
        frame.add(NavigationPanel);



        // Items Insertion
        InsertItems(PageNumber);

        // Bill Text
        BillPanelText = new JLabel("Bill:");
        BillPanelText.setFont(new Font(BillPanelText.getName(), Font.PLAIN, 30)); // Set font
        BillPanelText.setBounds(830 , 0, (int)BillPanelText.getPreferredSize().getWidth()+10, (int)BillPanelText.getPreferredSize().getHeight()+1);
        frame.add(BillPanelText);

        // Bill discount amount
        DiscountAmount = new JLabel("Discount: ");
        DiscountAmount.setBounds(710 , 650, 170 , (int)DiscountAmount.getPreferredSize().getHeight()+1);
        frame.add(DiscountAmount);

        // Bill Panel
        BillPanel = new JPanel();
        BillPanel.setBounds(700, 0, 300, 800);
        BillPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(BillPanel);



        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    // create a list of items (includes their name price and type so we can add to Items Panel)
    // Note: This is JUST THE CREATION !!! items does not get inserted into items panel YET
    private void LoadItems(){
        
        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            File myObj = new File("Database/Items.md");
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

                    // insert into Items list
                    if(dataArray != null){this.ListOfItems.add(new Items(dataArray[1],dataArray[2],dataArray[3],dataArray[4]));}

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

    // Insert List of Items into Items Panel
    private void InsertItems(int PageNumber){

        LoadItems();

        // As one page has its limit on how many items it can show,
        // we will have to set the index so the program knows from which index should the items be loaded
        int LoadIndex = (PageNumber * GridRow);


        ItemListPanel.add(TopPanel);
        // Add GridRow amount of entry
        for (int i = 0; i < GridRow - 1; i++) {

            // If Items exist
            if(ListOfItems.size() > i){

                // Skip items with quantity of 0 and below
                while(Integer.valueOf(ListOfItems.get(i).getQuantity()) <= 0){i++;}
                
                setItemEntry(i);
            }

            // if no more items to add, add empty row
            else{ItemListPanel.add(new JLabel());}
        }
        
    }

    private void setItemEntry(int i){

        // Main entry
        JPanel ItemEntry = new JPanel(new GridLayout(1, 7));
        
        // Sections of the Main entry
        JLabel ItemName,ItemPrice,ItemQuantity, Seperation;
        JButton AddItem,SubtractItem;

        // Item name
        ItemName = new JLabel(ListOfItems.get(i).getName());
        ItemName.setName(String.valueOf("Name " + i));
        ItemName.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemName);

        // Item price
        ItemPrice = new JLabel(ListOfItems.get(i).getPrice());
        ItemPrice.setName(String.valueOf("Price " + i));
        ItemPrice.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemPrice);

        // Item Quantity
        ItemQuantity = new JLabel(ListOfItems.get(i).getQuantity());
        ItemQuantity.setName(String.valueOf("Quantity " + i));
        ItemQuantity.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemQuantity);

        // Seperation
        Seperation = new JLabel();
        ItemEntry.add(Seperation);

        
        // -
        SubtractItem = new JButton("-");
        SubtractItem.setName(String.valueOf(i));
        SubtractItem.addActionListener(this);
        SubtractItem.setHorizontalAlignment(JButton.CENTER);
        ItemEntry.add(SubtractItem);

        // Quantity Count
        ItemCount[i] = new JLabel("0");
        ItemCount[i].setName(String.valueOf("Count " + i));
        ItemCount[i].setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemCount[i]);

        // +
        AddItem = new JButton("+");
        AddItem.setName(String.valueOf(i));
        AddItem.addActionListener(this);
        AddItem.setHorizontalAlignment(JButton.CENTER);
        ItemEntry.add(AddItem);

        // Add item entry
        ItemEntry.setSize(700, (int)ItemEntry.getPreferredSize().getHeight());
        ItemEntry.setBackground(null);
        ItemListPanel.add(ItemEntry);

    }

    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton)e.getSource();

        if(btn.getText() == "+"){
            int index = Integer.valueOf(btn.getName());

            // only allow add when there is enough stock
            if(Integer.valueOf(ItemCount[index].getText()) < Integer.valueOf(ListOfItems.get(index).getQuantity())){
            
                ItemCount[index].setText(String.valueOf(Integer.valueOf(ItemCount[index].getText()) + 1));
            
            }
        
        }

        if(btn.getText() == "-"){
            int index = Integer.valueOf(btn.getName());

            // only allow subtract if item count > 0
            if(Integer.valueOf(ItemCount[index].getText()) > 0){
                
                ItemCount[index].setText(String.valueOf(Integer.valueOf(ItemCount[index].getText()) - 1));
            
            }
        
        }
    }


    public static void main(String[] args) {
        new StaffInterface().MainMenu();
    }

}