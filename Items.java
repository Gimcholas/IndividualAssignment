public class Items {
    
    String Name,Price,Type;

    Items(String Name, String Price, String Type){

        this.Name = Name;
        this.Price = Price;
        this.Type = Type;

    }

    public String getName(){return Name;}
    public String getPrice(){return Price;}
    public String getType(){return Type;}

}
