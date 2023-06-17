package Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import Classes.Items;

public class BillDatabase {

    public void addToBillHistory(List<Items> ListOfItems,String DiscountAmount,String BeforeDiscount, String AfterDiscount){

        // Create new entry container
        String newEntry = "\n|";

        for (Items items : ListOfItems) {

            if(items.getCount() > 0){

                newEntry += items.getName() + ":";
                newEntry += String.valueOf(items.getCount()) + "-";

            }
            
        }
        newEntry += "__" + DiscountAmount + ":" + BeforeDiscount + ":" + AfterDiscount + "|";
                    
            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\BillHistory.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            
        
        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}            
    }

}
