import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.*;

//drawing the board
public class gameBoard{
    JFrame main = new JFrame("CodeBreaker");

    JPanel main_panel = new JPanel();
    JPanel empty_panel[] = new JPanel[6];
    JPanel check_panel[] = new JPanel[6];
    JPanel input_panel = new JPanel();

    JButton input_buttons[] = new JButton[7];
    
    Icon empty = new ImageIcon("Empty.png");

    Icon colour_list[] = {new ImageIcon("Colour_0.png"),new ImageIcon("Colour_1.png"),new ImageIcon("Colour_2.png"),new ImageIcon("Colour_3.png"),new ImageIcon("Colour_4.png"),new ImageIcon("Colour_5.png"),new ImageIcon("Colour_6.png")};

    Icon score_0 = new ImageIcon("Score_0.png");
    Icon score_1 = new ImageIcon("Score_1.png");

    //JButton b = new JButton();

    public gameBoard(){
        main_panel.setLayout(new GridBagLayout());
        main_panel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc_main = new GridBagConstraints();
        gbc_main.anchor = GridBagConstraints.NORTH;
        //gbc_main.fill = GridBagConstraints.VERTICAL;
        
        GridBagConstraints gbc_empty = new GridBagConstraints();
        gbc_empty.weightx = 1;
        gbc_empty.weighty = 1;

        GridBagConstraints gbc_check = new GridBagConstraints();
        gbc_check.fill = GridBagConstraints.VERTICAL;

        for(int i = 0; i < 6;i++ ){
            gbc_empty.gridy = i;

            empty_panel[i] = new JPanel();
            empty_panel[i].setLayout(new GridBagLayout());
            check_panel[i] = new JPanel();
            check_panel[i].setLayout(new GridLayout(2,2));
            check_panel[i].setPreferredSize(new Dimension(110,110));
            

            for(int x = 0; x< 4; x++){
                gbc_empty.gridx = x;

                empty_panel[i].add(new JLabel(empty),gbc_empty);
                check_panel[i].add(new JLabel(empty));

            }
            gbc_main.gridy = i;
            
            main_panel.add(empty_panel[i],gbc_main);
            main_panel.add(check_panel[i],gbc_main);
        }

        for(int i =0; i< colour_list.length ;i++){
            input_panel.add(new JButton(colour_list[i])); 
        }

        main_panel.add(input_panel);

        //panel.setOpaque(false);
        main.setContentPane(main_panel);
        main.setSize(500,900);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        
   }
}