import javax.swing.*;

import MainInterface.Login;

public class MiniGrocery extends JFrame{
 
    Login login;

    public MiniGrocery(){

        new Login().MainMenu();

    }

    public static void main(String[] args){
        new MiniGrocery();
    }

}