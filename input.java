import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.*;

public class input implements ActionListener{
    Icon colour_list[] = {new ImageIcon("Colour_0.png"),new ImageIcon("Colour_1.png"),new ImageIcon("Colour_2.png"),new ImageIcon("Colour_3.png"),new ImageIcon("Colour_4.png"),new ImageIcon("Colour_5.png"),new ImageIcon("Colour_6.png")};
    
    public input(){
        JButton lol = new JButton();
        lol.addActionListener(this);
    }
}
