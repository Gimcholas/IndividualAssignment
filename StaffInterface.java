import javax.swing.JFrame;
import javax.swing.JLabel;

public class StaffInterface{

    JFrame frame;
    JLabel Login;

    StaffInterface(String Name){
        frame = new JFrame();
        frame.setTitle(Name);
        frame.setSize(1000,800);
        
        Login = new JLabel("Welcome " + Name);
        Login.setBounds(100, 100, 500, 50);
        frame.add(Login);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
