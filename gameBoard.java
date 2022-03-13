import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.*;


public class gameBoard{
    JFrame main = new JFrame("CodeBreaker");

    JPanel main_panel = new JPanel();

    JPanel back_panel = new JPanel();

    JPanel empty_panel = new JPanel();

    JPanel check_panel = new JPanel(); 

    Picture p = new Picture("Empty.png");
    JLabel l = new JLabel(p);

    Icon empty = new ImageIcon("Empty.png");

    JButton b = new JButton(empty);

    public gameBoard(){
        main_panel.setLayout(new BoxLayout(main_panel,));
        back_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        empty_panel.setLayout(new GridLayout(1,4));
        check_panel.setLayout(new GridLayout(2,2));
        


        //panel.setOpaque(false);
        main.setContentPane(panel);
        main.setSize(600,600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);

        main.getContentPane().setBackground(Color.DARK_GRAY);
        
   }
}