import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/**
 * A CodeBreaker game.
 * The user may select one the 3 level of difficulty: Easy, Medium, or Hard. Then, the game board would show up and the user would be able to select their input from 1 of the 7 colours provided. 
 * If the user managed to win before their guesses runs out, the game announce that the user won along with the result in the terminal. 
 * If the user ran out of guesses and still have not won, the game will anounce that the user lost and output the result of the code.
 */
//drawing the board.
public class gameBoard implements MouseListener{
    private boolean set= false;// is a flag to check if the difficulty has been set.
    private int col= 10;// is used to represents the colour code's length, intialised as 10 then later changed when the difficulty is selected. 
    private int column_counter = 0;// is a counter for the number of columns in the game board.
    private int row_counter = 5; // is a counter for the number of rows in the game board.
    private int[] keys = new int[col];
    private int[] colour_filled = new int[col];
    private int[] found_list = new int[col] ;
    private int found= 0;// is a counter for the number of times a key has been found.
    private boolean end_of_board;// is a flag to see if the user has used of all their guesses.
    private boolean won = false;// is a flag to see if the user has won.
    private boolean again = true;// is a flag to see if the user wants to play again.

    Random rand = new Random(); //new instace for generation of a random answer key.
    Border border = BorderFactory.createLineBorder(Color.WHITE); // new instance for setting a border colour for jpanels.
    JFrame main = new JFrame("CodeBreaker");// creates a frame for the game board.
    JFrame difficulty_select = new JFrame("Difficulty selection");// creates a frame for the difficulty selection.

    JPanel main_panel = new JPanel(new GridBagLayout()); 
    JPanel row_panel = new JPanel(new GridBagLayout());
    JPanel input_panel = new JPanel(new GridBagLayout());
    JPanel difficulty_panel = new JPanel(new GridLayout(0,3));
    /*
    importing the images provided using the built in Icon class.
    */
    Icon empty = new ImageIcon("Empty.png");
    Icon score_0 = new ImageIcon("Score_0.png");
    Icon score_1 = new ImageIcon("Score_1.png");
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

    JLabel[][] row_labels = new JLabel[6][col];
    JLabel[][] check_labels = new JLabel[6][col];
    JLabel[] input_labels = new JLabel[7];
    JButton[] difficult_buttons = {new JButton("Easy"),new JButton("Medium"), new JButton("Hard")};
    int[] levels = new int[3];
    /*
    Since we're a GridBagLayout, we can set the apply different constraints on the layout used in each JPanels. 
    */
    GridBagConstraints gbc_main = new GridBagConstraints();
    GridBagConstraints gbc_input = new GridBagConstraints();
    GridBagConstraints gbc_row = new GridBagConstraints();
    /**
     * Creates the game board with a difficulty selected.  
     */
    public gameBoard(){ 
        difficulty();
    }
    /**
     * Creates a window with 3 buttons, each specifies a level of difficulty.
     */
    public void difficulty(){
        difficulty_select.setContentPane(difficulty_panel);
        difficulty_select.setSize(300,100);
        for(int i= 0; i<3;i++){
            difficult_buttons[i].addMouseListener(this);
            difficulty_panel.add(difficult_buttons[i]);
        }
        difficulty_select.setLocationRelativeTo(null);;
        difficulty_select.setVisible(true);
    }
    /**
     * Set up the background GUI for the game board.
     */
    public void set_up(){
        for(int i = 0; i < 6;i++){
            gbc_row.gridy = i;
            for(int x = 0; x< col; x++){
                gbc_row.gridx = x;
                row_labels[i][x] = new JLabel(empty);
                row_labels[i][x].setBorder(border);
                row_panel.add(row_labels[i][x],gbc_row);

                gbc_row.gridx = x+ col;
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
        //creating answer keys for the current game
        for(int i = 0; i< col; i++){
            keys[i] = rand.nextInt(7);
            //System.out.println(keys[i]);
            //uncomment the statement above for debugging purposes
        }
        //adding input panel to the bottom of main panel to finish setting up the game layout
        gbc_main.gridy = 0; 
        main_panel.add(row_panel,gbc_main);
        gbc_main.gridy = 1; 
        main_panel.add(input_panel,gbc_main);
        main.setContentPane(main_panel);
        final Dimension frame_dimension = main_panel.getPreferredSize();
        Double width = frame_dimension.getWidth();
        Double height = frame_dimension.getHeight();
        main.setSize(width.intValue()+100,height.intValue()+100);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main_panel.setBackground(Color.DARK_GRAY);
        main.setVisible(true);
    }       
    /**
     * Set the level of difficulty based on the button pressed.
     * @param level identifies which level of difficulty has been chosen.
     */
    public void set(int level){
        if(level == 0){
            col = 4;
        }
        else if(level == 1){
            col = 6;
        }
        else if(level ==2){
            col = 8;
        }
    }
    /**
     * Decides what happens when it detects a mouse press.
     */
    public void mousePressed(MouseEvent e) {
        if(set == false){ 
            for(int i =0; i< difficult_buttons.length; i++){
                if(e.getSource() == difficult_buttons[i]){
                    set(i); 
                    difficulty_select.setVisible(false);
                    set = true;
                    set_up(); // returns to setting up the GUI of the game board.
                    break;
                }
            }
        }
        if(set == true){ //if the level of difficulty has been set, the mouse pressed now detects the input.
            for(int i = 0; i< colour_list.length; i++){
                if(e.getSource()== input_labels[i]){
                    fill_row(colour_list[i],row_counter,column_counter);
                    colour_filled[column_counter] = i;
                    column_counter += 1;
                    break;
                }
            }
            if(column_counter == col){ //if the whole row has been filled.
                if(row_counter == 0){ //if the whole game board has been filled.
                    end_of_board = true;
                }
                column_counter = 0;// resets the column counter to allow filling in the clues
                for(int i = 0; i< col;i++){
                    for(int x = 0; x<col;x++){
                        int check = check(i,x);
                        if(check == 2){    
                            fill_check(get_score(2), row_counter, column_counter);
                            column_counter +=1;
                            found_list[x] = keys[x];// put the found key in a temporary list.
                            keys[x]= -1; // flag which of the key has been found. 
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
                            //do nothing
                        }
                    }
                }
                if(found == col){// if the number of keys found is equal to number of colour code.
                    won = true;
                    end_of_board = true;
                    re_key();
                    restart();
                }
                else if(end_of_board == true){// if the user runs out of guesses.
                    won = false;
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
    }
    /**
     * fills in the guesses row with suitable amount of ticks.
     * @param icon is the number of ticks to be filled in.
     * @param row is the position of the row.
     * @param col is the position of the column.
     */
    public void fill_row(Icon icon,int row,int col){
        row_labels[row][col].setIcon(icon);
    }
    /**
     * fills in the clues row with suitable amount of ticks.
     * @param icon is the number of ticks to be filled in.
     * @param row is the position of the row.
     * @param col is the position of the column.
     */
    public void fill_check(Icon icon,int row,int col){
        check_labels[row][col].setIcon(icon);
    }
    /**
     * checks whether any of the user inputs matches the keys.
     * @param i position of the outer loop.
     * @param x position of the inner loop.
     * @return 2 if the position, colour of the input is correct; 1 if only the colour is correct; 0 if nothing is correct.
     */
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
    /**
     * restore the key sequence after the checking.
     */
    public void re_key(){
        for(int i =0; i<col; i++){
            if(keys[i] != -1){
                found_list[i] = keys[i];
            }
        }
        for(int i =0; i<col;i++){
            keys[i] = found_list[i];
        }
    }
    /**
     * retrieves the image of the suitable amount of ticks
     * @param score is the user score for an input.
     * @return the image of the ticks as an Icon class.
     */
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
    /**
     * check whether the user has won or lost.
     * @return 
     */
    public boolean restart(){
        if(won == true){
            System.out.println("You have won!");
        }
        else{
            System.out.println("You lost :(");
        }
        System.out.println("The result of the game are: ");
        for(int i = 0; i<col;i++){
            System.out.println(colour_names[keys[i]]);
        }
        return again;
    }
    /**
     * required method when implementing MouseListener event.
     */
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * required method when implementing MouseListener event.
     */
    public void mouseReleased(MouseEvent e) {
    }
    /**
     * required method when implementing MouseListener event.
     */
    public void mouseEntered(MouseEvent e) {
    }
    /**
     * required method when implementing MouseListener event.
     */
    public void mouseExited(MouseEvent e) {
    }
}