import javax.swing.JFrame;

public abstract class Interface {

    protected JFrame frame;
    protected static String Name;
    protected static String Role;
    public abstract void MainMenu();

    protected void setName(String Name){this.Name = Name;}
    protected void setRole(String Role){this.Role = Role;}

}
