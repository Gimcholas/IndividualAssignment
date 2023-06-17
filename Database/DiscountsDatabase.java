package Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import javax.swing.JComboBox;

public class DiscountsDatabase {

    JComboBox<String> BundleDropdown;
    // add bundles to dropdown
    protected JComboBox<String> loadBundledDiscount(){

        BundleDropdown = new JComboBox<>();

        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\Discounts.md";

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

                    // insert into Dropdown
                    if(dataArray != null){BundleDropdown.addItem(dataArray[1] + " and " + dataArray[2] + " for " + dataArray[3] + "%");}

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

        return BundleDropdown;

    }

    protected void addNewBundledDiscount(String newEntry){

        String s = System.getProperty("user.dir");
        Path currentRelativePath = Paths.get(s);
        s = currentRelativePath.toString()+"\\Database\\Discounts.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            

        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}

    }
}
