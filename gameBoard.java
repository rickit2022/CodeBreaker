import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//drawing the board
public class gameBoard implements MouseListener{
    Random rand = new Random();
    Border border = BorderFactory.createLineBorder(Color.GREEN);
    JFrame main = new JFrame("CodeBreaker");

    JPanel main_panel = new JPanel(new GridBagLayout());
    JPanel row_panel = new JPanel(new GridBagLayout());
    JPanel input_panel = new JPanel(new GridBagLayout());

    Icon empty = new ImageIcon("Empty.png");

    Icon colour_list[] = {
    new ImageIcon("Colour_0.png"),
    new ImageIcon("Colour_1.png"),
    new ImageIcon("Colour_2.png"),
    new ImageIcon("Colour_3.png"),
    new ImageIcon("Colour_4.png"),
    new ImageIcon("Colour_5.png"),
    new ImageIcon("Colour_6.png")
    };

    String colour_names[]= {"Red","Orange","Yellow","Green","Blue","Indigo","Violet",};
    
    Icon score_0 = new ImageIcon("Score_0.png");
    Icon score_1 = new ImageIcon("Score_1.png");

    JLabel[][] row_labels = new JLabel[6][4];
    JLabel[][] check_labels = new JLabel[6][4];
    JLabel[] input_labels = new JLabel[7];

    private int column_counter = 0;
    private int row_counter = 5;
    private int[] keys = new int[4];
    private int[] colour_filled = new int[4];
    private int[] found_list = new int[4] ;
    private int found= 0;
    public boolean end;

    public gameBoard() { 
        main_panel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc_main = new GridBagConstraints();

        GridBagConstraints gbc_input = new GridBagConstraints();
        gbc_input.fill = GridBagConstraints.BOTH;

        GridBagConstraints gbc_row = new GridBagConstraints();
        gbc_input.fill = GridBagConstraints.BOTH;
        //creating and adding JLabels to represents the game layout
        for(int i = 0; i < 6;i++ ){
            gbc_row.gridy = i;
            for(int x = 0; x< 4; x++){
                gbc_row.gridx = x;
                row_labels[i][x] = new JLabel(empty);
                row_labels[i][x].setBorder(border);
                row_panel.add(row_labels[i][x],gbc_row);
                
                gbc_row.gridx = x+4;
                check_labels[i][x] = new JLabel(empty);
                check_labels[i][x].setBorder(border);
                row_panel.add(check_labels[i][x],gbc_row);
            }
        }
        //adding input colour keys to input_panel
        for(int i = 0; i< colour_list.length; i++){
            input_labels[i] = new JLabel(colour_list[i]);
            input_labels[i].addMouseListener(this);
            input_labels[i].setBorder(border);
            input_panel.add(input_labels[i],gbc_input);
        }
        //creating answer keys
        for(int i = 0; i< 4; i++){
            keys[i] = rand.nextInt(7);
            System.out.println(keys[i]);
        }
        //adding input panel to the bottom of main panel to finish setting up the game layout
        //input_panel.setBackground(Color.RED);
        gbc_main.gridy = 0; 
        main_panel.add(row_panel,gbc_main);
        gbc_main.gridy = 1; 
        main_panel.add(input_panel,gbc_main);

        //panel.setOpaque(false);
        main.setContentPane(main_panel);
        main.setSize(500,1000);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        
   }
    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i< colour_list.length; i++){
            if(e.getSource()== input_labels[i]){
                fill_row(colour_list[i],row_counter,column_counter);
                colour_filled[column_counter] = i;
            }
        }
        column_counter += 1;
        if(column_counter == 4){
            column_counter = 0;
            for(int i = 0; i< 4;i++){
                for(int x = 0; x<4;x++){
                    int check = check(i,x);
                    if(check == 2){    
                        fill_check(get_score(2), row_counter, column_counter);
                        found_list[x] = keys[x];
                        keys[x]= -1;
                        column_counter +=1; 
                        break;
                    }
                    else if(check == 1){
                        fill_check(get_score(1), row_counter, column_counter);
                        column_counter +=1; 
                        found_list[x] = keys[x];
                        keys[x]= -1;
                        break;

                    }
                    else if(check== 0){

                    }
                }
            }
            if(found==4){
                re_key();
                restart();
            }
            else{
                re_key();
                found = 0;
                row_counter -= 1;
                column_counter = 0;
            }
        }
    }
    public void mouseReleased(MouseEvent e) {

        
    }
    public void mouseEntered(MouseEvent e) {

        
    }
    public void mouseExited(MouseEvent e) {
        
    }
    public void fill_row(Icon icon,int row,int col){
        row_labels[row][col].setIcon(icon);
    }
    public void fill_check(Icon icon,int row,int col){
        check_labels[row][col].setIcon(icon);
    }

    public int check(int i, int x){
        if(colour_filled[i] == keys[x]){
            if(colour_filled[x] == keys[x]){
                found +=1;
                return 2;
            }
            else{
                return 1;
            }
        }
        if(colour_filled[i] == keys[x]){
            if(colour_filled[i] == keys[i]){
                found +=1;
                return 2;
            }
        }
        return 0;
    }

    public void re_key(){
        for(int i =0; i<4; i++){
            if(keys[i] != -1){
                found_list[i] = keys[i];
            }
        }

        for(int i =0; i<4;i++){
            keys[i] = found_list[i];
        }

        for(int i =0; i<4; i++){
            //System.out.println(keys[i]);
        }
    }
    public Icon get_score(int score){
        if(score == 0){
            return empty;
        }
        if(score == 1){
            return score_1;
        }
        if(score == 2){
            return score_0;
        } 
        return empty; 
    }
    public boolean restart(){
        boolean again = false;
        System.out.println("The result of the game are: ");
        for(int i = 0; i<4;i++){
            System.out.println(colour_names[keys[i]]);
        }
        return again;
    }
}