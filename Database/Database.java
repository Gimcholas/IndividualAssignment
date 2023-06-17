package Database;

import java.util.List;

import Classes.Items;
import Classes.Account;

public class Database {

    // returns list of data
    public List<Account> getAccounts(){return new AccountDatabase().LoadAccounts();}
    public List<Items> getListOfItems(){return new ItemsDatabase().LoadItems();}
    public RoyaltyCardScanner getRoyaltyCardScanner(){return new RoyaltyCardScanner();}
    // Functions and Operations on database
    
    // RoyaltyCardScanner
    public void setPoints(String RoyaltyCardNumber, String OldPoints, String DeductionAmount){
        new RoyaltyCardScanner().setPoints( RoyaltyCardNumber,  OldPoints,  DeductionAmount);
    }

    public boolean scanRoyaltyCard(String RoyaltyCardNumber, String DeductionAmount){
        return new RoyaltyCardScanner().scanRoyaltyCard( RoyaltyCardNumber,  DeductionAmount);
    }

    public String getPoints(String RoyaltyCardNumber){return new RoyaltyCardScanner().getPoints(RoyaltyCardNumber);}

    public boolean cardExist(String RoyaltyCardNumber){
        return new RoyaltyCardScanner().cardExist( RoyaltyCardNumber);
    }

    //  Accounts
    List<Account> LoadAccounts(){return new AccountDatabase().LoadAccounts();}

}
