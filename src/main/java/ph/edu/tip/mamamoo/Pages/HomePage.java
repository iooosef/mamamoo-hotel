package ph.edu.tip.mamamoo.Pages;

import javax.swing.*;

public class HomePage extends JPanel {
    private JLabel welcomeLbl;
    public HomePage(){
        welcomeLbl = new JLabel("Welcome to Mamamoo Hotel!");
        add(welcomeLbl);
    }
    public void showHomePage(){
        setVisible(true);
    }
    public void hideHomePage(){
        setVisible(false);
    }
}
