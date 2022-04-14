package controller;

import java.util.Scanner;

public class Main {
      
    public static int choix = 0;
    // Variable choix initialisée à 0 (pour version console par défaut) et changée à 1 dans FenetreFinPartie.java pour la version graphique => penser à passer aux lettres c, g, q!
       
    public static void main (String[] args) {
        //modif branche master
        // Sélection du mode d'affichage/de jeu
        char c = ' '; 
        
        System.out.println();
        System.out.println("Pour démarrer le jeu, veuillez choisir une interface");
        System.out.println("c : Console");
        System.out.println("g : Graphique");
        System.out.println("q : Quitter");

        try {
            Scanner clavier = new Scanner(System.in);
            if (clavier.hasNext()) {
                c = clavier.nextLine().charAt(0);
                //charAt(0) pour trouver et lire le char à la position 0 (1ère position)
            }
        } catch (Exception e) {
            System.out.println("Fatal Error X_X");
        }
                
        while (c != 'c' && c != 'g' && c != 'q'){
            
                System.out.println ();
                System.out.println ("/!\\ Ceci n'est pas une option valide! /!\\");
                System.out.println (" Veuillez rentrer une option valide: C, G ou Q!");
                System.out.println ();
            
            try {
                Scanner clavier = new Scanner(System.in);
                if (clavier.hasNext()) {
                    c = clavier.nextLine().charAt(0);
                }
            } catch (Exception e) {
                System.out.println("Fatal Error X_X");
            }
            
        
        }
        //Choix de sélection  
        switch (c) {
              case 'c' : ControllerConsole.main(args); break;
              case 'g' : ControllerGraphique.main(args); break;
              case 'q' : System.exit(0); break;
        } 
    }
}