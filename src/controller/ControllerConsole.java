package controller;

import model.Direction;
import model.Jeu;
import view.AffichageConsole;

public class ControllerConsole {

    public static void main(String[] args) {
        Jeu jeu = Jeu.getInstance();
        boolean gameOver = false;
        //char[][] tabAffichage = AffichageConsole.getTableauAffichage();
        Direction direction;
        boolean peutEncoreJouer;//Variable non utilisée pour l'instant
        AffichageConsole affichage = new AffichageConsole();
        affichage.afficherJeu();
        jeu.addObserver(affichage);
        do {
            direction = lireToucheClavier();
            if (direction != null) {
                gameOver = jeu.jouer(direction);
                if (gameOver) {
                    jeu.notifyObservers();
                }
            }
        } while (!gameOver);
        if (jeu.pacManMort()) {
            affichage.afficherDefaite();
        } else {
            affichage.afficherVictoire();
        }
    }

    public static Direction lireToucheClavier() {
        char c = '1';
        try {
            c = (char) System.in.read();
        } catch (Exception e) {
            System.out.println("probleme lors de la lecture");
        }
        return getDirection(c);
    }

    private static Direction getDirection(char c) {
        switch (c) {
            case 'z':
                return Direction.HAUT;
            case 's':
                return Direction.BAS;
            case 'q':
                return Direction.GAUCHE;
            case 'd':
                return Direction.DROITE;
            case '0':
                System.exit(1);
            default:
                return null;
        }
    }
}
