import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    boolean itemsIsEnd = false;

    // Bills
    JLabel BillPanelText;
    JPanel BillPanel;
    JButton CheckOutButton;
    JLabel DiscountAmount;
    JLabel NetPayable;
    JButton NextBillPagae, PreviouBillPage;
    JLabel BillPage;
    JPanel BillNavigation;
    int BillPageNumber = 1;
    int BillRows = 20;
    boolean BillIsEnd = false;


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
        searchPanel.setBounds(0, 725, 400, 70);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(searchPanel);

        // Navigation Button
        NextPage = new JButton("Next >");
        NextPage.addActionListener(this);
        CurrentPage = new JLabel(String.valueOf(PageNumber));
        PreviousPage = new JButton("< Previous");
        PreviousPage.addActionListener(this);
        
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
        LoadItems();
        InsertItems();

        // Bill Panel
        BillPanel = new JPanel(new GridLayout(BillRows, 1));
        BillPanel.setBounds(700, 0, 300, 770);
        BillPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(BillPanel);

        // Bill Text
        BillPanelText = new JLabel("Bill:");
        BillPanelText.setFont(new Font(BillPanelText.getName(), Font.PLAIN, 30)); // Set font
        BillPanelText.setHorizontalAlignment(JLabel.CENTER);

        // Bill discount amount
        DiscountAmount = new JLabel("Discount: ");

        // NetPayable
        NetPayable = new JLabel("NetPayable: ");

        // Bill Navigation
        NextBillPagae = new JButton("Next >");
        NextBillPagae.setName("BillNavigation");
        NextBillPagae.addActionListener(this);
        BillPage = new JLabel(String.valueOf(BillPageNumber));
        PreviouBillPage = new JButton("< Previous");
        PreviouBillPage.setName("BillNavigation");
        PreviouBillPage.addActionListener(this);

        // Compile Bill Navigation panel
        BillNavigation = new JPanel(new FlowLayout(FlowLayout.CENTER));
        BillNavigation.setBackground(null);
        BillNavigation.add(PreviouBillPage);
        BillNavigation.add(BillPage);
        BillNavigation.add(NextBillPagae);

        showBill();

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
                    if(dataArray != null){this.ListOfItems.add(new Items(dataArray[1],Double.valueOf(dataArray[2]),Integer.valueOf(dataArray[3]),dataArray[4]));}

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
    private void InsertItems(){

        // Clear panel
        ItemListPanel.removeAll();

        ItemListPanel.add(TopPanel);

        int index = 0;

        // Add GridRow amount of entry
        for (int i = 0; i < (GridRow -1)* PageNumber; i++) {
            
            // If Items exist
            if(ListOfItems.size() > index){


                // Skip items with quantity of 0 and below
                while(Integer.valueOf(ListOfItems.get(index).getQuantity()) <= 0){index++;}

                // Compensate for page number and add entry
                if(i >= (GridRow-1) * (PageNumber-1)){
                    setItemEntry(index, i % (GridRow-1));
                    
                    // If there is still item entry, then its not the end of the page
                    itemsIsEnd = false;
                }
                
                index++;
            }

            // if no more items to add, add empty row, and signify end of page
            else{ItemListPanel.add(new JLabel()); itemsIsEnd = true;}
        }

    }

    private void setItemEntry(int index, int i){

        // Main entry
        JPanel ItemEntry = new JPanel(new GridLayout(1, 7));
        
        // Sections of the Main entry
        JLabel ItemName,ItemPrice,ItemQuantity, Seperation;
        JButton AddItem,SubtractItem;

        // Item name
        ItemName = new JLabel(ListOfItems.get(index).getName());
        ItemName.setName(String.valueOf("Name " + index));
        ItemName.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemName);

        // Item price
        ItemPrice = new JLabel(String.valueOf(ListOfItems.get(index).getPrice()));
        ItemPrice.setName(String.valueOf("Price " + index));
        ItemPrice.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemPrice);

        // Item Quantity
        ItemQuantity = new JLabel(String.valueOf(ListOfItems.get(index).getQuantity()));
        ItemQuantity.setName(String.valueOf("Quantity " + index));
        ItemQuantity.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemQuantity);

        // Seperation
        Seperation = new JLabel();
        ItemEntry.add(Seperation);
        
        // -
        SubtractItem = new JButton("-");
        SubtractItem.setName(String.valueOf(index)+":"+String.valueOf(i));  // we need index to set count in ListOfItems, i to set count in ItemListPanel
        SubtractItem.addActionListener(this);
        SubtractItem.setHorizontalAlignment(JButton.CENTER);
        ItemEntry.add(SubtractItem);

        // Quantity Count
        ItemCount[i] = new JLabel(String.valueOf(ListOfItems.get(index).getCount()));
        ItemCount[i].setName(String.valueOf("Count " + i));
        ItemCount[i].setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemCount[i]);

        // +
        AddItem = new JButton("+");
        AddItem.setName(String.valueOf(index)+":"+String.valueOf(i));   // we need index to set count in ListOfItems, i to set count in ItemListPanel
        AddItem.addActionListener(this);
        AddItem.setHorizontalAlignment(JButton.CENTER);
        ItemEntry.add(AddItem);

        // Add item entry
        ItemEntry.setSize(700, (int)ItemEntry.getPreferredSize().getHeight());
        ItemEntry.setBackground(null);
        ItemListPanel.add(ItemEntry);

    }


    private void showBill(){

        // Reset Bill is end
        BillIsEnd = false;

        // Setup Bill panel
        BillPanel.removeAll();

        BillPanel.add(BillPanelText);

        // empty space
        int EmptySpace = BillRows -4;

        // track amount of items on bill
        int itemTrack = 0;

        // Add added items entry
        for (int i = 0; i < ListOfItems.size() ; i++) {

            if(ListOfItems.get(i).getCount() > 0){

                System.out.println( (BillRows-4) * (BillPageNumber-1));

                // compensate for page number and add Bill entry
                if( itemTrack  >= (BillRows-4) * (BillPageNumber-1) && itemTrack < (BillRows-4) * (BillPageNumber)){

                    BillPanel.add(new JLabel(ListOfItems.get(i).getName() + ":" + ListOfItems.get(i).getCount()));
                    EmptySpace--;

                }

                itemTrack++;
            
            }
        }

        // Add empty space after all items are shown, if have empty space means its end of page
        for (int i = 0; i < EmptySpace; i++) {BillPanel.add(new JLabel()); BillIsEnd = true;}


        BillPanel.add(DiscountAmount);
        BillPanel.add(NetPayable);
        BillPanel.add(BillNavigation);

    }

    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton)e.getSource();

        if(btn.getText() == "+"){

            String[] Index_i = btn.getName().split(":");
            int index = Integer.valueOf(Index_i[0]);
            int i = Integer.valueOf(Index_i[1]);

            // only allow add when there is enough stock
            if(Integer.valueOf(ItemCount[i].getText()) < Integer.valueOf(ListOfItems.get(index).getQuantity())){
            
                ListOfItems.get(index).setCount(ListOfItems.get(index).getCount()+1);
                ItemCount[i].setText(String.valueOf(ListOfItems.get(index).getCount()));
            
            }

            showBill();

        
        }

        else if(btn.getText() == "-"){

            String[] Index_i = btn.getName().split(":");
            int index = Integer.valueOf(Index_i[0]);
            int i = Integer.valueOf(Index_i[1]);

            System.out.println(index + ":" + i);

            // only allow subtract if item count > 0
            if(Integer.valueOf(ItemCount[i].getText()) > 0){
                
                ListOfItems.get(index).setCount(ListOfItems.get(index).getCount()-1);
                ItemCount[i].setText(String.valueOf(ListOfItems.get(index).getCount()));            
            }

            showBill();

        
        }

        else if(btn.getText() == "Next >" && btn.getName() == "BillNavigation"){

            if(!BillIsEnd){

                BillPageNumber++;
                BillPage.setText(String.valueOf(BillPageNumber));

                showBill();

            }

        }

        else if(btn.getText() == "Next >" && !itemsIsEnd){

            PageNumber++;
            CurrentPage.setText(String.valueOf(PageNumber));
            InsertItems();

        }

        else if(btn.getText() == "< Previous" && btn.getName() == "BillNavigation"){

            if(BillPageNumber > 1){

                BillPageNumber--;
                BillPage.setText(String.valueOf(BillPageNumber));

                showBill();

            }

        }

        else if(btn.getText() == "< Previous" && PageNumber > 1){

            PageNumber--;
            CurrentPage.setText(String.valueOf(PageNumber));
            InsertItems();

        }


    }


    public static void main(String[] args) {
        new StaffInterface().MainMenu();
    }

}