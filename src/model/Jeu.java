package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.TreeMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.AffichageConsole;

public class Jeu extends Observable {
    
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

    private static int coups = 0;
    private static int fantomeID = -1;
    public static final int POINTS_MANGER_GOMME = 1;
    public static final int POINTS_MANGER_FANTOME = 20;
    private static boolean gameOver = false;
    
    
    public final static Labyrinthe labyrinthe = Labyrinthe.getInstance();
    private static final ArrayList<Fantome> listeFantomes = new ArrayList<>();
    
    private static PacMan pacman;
    private static Position previousPos;
    private static Case currentCase;
   // private EtatDuJeu etatDuJeu;
    
    private static Jeu instance;
    private static int nbFantomesVivants = 4;
    
    private Jeu () { //Définition de la méthode Jeu()
        initialiserPersos();
        placerPersos();
    }
    
    public static Jeu getInstance () {
        if (instance == null) {
            instance = new Jeu();
        }
        return instance;
    }
    
    public char[][] getTableauAffichage () {
        return this.tabAffichage;
    }
    
    public boolean jouer (Direction d) {
        if (d != null) {
            if (bougerPacman(d)) {
                coups++;
                mainLogic();
            }
            placerPersos();
            if (labyrinthe.noMoreGommes() || tousLesFantomesMorts() || pacManMort()) {
                gameOver = true;
            }
            setChanged();
            notifyObservers();
        }
        return gameOver;
    }
    
    public EtatDuJeu getEtatDuJeu () {
        if (pacManMort()) {
            return EtatDuJeu.MORT;
        }
        if (tousLesFantomesMorts()) {
            return EtatDuJeu.FANTOMES_MORTS;
        }
        return EtatDuJeu.CONTINUE;
    }
    
    public void bougerFantomes () {
        setChanged();
        notifyObservers();
        Case c;
        for (Fantome f : listeFantomes) {
            Direction d = f.getDirection();
            c = labyrinthe.canGoThere(d, f.getPosition());
            if (c != null && !f.estMort()) {
                f.deplace();
                mainLogic();
            } else {
                f.choisirDirection();
            }
        }
    }
    
    private boolean tousLesFantomesMorts () {
        //return listeFantomes.isEmpty();
        return nbFantomesVivants == 0;
    }
    
    private boolean bougerPacman (Direction d) {
        previousPos = pacman.getPositionCourante();
        currentCase = labyrinthe.canGoThere(d, pacman.getPosition());
        if (currentCase != null) {
            pacman.deplace(d);
            return true;
        }
        return false;
    }
    
    private void pacmanMangeAliment (Case c) {
        pacman.mange(c);
        if (isSuperPacMan()) {
            controller.ControllerGraphique.retirerSuper();
        }
        labyrinthe.retirerAliment(c);
    }
    
    private boolean checkCollisions () {
        for (Fantome f : listeFantomes) {
            if (f.getPosition().equals(pacman.getPosition())) {
                fantomeID = f.getID();
                return true;
            }
        }
        return false;
    }
    
    public boolean pacManMort () {
        return getVies() == 0;
    }
    
    public static TreeMap getStatsJeu () {
        TreeMap<String,Integer> map = new TreeMap<>();
        map.put("Gommes",labyrinthe.getNbGommes());
        //map.put("Fantomes", listeFantomes.size());
        map.put("Fantomes", nbFantomesVivants);
        map.put("Vies PacMan", pacman.getNbVies());
        map.put("Points", pacman.getScore());
        return map;
    }
    
//    public int getScore () {
//        return pacman.getScore();
//    }
//    
    private static int getVies () {
        return pacman.getNbVies();
    }
    
//    public int getNbFantomes () {
//        return LISTE_FANTOMES.size();
//    }
//    
//    public int getNbGommes () {
//        return labyrinthe.getNbGommes();
//    }
//    
//    public int getNbCoups () {
//        return coups;
//    }
//    
    public boolean isSuperPacMan () {
        return pacman.isSuperPacMan();
    }
    
    private void initialiserPersos () {
        Position p;
        p = Labyrinthe.getCaseDepartPacMan();
        pacman = new PacMan(p);
        previousPos = pacman.getPosition();
        /*listeFantomes.add(new Fantome(getNewRandomPosition()));
        listeFantomes.add(new Fantome(getNewRandomPosition()));
        listeFantomes.add(new Fantome(getNewRandomPosition()));
        listeFantomes.add(new Fantome(getNewRandomPosition()));
                */
        listeFantomes.add(new Fantome(labyrinthe.getCaseDepart(0)));
        listeFantomes.add(new Fantome(labyrinthe.getCaseDepart(1)));
        listeFantomes.add(new Fantome(labyrinthe.getCaseDepart(2)));
        listeFantomes.add(new Fantome(labyrinthe.getCaseDepart(3)));
        Fantome f = new Fantome(p);
        Direction d = f.choisirDirection();
    }
    
    private void tuerFantome (int id) {
        for (Fantome f : listeFantomes) {
            if (f.getID() == id) {
                f.positionDepart(labyrinthe.getCaseDepart(id));
                f.setMort();
                nbFantomesVivants--;
                //listeFantomes.remove(f);
                break;
            }
        }
    }
    
    private Position getNewRandomPosition () {
        return labyrinthe.getNewRandomPosition(pacman.getPosition());
    }
    
    public void placerPersos () {
        placerFantomes();
        placerPacMan();
    }
    
    public void placerFantomes () {
        for (Fantome f : listeFantomes) {
            tabAffichage[f.getPreviousI()][f.getPreviousJ()] = labyrinthe.getCase(f.getPreviousI(), f.getPreviousJ());
            tabAffichage[f.getI()][f.getJ()] = 'A';
        }
    }
    
    private void placerPacMan () {
        tabAffichage[previousPos.getI()][previousPos.getJ()] = ' ';
        tabAffichage[pacman.getI()][pacman.getJ()] = 'C';
    }

    public void enleverSuper() {
        pacman.removeSuper();
    }

    private void mainLogic() {
        if (checkCollisions()) {
            if (pacman.mangeFantome(fantomeID)) {
                tuerFantome(fantomeID);
                pacmanMangeAliment(currentCase);
            }
        } else {   
            pacmanMangeAliment(currentCase);
        }
    }

}
