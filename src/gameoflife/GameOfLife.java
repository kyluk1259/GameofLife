/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Kyle Lukaszek
 * March 6th 2019
 * Game of Life
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     * @return
     */
    
    //public variables for usage across methods
    
    //Grid size selection
    public static String howMany = JOptionPane.showInputDialog("Welcome to the game of life!\nPlease enter how many cells you wish to start the game with.");
    public static int cells = (int) Math.sqrt((double) Integer.parseInt(howMany));
    public static String[][] cell = new String[cells][cells];
    public static String dead = ".";
    public static String alive = "O";
    public static int counter = 0;
    public static Random rand = new Random();
    public static int gen = 0;

    //print cells to console in correct order
    public static void initCells() {

        for (int i = 0; i < cells; i++) {
            for (int j = 0; j < cells; j++) {
                counter++;
                System.out.print(cell[i][j]);
                if (counter == cells) {
                    System.out.println();
                    counter = 0;
                }
            }
        }
    }

    //initialize generation 0
    public static void firstGen() {

        System.out.println("----Generation 0-------------------------------------------------");
        int randomCells = (int) rand.nextInt((int) Math.pow(cells, 2) - 0 + 1); //max - min + 1
        for (int i = 0; i < cells; i++) {
            for (int j = 0; j < cells; j++) {
                cell[i][j] = dead;
            }
        }

        for (int k = 0; k < randomCells; k++) {
            int randomNum1 = rand.nextInt(cells - 0); //max - min + 1
            int randomNum2 = rand.nextInt(cells - 0); //max - min + 1
            cell[randomNum1][randomNum2] = alive;
        }

        initCells();
    }

    //calculate positions of next generation
    public static void calcs() {
        int adjacent = 0;
        
        for (int i = 0; i <= cells - 1; i++) {
            for (int j = 0; j <= cells - 1; j++) {
                adjacent = 0;
                    
                if (cell[i][j].equals(dead)) {
                    if(i > 0){
                    if(cell[i - 1][j].equals(alive)){adjacent += 1;} 
                    }
                    if(i < cells-1){
                    if(cell[i + 1][j].equals(alive)){adjacent += 1;} 
                    }
                    if(j > 0){
                    if(cell[i][j - 1].equals(alive)){adjacent += 1;} 
                    }
                    if(j < cells-1){
                    if(cell[i][j + 1].equals(alive)){adjacent += 1;}
                    }
                        if (adjacent == 3) {
                            cell[i][j] = alive;
                            adjacent = 0;
                        }else{
                            cell[i][j] = dead;
                            adjacent = 0;
                        }
                }
                
                else if (cell[i][j].equals(alive)) {
                   if(i > 0){
                    if(cell[i - 1][j].equals(alive)){adjacent += 1;} 
                    }
                    if(i < cells - 1){
                    if(cell[i + 1][j].equals(alive)){adjacent += 1;} 
                    }
                    if(j > 0){
                    if(cell[i][j - 1].equals(alive)){adjacent += 1;} 
                    }
                    if(j < cells - 1){
                    if(cell[i][j + 1].equals(alive)){adjacent += 1;}
                    }  
                        if (adjacent >= 2 && adjacent < 4) {
                            cell[i][j] = alive;
                            adjacent = 0;
                        }else{
                            cell[i][j] = dead;
                            adjacent = 0;
                    } 
                }
            }
        }
    }
    
    
    //load next generation
    public static void nextGen() {
        gen += 1;
        for (int a = 0; a < 10; a++) {
            System.out.println();
        }
        System.out.println("----Generation " + gen + "-------------------------------------------------");

        //get positions from calcs method and print next generation to console
        calcs();
        initCells();

    }

    public static void main(String[] args) {
        // TODO code application logic here

        //load gen 0
        boolean game = true;
        firstGen();

        try {
            while (game = true) {
                String input = JOptionPane.showInputDialog("1 - Advance one generation\nexit - exit game.");

                //run next generation
                if (input.equals("1")) {
                    nextGen();
                } else if (input.equals("exit")) {
                    game = false;
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
