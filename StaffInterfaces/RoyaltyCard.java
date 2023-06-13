package StaffInterfaces;
public class RoyaltyCard {

    private int RoyaltyCardNumber;
    private int RoyaltyCardPoints;

    RoyaltyCard(){}
    
    RoyaltyCard(int RoyaltyCardNumber, int RoyaltyCardPoints){

        this.RoyaltyCardNumber=RoyaltyCardNumber;
        this.RoyaltyCardPoints=RoyaltyCardPoints;

    }

    protected boolean checkPoints(int DeductionAmount){

        if(RoyaltyCardPoints - DeductionAmount >= 0){return true;}
        return false;
    
    }

    protected String getPoints(){return String.valueOf(RoyaltyCardPoints);}
    
}
