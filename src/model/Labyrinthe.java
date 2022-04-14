/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

public class Labyrinthe {
    
    private static int nbGommes = 0;
    private final static int[][] initTableau = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 5, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 5, 1},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 0, 0, 0, 0, 0, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 6, 4, 1, 3, 3, 0, 3, 3, 1, 4, 6, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1},
            {1, 5, 4, 4, 1, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4, 4, 4, 4, 1, 4, 4, 5, 1},
            {1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1},
            {1, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    
    public static final int LONGUEUR = initTableau[0].length;
    public static final int HAUTEUR = initTableau.length;
    
    private static Case[][] labyrinthe = null;
    private static Labyrinthe instance = null;

    public static Position getCaseDepartPacMan() { //position pac man = new pos x / y 
        Position p = new Position(18, 11);
        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                if (initTableau[i][j] == 2) {
                    p =  new Position(i, j);
                }
            }
        }
        return p;
    }

    private Labyrinthe () {
        
    }
    
    public static Labyrinthe getInstance () {
        if (instance == null) {
            instance = new Labyrinthe();
            instance.initLab();
        }
        return instance;
    }
    
    public int getNbGommes() {
        return nbGommes;
    }
    
    public void retirerAliment (Case c) {
        if (c instanceof Chemin) {
            Chemin ch = (Chemin)c;
            //TypeAliments type = ch.getTypeAliment();
            if (!noMoreGommes() && (ch.getAliment()) instanceof Gomme) {
                nbGommes--;
            }
            ch.mangerAliment();
        }
    }
    
    private void initLab () {
        labyrinthe = new Case[HAUTEUR][LONGUEUR];
        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                switch (initTableau[i][j]) {
                    case 0 ://Pas utilisé
                    case 3 :
                    case 2 : labyrinthe[i][j] = new Chemin(); break; //place de pacman
                    case 1 : labyrinthe[i][j] = new Mur();break; //mur
                    case 4 : labyrinthe[i][j] = new Chemin(new Gomme()); nbGommes++; break;//place des gommes
                    case 5 : labyrinthe[i][j] = new Chemin(new Fruit()); break;//place des fruits
                    case 6 : labyrinthe[i][j] = new Chemin(new Champignon()); break;//place des champigons
                }
            }
        }
    }
    
    public Case canGoThere (Direction d, Position p) {
        Case c = null;
        int i, j;
        i = p.getI();
        j = p.getJ();
        Position p2 = new Position(i, j);
        p2.move(d);
        i = p2.getI();
        j = p2.getJ();
        if (validIndexes(i, j)) {
            c = labyrinthe[i][j];
            if (c.estMur())
                return null;
        }
        return c;
    }
    
    private boolean validIndexes (int i, int j) {
        return (j >= 0 && j < LONGUEUR) && (i >= 0 && i < HAUTEUR);
    }
    
    public boolean noMoreGommes () {
        return nbGommes == 0;
    }
    
    public Position getNewRandomPosition (Position pacmanPos) {
        int min = 1;
        int max = labyrinthe[0].length - 1;
        int i, j;
        Random rand = new Random();
        i = rand.nextInt((max + 1) - min) + min;
        j = rand.nextInt((max + 1) - min) + min;
        Position pos;
        pos = new Position(i,j);
        while (labyrinthe[i][j].estMur() || pacmanPos.equals(pos)) {
            i = rand.nextInt((max + 1) - min) + min;
            j = rand.nextInt((max + 1) - min) + min;
            pos = new Position(i,j);
        }
        return pos;
    }

    public char getCase(int previousI, int previousJ) {
        if (labyrinthe[previousI][previousJ] instanceof Chemin) {
            if (((Chemin)labyrinthe[previousI][previousJ]).getAliment() instanceof Fruit) {
                return '@';
            } else if (((Chemin)labyrinthe[previousI][previousJ]).getAliment() instanceof Gomme) {
                return '*';
            } else if (((Chemin)labyrinthe[previousI][previousJ]).getAliment() instanceof Champignon) {
                return '$';
            }
        }
        return ' ';
    }

    public Position getCaseDepart(int id) {
        int x = 0;
        int y = 0;
        int n = 0;
        
        for (int i = 0; i < initTableau.length; i++) {
            for (int j = 0; j < initTableau[0].length; j++) {
                if (initTableau[i][j] == 3) {
                    if (n == id) {
                        x = i;
                        y = j;
                        n++;
                    } else if (n < id) {
                        n++;
                    }
                }
            }
        }
        return new Position(x, y);
    }

}
