package Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.Account;
import MainInterface.Login;

public class AccountDatabase {

    List<Account> accounts = new ArrayList<Account>(); 


    // load accounts from database
    protected List<Account> LoadAccounts(){
        
        // Read from file
        //https://www.w3schools.com/java/java_files_read.asp
        try{


            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\Accounts.md";

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

                    // insert into account list
                    if(dataArray != null){this.accounts.add(new Account(dataArray[1],dataArray[2],dataArray[3]));}

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

        return accounts;

    }

    protected void addStaff(String newEntry){
        // Add Staff
        String s = System.getProperty("user.dir");
        Path currentRelativePath = Paths.get(s);
        s = currentRelativePath.toString()+"\\Database\\Accounts.md";

        try {Files.write(Paths.get(s), newEntry.getBytes(), StandardOpenOption.APPEND);}            
        // Error
        catch (IOException e) {System.out.println("Error, Check file path");}
    } 

    //https://stackoverflow.com/a/37624091/15149509
    protected void ResetPassword(String Role, String Name, String newPW){

        try{
            String s = System.getProperty("user.dir");
            Path currentRelativePath = Paths.get(s);
            s = currentRelativePath.toString()+"\\Database\\Accounts.md";

            Path FILE_PATH = Paths.get(s);

            List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));

            String oldEntry = "|" + Role + "|" + Name + "| |";
            String newEntry = "|" + Role + "|" + Name + "|" + newPW + "|";

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldEntry)) {
                    fileContent.set(i, newEntry);
                    break;
                }
            }

            Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
            new Login().MainMenu();

        }
        
        catch (IOException e){System.out.println("Error, check file path");}

    }

}
