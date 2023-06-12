public class Items {
    
    String Name,Type;
    int Quantity;
    Double Price;

    // tracks items added into cart
    int Count = 0;

    Items(String Name, Double Price, int Quantity, String Type){

        this.Name = Name;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Type = Type;

    }

    public String getName(){return Name;}
    public double getPrice(){return Price;}
    public int getQuantity(){return Quantity;}
    public String getType(){return Type;}
    public int getCount(){return Count;}
    
    public void setCount(int Count){this.Count = Count;}

}
