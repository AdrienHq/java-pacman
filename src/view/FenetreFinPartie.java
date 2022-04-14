/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static controller.Main.choix;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author 2303frmalherbe
 */
public class FenetreFinPartie extends Application {

    private final Canvas canvas = new Canvas();
    private final int TAILLE = 20;
    private String messageAffichage;
    private final Image imgPac = new Image ("file:images/pacman.png");

    @Override 
    public void start(Stage primaryStage) { //Méthode redéfinie à chaque fois qu'on décide d'une action de direction afin réafficher le canevas

        Pane root = new Pane() {
            @Override //Gestion du canevas graphique
            protected void layoutChildren() {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                canvas.setWidth(getWidth());
                canvas.setHeight(getHeight());
                canvas.setFocusTraversable(true); //Permet de passer d'une case à une autre. Exigence de l'itération2.
                canvas.requestFocus();
                canvas.setOnKeyPressed((KeyEvent event) -> {
                    if (((event.getCode()).toString()).equals("ESCAPE")) {
                        Platform.exit(); //Expression lamda permettant d'activer la méthode exit de la plateforme graphique (gestion d'évènement) si on enfonce la touche escape.
                        //la méthode exit est gérée comme un évènement dont on obtient le code et transformée en string qui sera comparé à la chaîne de caractères "ESCAPE" pour générer l'action de quitter.
                    }
                });//Affichage d'un écran de fin de jeu avec option de quitter
                gc.setFill(Color.WHITE);
                gc.strokeText(messageAffichage, 50, 50);
                gc.strokeText("ESC : quitter", 0, 100);
                gc.setFill(Color.BLACK);
            }
        };

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 200, 120);

        //primaryStage.setTitle("Exemple d'affichage d'une Image sur un Canvas"); : pas utile
        primaryStage.setScene(scene);
        //primaryStage.show();
        choix = 1; //Pour indiquer qu'on est en mode graphique : rapport avec la fonction main Mais n'est pas utilisé
    }
    
    public void entry(Stage stage, String message) {
        messageAffichage = message;
        start(stage);
    }
    
}
