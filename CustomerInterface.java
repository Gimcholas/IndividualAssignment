import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;


public class CustomerInterface extends JFrame implements ActionListener{
    CustomerInterface(){
        super("MiniGrocery");
        setSize(1000,800);
        
        JButton Login = new JButton("HELLO");
        this.add(Login);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){}

}
