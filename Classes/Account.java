package Classes;
public class Account {

    String Role, Name, Password;

    Account(String Role, String Name, String Password){
        this.Role = Role;
        this.Name = Name;
        this.Password = Password;
    }

    // Getters
    public String getRole() {return Role;}
    public String getName() {return Name;}
    public String getPassword() {return Password;}

}
