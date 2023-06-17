package Database;

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

import javax.swing.JComboBox;

import Classes.Items;

public class ItemsDatabase {

    List<Items> ListOfItems = new ArrayList<Items>();
    
    protected List<Items> LoadItems(){

        ListOfItems.clear();
        
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

        return ListOfItems;


    }

    protected void LoadDropdown(JComboBox<String> Pair1Dropdown,JComboBox<String> Pair2Dropdown){

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

    protected void addItem(String newEntry){

        String s = System.getProperty("user.dir");
        Path currentRelativePath = Paths.get(s);
        s = currentRelativePath.toString()+"\\Database\\Items.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            

        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}

    }

    protected void LoadDropdown(JComboBox<String> TypeDropdown,List<String> noDuplicateName){

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
                    
                    if(dataArray != null){
                        if(!noDuplicateType.contains(dataArray[4])){
                            TypeDropdown.addItem(dataArray[4]);
                            noDuplicateType.add(dataArray[4]);
                        }
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

}
