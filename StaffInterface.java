import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    int GridRow = 10,GridColumn = 6;
    int PageNumber = 1;
    JLabel ItemsPanelText;
    JPanel ItemsPanel;
    JPanel ItemListPanel;
    List<Items> ListOfItems = new ArrayList<Items>();
    
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
        ItemsPanelText.setBounds(330 , 0, (int)ItemsPanelText.getPreferredSize().getWidth()+10, (int)ItemsPanelText.getPreferredSize().getHeight()+1);
        ItemsPanel.add(ItemsPanelText);

        // Item List Panel
        ItemListPanel = new JPanel(new GridLayout(GridRow,GridColumn));
        ItemListPanel.setBounds(0, (int)ItemsPanelText.getPreferredSize().getHeight()+1, 700, 800 - ((int)ItemsPanelText.getPreferredSize().getHeight()+1));
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
                    if(dataArray != null){this.ListOfItems.add(new Items(dataArray[1],dataArray[2],dataArray[3]));}

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

        for (int i = 0; i < GridRow; i++) {
            if(ListOfItems.size() > i){

                ItemListPanel.add(new JLabel(ListOfItems.get(i).getName()));
                ItemListPanel.add(new JLabel(ListOfItems.get(i).getPrice()));
                ItemListPanel.add(new JLabel());
                ItemListPanel.add(new JButton("-"));
                ItemListPanel.add(new JLabel("0"));
                ItemListPanel.add(new JButton("+"));
            }

            // if no more items to add, add empty row
            else{for (int j = 0; j < GridColumn; j++) {ItemListPanel.add(new JLabel());}}
        }
        
    }

    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton)e.getSource();

    }


    public static void main(String[] args) {
        new StaffInterface().MainMenu();
    }

}