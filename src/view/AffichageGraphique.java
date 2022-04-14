/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static controller.ControllerGraphique.jeu;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Direction;
import model.Jeu;



public class AffichageGraphique extends Application implements Observer {

    private final Image imgPac = new Image("file:src/images/pacman_normal.png");
    private final Image imgFantome = new Image("file:src/images/enemy_normal.png");
    private final Image imgFruit = new Image("file:src/images/fruit.png");
    private final Image imgChamp = new Image("file:src/images/champignon.png");
    private final Image imgGomme = new Image("file:src/images/boule-de-gomme.png");
    private final Image imgZombie = new Image("file:src/images/enemy_zombie_2.png");
    private final Image imgSuperPac = new Image("file:src/images/pacman_invincible.png"); //Pas utilisé pour l'instant
    private final Image imgDeadPac = new Image("file:src/images/pacman_dead.gif"); //Pas utilisé pour l'instant
    private final Canvas canvas = new Canvas();
    private static final int TAILLE = 30;
    private Direction direction;
    private static char[][] tabAffichage;
    private final int HAUTEUR = tabAffichage.length;
    private final int LARGEUR = tabAffichage[0].length;
    private GraphicsContext gc;
    private boolean gameOver;
    private final String messageVictoire = "Felications, vous avez gagné";
    private final String messageDefaite = "PERDU HAHAHA";
    FenetreFinPartie fenetre = new FenetreFinPartie();

    @Override
    public void start(Stage primaryStage) { //Méthode redéfinie à chaque fois qu'on décider d'une action de direction afin réafficher le canevas

        Pane root = new Pane() {
            @Override //Gestion du canevas graphique
            protected void layoutChildren() {
                //GraphicsContext gc = canvas.getGraphicsContext2D();
                canvas.setWidth(getWidth());
                canvas.setHeight(getHeight());
                canvas.setFocusTraversable(true);
                canvas.requestFocus();
                canvas.setOnKeyPressed((KeyEvent event) -> {
                    direction = getDirection((event.getCode()).toString()); //Expression lamda permettant d'activer la méthode jouer de la plateforme graphique (gestion d'évènement) si on enfonce une touche de direction.
                    //la méthode jouer est gérée comme un évènement dont on obtient le code de direction et transformée en string qui sera comparé à la chaîne de caractères des différentes directions. Ensuite on vérifie s'il n'y a pas de conflit avec jeu.CONTINUE.
                    gameOver = jeu.jouer(direction);
                    if (gameOver) { //Vérifiaction de savoir si le conflit n'entraine pas un GAME OVER!
                        if (jeu.pacManMort()) {
                            fenetre.entry(primaryStage, messageDefaite);
                        } else {
                            fenetre.entry(primaryStage, messageVictoire);
                        }
                    }
                });
                afficherJeu();
            }
        };
        jeu.addObserver(this);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, LARGEUR * TAILLE, HAUTEUR * TAILLE + TAILLE);

        primaryStage.setTitle("PacMan - Overkill Bonus Edition ++");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        tabAffichage = jeu.getTableauAffichage();
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        afficherJeu();
    }

    private void afficherJeu() {
        gc = canvas.getGraphicsContext2D();
        Image fantome = imgFantome;
        Image pac = imgPac;
        //Image superPacMan = imgPac;
        if (jeu.isSuperPacMan()) {
            fantome = imgZombie; //Passage de fantome normal en fantome Zombie quand Pacman devient SuperPacMan
            pac = imgSuperPac; //Passage de pacman normal en superpacman
        } else {
            fantome = imgFantome;
            pac = imgPac;
        }
        for (int i = 0; i < tabAffichage.length; i++) {
            for (int j = 0; j < tabAffichage[0].length;j++) {
                switch (tabAffichage[i][j]) { //On rempli le tableau avec tous les éléments et on leur attribue une taille
                    case 'C' : gc.drawImage(pac, j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Pacman
                    case '@' : gc.drawImage(imgFruit, j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Fruit
                    case '$' : gc.drawImage(imgChamp, j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Champigon
                    case 'A' : gc.drawImage(fantome, j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Fantome
                    case ' ' : gc.setFill(Color.WHITE); gc.fillRect(j * TAILLE, i * TAILLE, TAILLE, TAILLE); break;// Chemin de couleur blanche
                    case '*' : gc.drawImage(imgGomme, j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Gommes
                    default : gc.setFill(Color.BLACK);gc.fillRect(j*TAILLE, i*TAILLE, TAILLE, TAILLE); break;// Remplissage de fonds de couleur noire
                }
            }
        }
        
        //Utilisation d'un TreeMap pour afficher l'état/les statistiques du jeu
        TreeMap <String,Integer> map = getStatsJeu();
        String s = "";
        for (String key : map.keySet()) {
            s += key + " : " + map.get(key) + " - ";
        }
        gc.setFill(Color.WHITE);// Couleur de fonds de la zone editable de texte
        gc.fillRect(0, HAUTEUR * TAILLE, LARGEUR * TAILLE, 30);
        gc.strokeText(s, 0, HAUTEUR*TAILLE + TAILLE);
    }
    
    private TreeMap getStatsJeu () {
        return Jeu.getStatsJeu();
    }
        
    private Direction getDirection (String dir) {
        switch (dir) {
            case "UP" : return Direction.HAUT;
            case "DOWN" : return Direction.BAS;
            case "LEFT" : return Direction.GAUCHE;
            case "RIGHT" : return Direction.DROITE;
            case "ESCAPE" : Platform.exit();
            default : return null;
        }
    }
}
