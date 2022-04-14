/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Map;
import java.util.Map.Entry;
import model.Jeu;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

/**
 *
 * @author 2303frmalherbe
 */
public class AffichageConsole implements Observer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    
    private static final char [][] tabAffichage = {
            {'┌', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '┬', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '┐'},
            {'│', '*','*', '*', '*', '*','*', '*', '*','*', '*', '|', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|'},
            {'│', '@', '┌', '─', '┐', '*', '┌', '─', '─', '┐', '*', '|', '*', '┌', '─', '─', '┐', '*', '┌', '─', '┐', '@', '|'},
            {'│', '*',  '└', '─', '┘', '*', '└', '─', '─', '┘', '*', '|', '*', '└', '─', '─', '┘', '*', '└', '─', '┘', '*', '|'},
            {'│', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|'},
            {'│', '*', '─', '─', '─', '*', '|', '*', '┌', '─', '─', '─', '─', '─', '┐', '*', '|', '*', '─', '─', '─', '*', '|'},
            {'│', '*', '*', '*', '*', '*', '|', '*', '└', '─', '─', '┬', '─', '─', '┘', '*', '|', '*', '*', '*', '*', '*', '|'},
            {'└', '─', '─', '─', '┐', '*', '|', '*', '*', '*', '*', '|', '*', '*', '*', '*', '|', '*', '┌', '─', '─', '─', '┘'},
            {' ', ' ', ' ', ' ', '│', '*', '├', '─', '─', '─', '*', '|', '*', '─', '─', '─', '┤', '*', '|', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', '│', '*', '|', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|', '*', '|', ' ', ' ', ' ', ' '},
            {'┌', '─', '─', '─', '┘', '*', '|', '*', '┌', ' ', ' ', ' ', ' ', ' ', '┐', '*', '|', '*', '└', '─', '─', '─', '┐'},
            {'│', '*', '*', '*', '*', '*', '$', '*', '│', ' ', ' ', ' ', ' ', ' ', '│', '*', '$', '*', '*', '*', '*', '*', '|'},
            {'└', '─', '─', '─', '┐', '*', '|', '*', '└', '─', '─', '─', '─', '─', '┘', '*', '|', '*', '┌', '─', '─', '─', '┘'},
            {' ', ' ', ' ', ' ', '|', '*', '|', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|', '*', '|', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', '|', '*', '|', '*', '┌', '─', '─', '─', '─', '─', '┐', '*', '|', '*', '|', ' ', ' ', ' ', ' '},
            {'┌', '─', '─', '─', '┘', '*', '|', '*', '└', '─', '─', '┬', '─', '─', '┘', '*', '|', '*', '└', '─', '─', '─', '┐'},
            {'|', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '|'},
            {'|', '*', '─', '─', '┐', '*', '─', '─', '─', '─', '*', '|', '*', '─','─', '─','─', '*', '┌', '─','─', '*', '|'},
            {'|', '@', '*', '*', '|', '*', '*', '*', '*', '*', '*', ' ', '*', '*', '*', '*', '*', '*', '|', '*', '*', '@', '|'},
            {'├', '─', '─', '*', '|', '*', '|', '*', '─', '─', '─', '┬', '─', '─', '─', '*', '|', '*', '|', '*', '─', '─', '┤'},
            {'|', '*', '*', '*', '*', '*', '|', '*', '*', '*', '*', '|', '*', '*', '*', '*', '|', '*', '*', '*', '*', '*', '|'},
            {'|', '*', '┌', '─', '─', '─', '┴', '─', '─', '┐', '*', '|', '*', '┌', '─', '─', '┴', '─','─', '─', '┐', '*', '|'},
            {'|', '*', '└', '─', '─', '─', '─', '─', '─', '┘', '*', '|', '*', '└', '─', '─', '─', '─', '─', '─', '┘', '*', '|'},
            {'|', '$', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '$', '|'},
            {'└', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '─', '┘'}
        };
    
    public AffichageConsole () {
        
    }
    
    public static char[][] getTableauAffichage () {
        return tabAffichage;
    }
    
    public void afficherVictoire() {
        System.out.println("\n\n--- FELICITATIONS, VOUS AVEZ GAGNE! ---\n\n");
        afficherBandeauStatut();
        afficherLab();
    }

    public void afficherDefaite() {
        System.out.println("\n\n--- PERDU! HAHAHA! ---\n\n");
        afficherBandeauStatut();
        afficherLab();
    }

    @Override
    public void update(Observable o, Object arg) {
        afficherJeu();
    }
    
    private void afficherOptions () {
        System.out.println("");
        System.out.print("| Options du jeu : " + ANSI_WHITE + " Pour déplacer PacMan, tapez : " + ANSI_YELLOW + "z" + ANSI_WHITE + " = vers le haut, " + ANSI_YELLOW + "s" + ANSI_WHITE + " = vers le bas, " + ANSI_YELLOW + "q" + ANSI_WHITE + " = vers la gauche, " + ANSI_YELLOW + "d" + ANSI_WHITE + " = vers la droite || Pour terminer le jeu tapez = " + ANSI_YELLOW + "0" + ANSI_WHITE + " |\n" + ANSI_RESET);
        System.out.println("");
    }
    
//    private void afficherSuperPac () {
//        System.out.print("| Super : " + ANSI_RED + (Jeu.getSuperPacMan() ? "y" : "n") + ANSI_RESET + " |");
//    }
//    
//    private void afficherScore () {
//        System.out.print("| Score : " + ANSI_RED + Jeu.getScore() + ANSI_RESET + " ");
//    }
//    
//    private void afficherVies () {
//        System.out.print("| Vies : " + ANSI_RED + Jeu.getVies() + ANSI_RESET + " ");
//    }
//    
//    private void afficherNbFantomes () {
//        System.out.print("| Fantomes : " + ANSI_RED + Jeu.getNbFantomes() + ANSI_RESET + " ");
//    }
//    
//    private void afficherNbGommes () {
//        System.out.print("| Gommes restantes : " + ANSI_RED + Jeu.getNbGommes() + ANSI_RESET + " ");
//    }
//    
//    private void afficherNbCoups () {
//        System.out.print(" | Nombre de coups : " + Jeu.getNbCoups() + " ");
//    }
//    
    public void afficherMessage () {
      System.out.println("");
      System.out.print(ANSI_RED + " /!\\ Erreur : CETTE OPTION NE FAIT PAS PARTIE DE LA LISTE DES OPTIONS DISPONIBLES /!\\ " + ANSI_RESET);
      System.out.println(""); 
    }
    
    private void afficherBandeauStatut () {
//        afficherVies();
//        afficherScore();
//        afficherNbFantomes();
//        afficherNbGommes();
//        afficherNbCoups();
//        afficherSuperPac();
//        System.out.println("");
        
        TreeMap<String,Integer> map = Jeu.getStatsJeu ();
        for (String key : map.keySet()) {
            System.out.print(key + " : " + map.get(key));
        }
    }
    
    public void afficherJeu () {
        afficherBandeauStatut();
        afficherLab();
    }
    
    private void afficherLab () {
        System.out.println("");
        int i = 0;
        for (char[] cc : tabAffichage) {
            for (char c : cc) {
                if (c == 'C') {
                    System.out.print(ANSI_YELLOW);
                }
                if (c == '@') {
                    System.out.print(ANSI_GREEN);
                }
                if (c == '$') {
                    System.out.print(ANSI_GREEN);
                }
                if (c == 'A') {
                    switch (i) {
                        case 0 : System.out.print(ANSI_RED);i++;break;
                        case 1: System.out.print(ANSI_BLUE);i++;break;
                        case 2 : System.out.print(ANSI_PURPLE);i++;break;
                        case 3 : System.out.print(ANSI_CYAN);i = 0;break;
                    }
                }
                System.out.print(c);
                System.out.print(ANSI_RESET);
            }
            System.out.println("");
        }
    }
    
}
