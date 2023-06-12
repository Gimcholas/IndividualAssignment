public class Items {
    
    String Name,Price,Quantity,Type;

    Items(String Name, String Price, String Quantity, String Type){

        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Type = Type;

    }

    public String getName(){return Name;}
    public String getPrice(){return Price;}
    public String getQuantity(){return Quantity;}
    public String getType(){return Type;}

}
