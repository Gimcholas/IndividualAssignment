package Database;

import java.util.List;

import javax.swing.JComboBox;

import Classes.Items;
import Classes.Account;

public class Database {

    // returns list of data
    public List<Account> getAccounts(){return new AccountDatabase().LoadAccounts();}
    public List<Items> getListOfItems(){return new ItemsDatabase().LoadItems();}
    public RoyaltyCardScanner getRoyaltyCardScanner(){return new RoyaltyCardScanner();}

    // Functions and Operations on database //
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
    public List<Account> LoadAccounts(){return new AccountDatabase().LoadAccounts();}
    public void addStaff(String newEntry){new AccountDatabase().addStaff(newEntry);}
    public void ResetPassword(String Role, String Name, String newPW){new AccountDatabase().ResetPassword(Role, Name, newPW);}

    // Discounts
    public JComboBox<String> loadBundledDiscount(){return new DiscountsDatabase().loadBundledDiscount();}
    public void addNewBundledDiscount(String newEntry){new DiscountsDatabase().addNewBundledDiscount(newEntry);}
    public void LoadDropdown(JComboBox<String> Pair1Dropdown,JComboBox<String> Pair2Dropdown){new ItemsDatabase().LoadDropdown(Pair1Dropdown,Pair2Dropdown);}

    // Items
    public void addItem(String newEntry){new ItemsDatabase().addItem(newEntry);}
    public void LoadDropdown(JComboBox<String> TypeDropdown,List<String> noDuplicateName){ new ItemsDatabase().LoadDropdown(TypeDropdown, noDuplicateName);}
}
