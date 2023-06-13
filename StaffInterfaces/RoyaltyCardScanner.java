package StaffInterfaces;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RoyaltyCardScanner extends RoyaltyCard{

    private HashMap<String,RoyaltyCard> ListOfRoyaltyHashMap = new HashMap<String, RoyaltyCard>();

    RoyaltyCardScanner(){loadCards();}

    // Loads card into HashMap
    private void loadCards(){
        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{
            File myObj = new File("C:\\Users\\End User\\Documents\\MMU Stuff\\java\\IndividualAssignment\\Database\\RoyaltyCard.md");
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

                    // insert into HashMap
                    if(dataArray != null){this.ListOfRoyaltyHashMap.put(dataArray[1],new RoyaltyCard(Integer.valueOf(dataArray[1]),Integer.valueOf(dataArray[2])));}

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

    //https://stackoverflow.com/a/37624091/15149509
    private void setPoints(String RoyaltyCardNumber, String OldPoints, String DeductionAmount){

        try{
            Path FILE_PATH = Paths.get("C:\\Users\\End User\\Documents\\MMU Stuff\\java\\IndividualAssignment\\Database\\RoyaltyCard.md");

            List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));

            String oldEntry = "|" + RoyaltyCardNumber + "|" + OldPoints + "|";
            String newEntry = "|" + RoyaltyCardNumber + "|" + (Integer.valueOf(OldPoints) - Integer.valueOf(DeductionAmount)) + "|";

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

    // Handles everything from existence of card and points checking to deduction of points
    public boolean scanRoyaltyCard(String RoyaltyCardNumber, String DeductionAmount){

        loadCards();

        // if Card exist, then check if there is sufficient points to deduct
        if(ListOfRoyaltyHashMap.containsKey(RoyaltyCardNumber) && ListOfRoyaltyHashMap.get(RoyaltyCardNumber).checkPoints(Integer.valueOf(DeductionAmount))){

            setPoints(RoyaltyCardNumber,ListOfRoyaltyHashMap.get(RoyaltyCardNumber).getPoints(),DeductionAmount);
            return true;

        }

        return false;

    }

    public String getPoints(String RoyaltyCardNumber){return ListOfRoyaltyHashMap.get(RoyaltyCardNumber).getPoints();}

    public boolean cardExist(String RoyaltyCardNumber){

        return ListOfRoyaltyHashMap.containsKey(RoyaltyCardNumber);
    
    }

}
