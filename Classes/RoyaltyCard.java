package Classes;
public class RoyaltyCard {

    private int RoyaltyCardNumber;
    private int RoyaltyCardPoints;

    protected RoyaltyCard(){}

    public RoyaltyCard(int RoyaltyCardNumber, int RoyaltyCardPoints){

        this.RoyaltyCardNumber=RoyaltyCardNumber;
        this.RoyaltyCardPoints=RoyaltyCardPoints;

    }

    public boolean checkPoints(int DeductionAmount){

        if(RoyaltyCardPoints - DeductionAmount >= 0){return true;}
        return false;
    
    }

    public String getPoints(){return String.valueOf(RoyaltyCardPoints);}

    // though this is pointless, its logical to have card number in card class
    // (We use hashmap so the .get() function act as a getNumber ady)
    public String getNumber(){return String.valueOf(RoyaltyCardNumber);}

    
}
