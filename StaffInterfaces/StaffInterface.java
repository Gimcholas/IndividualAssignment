package StaffInterfaces;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Interface;
import Classes.Items;
import Database.Database;

import java.awt.*;
import java.util.List;
import java.awt.event.*;


public class StaffInterface extends Interface implements ActionListener{

    // Items
    int GridRow = 11,GridColumn = 1;    // Grid row must be 2 and above
    int PageNumber = 1;
    JLabel ItemsPanelText;
    JPanel ItemsPanel;
    JPanel ItemListPanel;
    List<Items> ListOfItems = new Database().getListOfItems();
    JLabel NameText, PriceText, TypeText, QuantityText;
    JPanel TopPanel;
    JLabel[] ItemCount = new JLabel[GridRow-1];

    // Next Previous
    JPanel NavigationPanel;
    JButton NextPage, PreviousPage;
    JLabel CurrentPage;
    boolean itemsIsEnd = false;

    // Bills
    JLabel BillText;
    JPanel BillPanel;
    int BillRows = 20;  // Must be MORE THAN 4 = 5 and above
    int BillColumns = 4;
    JButton CheckOutButton;
    int DiscountAmount=0;
    double BeforeDiscount;
    double NetPayable;

    // Bill Navigation
    JButton NextBillPage, PreviouBillPage;
    JLabel BillPage;
    int BillPageNumber = 1; // Do not touch
    boolean BillIsEnd = false;

    // Discounts
    JButton DiscountButton;
    //Bundled Discount
    String item1 = null,item2 = null;
    int DiscountBuffer=0;
    boolean containItem1 = false,containItem2 = false;



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

        TypeText = new JLabel("Type:");
        TypeText.setHorizontalAlignment(JLabel.CENTER);

        QuantityText = new JLabel("Stock:");
        QuantityText.setHorizontalAlignment(JLabel.CENTER);

        TopPanel = new JPanel(new GridLayout(1, 7));
        TopPanel.add(NameText);
        TopPanel.add(PriceText);
        TopPanel.add(TypeText);
        TopPanel.add(QuantityText);
        for (int i = 0; i < 3; i++) {TopPanel.add(new JLabel());}
        TopPanel.setSize(700, (int)TopPanel.getPreferredSize().getHeight());
        TopPanel.setBackground(null);

        // Item List Panel
        ItemListPanel = new JPanel(new GridLayout(GridRow,GridColumn));
        ItemListPanel.setBounds(0, (int)ItemsPanelText.getPreferredSize().getHeight()+1, 700, 700);
        ItemListPanel.setBackground(null);
        ItemsPanel.add(ItemListPanel);

        // Navigation Button
        NextPage = new JButton("Next >");
        NextPage.addActionListener(this);
        CurrentPage = new JLabel(String.valueOf(PageNumber));
        PreviousPage = new JButton("< Previous");
        PreviousPage.addActionListener(this);
        CheckOutButton = new JButton("Checkout");
        CheckOutButton.addActionListener(this);
        
        // Navigation panel
        NavigationPanel = new JPanel();
        NavigationPanel.setBounds(0, 725, 700, 75);
        NavigationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        NavigationPanel.setBackground(Color.GRAY);
        NavigationPanel.add(PreviousPage);
        NavigationPanel.add(CurrentPage);
        NavigationPanel.add(NextPage);
        frame.add(NavigationPanel);



        // Items Insertion
        ListOfItems = new Database().getListOfItems();
        InsertItems();

        // Bill Panel
        BillPanel = new JPanel(new GridLayout(BillRows, 1));
        BillPanel.setBounds(700, 0, 290, 770);
        BillPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(BillPanel);

        // Bill Text
        BillText = new JLabel("Bill:");
        BillText.setFont(new Font(BillText.getName(), Font.PLAIN, 30)); // Set font
        BillText.setHorizontalAlignment(JLabel.CENTER);

        // Before discount
        BeforeDiscount = 0;

        // NetPayable
        NetPayable = 0;


        // Bill Navigation
        NextBillPage = new JButton(">");
        NextBillPage.setName("BillNavigation");
        NextBillPage.addActionListener(this);
        BillPage = new JLabel(String.valueOf(BillPageNumber),JLabel.CENTER);
        PreviouBillPage = new JButton("<");
        PreviouBillPage.setName("BillNavigation");
        PreviouBillPage.addActionListener(this);

        // Discount button
        DiscountButton = new JButton("Discount");
        DiscountButton.addActionListener(this);



        showBill();
        

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

                    ItemListPanel.add(createItemEntryPanel(index, i % (GridRow-1)));
                    
                    // If there is still item entry, then its not the end of the page
                    itemsIsEnd = false;
                }
                
                index++;
            }

            // if no more items to add, add empty row, and signify end of page
            else{ItemListPanel.add(new JLabel()); itemsIsEnd = true;}
        }

        //https://stackoverflow.com/a/10367754/15149509
        ItemsPanel.revalidate();
        ItemsPanel.repaint();

    }


    // Solely CREATING JPanel, does not add to anything in and of itself
    private JPanel createItemEntryPanel(int index, int i){

        // Main entry
        JPanel ItemEntry = new JPanel(new GridLayout(1, 7));
        
        // Sections of the Main entry
        JLabel ItemName,ItemPrice,ItemType,ItemQuantity;
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

        // Seperation
        ItemType = new JLabel(String.valueOf(ListOfItems.get(index).getType()));
        ItemType.setName(String.valueOf("Quantity " + index));
        ItemType.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemType);

        // Item Quantity
        ItemQuantity = new JLabel(String.valueOf(ListOfItems.get(index).getQuantity()));
        ItemQuantity.setName(String.valueOf("Quantity " + index));
        ItemQuantity.setHorizontalAlignment(JLabel.CENTER);
        ItemEntry.add(ItemQuantity);
        
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
        
        return ItemEntry;

    }


    private void showBill(){

        // not items (to compensate for text and stuff)
        int AmountOfnotItems = 6;


        // Reset Bill is end
        BillIsEnd = false;

        // Price
        double Price=0;
        
        // Setup Bill panel
        BillPanel.removeAll();

        BillPanel.add(BillText);
        for (int i = 0; i < BillColumns-1; i++) {BillPanel.add(new JLabel());}

        BillPanel.add(new JLabel("Item",JLabel.CENTER));
        BillPanel.add(new JLabel("Quantity",JLabel.CENTER));
        BillPanel.add(new JLabel("Price",JLabel.CENTER));
        BillPanel.add(new JLabel("Total",JLabel.CENTER));

        // empty space
        int EmptySpace = BillRows - AmountOfnotItems;

        // track amount of items on bill
        int itemTrack = 0;

        // reset
        containItem1 = containItem2 = false;
        
        // Add added items entry
        for (int i = 0; i < ListOfItems.size() ; i++) {

            if(ListOfItems.get(i).getCount() > 0){

                Price+=ListOfItems.get(i).getPrice() * ListOfItems.get(i).getCount();

                // Bundled discount check
                if(item1!= null && ListOfItems.get(i).getType().compareTo(item1) == 0){containItem1=true;}
                if(item2 != null && ListOfItems.get(i).getType().compareTo(item2) == 0){containItem2=true;}

                // compensate for page number and add Bill entry
                if( itemTrack  >= (BillRows-AmountOfnotItems) * (BillPageNumber-1) && itemTrack < (BillRows-AmountOfnotItems) * (BillPageNumber)){

                    BillPanel.add(new JLabel(ListOfItems.get(i).getName(),JLabel.CENTER));
                    BillPanel.add(new JLabel(String.valueOf(ListOfItems.get(i).getCount()),JLabel.CENTER));
                    BillPanel.add(new JLabel(String.valueOf(ListOfItems.get(i).getPrice()),JLabel.CENTER));
                    BillPanel.add(new JLabel(String.valueOf(ListOfItems.get(i).getCount() * ListOfItems.get(i).getPrice()),JLabel.CENTER));

                    EmptySpace--;

                }

                itemTrack++;
            
            }

        }

        // check bundled discount
        if( item1 != null){

            if(containItem1 && containItem2){DiscountAmount = DiscountBuffer;}
            
            else{DiscountAmount = 0;}

        }

        // Add empty space after all items are shown, if have empty space means its end of page
        for (int i = 0; i < EmptySpace*BillColumns; i++) {

            BillPanel.add(new JLabel());
            BillIsEnd = true;
        
        }

        // calculate discount amount in 0.%% format
        double discountDouble = 100 - DiscountAmount;
        discountDouble /= 100;

        BeforeDiscount = Price;
        NetPayable = Price * discountDouble;

        BillPanel.add(new JLabel ("Before: "));
        BillPanel.add(new JLabel(String.valueOf(BeforeDiscount)));
        for (int i = 0; i < BillColumns-2; i++) {BillPanel.add(new JLabel());}

        BillPanel.add(new JLabel("Discount: "));
        BillPanel.add(new JLabel(String.valueOf(String.valueOf(DiscountAmount) + "%")));
        BillPanel.add(new JLabel(item1));
        BillPanel.add(new JLabel(item2));
        for (int i = 0; i < BillColumns-4; i++) {BillPanel.add(new JLabel());}


        BillPanel.add(new JLabel("Net: "));
        BillPanel.add(new JLabel(String.valueOf(NetPayable)));
        for (int i = 0; i < BillColumns-3; i++) {BillPanel.add(new JLabel());}
        BillPanel.add(DiscountButton);

        BillPanel.add(PreviouBillPage);
        BillPanel.add(BillPage);
        BillPanel.add(NextBillPage);
        BillPanel.add(CheckOutButton);
        for (int i = 0; i < BillColumns-4; i++) {BillPanel.add(new JLabel());}


        //https://stackoverflow.com/a/10367754/15149509
        BillPanel.revalidate();
        BillPanel.repaint();

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

        }

        else if(btn.getText() == "-"){

            String[] Index_i = btn.getName().split(":");
            int index = Integer.valueOf(Index_i[0]);
            int i = Integer.valueOf(Index_i[1]);

            // only allow subtract if item count > 0
            if(Integer.valueOf(ItemCount[i].getText()) > 0){
                
                ListOfItems.get(index).setCount(ListOfItems.get(index).getCount()-1);
                ItemCount[i].setText(String.valueOf(ListOfItems.get(index).getCount()));            
            }

        }

        else if(btn.getText() == ">"){

            if(!BillIsEnd){

                BillPageNumber++;
                BillPage.setText(String.valueOf(BillPageNumber));

            }

        }

        else if(btn.getText() == "Next >" && !itemsIsEnd){

            PageNumber++;
            CurrentPage.setText(String.valueOf(PageNumber));

        }

        else if(btn.getText() == "<"){

            if(BillPageNumber > 1){

                BillPageNumber--;
                BillPage.setText(String.valueOf(BillPageNumber));

            }

        }

        else if(btn.getText() == "< Previous" && PageNumber > 1){

            PageNumber--;
            CurrentPage.setText(String.valueOf(PageNumber));

        }

        else if(btn.getText() == "Discount"){
            new DiscountInterface(this).MainMenu();
        }

        else if(btn.getText() == "Checkout"){
            
            new Checkout(ListOfItems, String.valueOf(DiscountAmount), String.valueOf(BeforeDiscount), String.valueOf(NetPayable));
            DiscountBuffer=DiscountAmount=0;
            item1=item2=null;
            containItem1=containItem2=false;
            ListOfItems = new Database().getListOfItems();

        }

        InsertItems();
        showBill();

    }


    // Define all types of discounts here

    public void setDiscount(String DiscountAmount){

        // no condition required to apply discount
        item1 = item2 = null;
        

        this.DiscountAmount = Integer.valueOf(DiscountAmount);
        showBill();
    
    }


    public void setBundledDiscount(String item1, String item2, String DiscountAmount){

        // conditional applicable discount
        this.item1 = item1;
        this.item2 = item2;
        this.DiscountBuffer = Integer.valueOf(DiscountAmount);
        containItem1 = false;
        containItem2 = false;
        this.DiscountAmount=0;
        showBill();

    }

    // end of define discount

}