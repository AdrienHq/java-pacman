package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Jeu;
import view.AffichageGraphique;

public class ControllerGraphique {

    public static final Jeu jeu = Jeu.getInstance();
    private static KeyFrame k = new KeyFrame(new Duration(5000), ae -> actionSuper());
    private static Timeline t = new Timeline(k);

    public static void main(String[] args) {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> actionPeriodique())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        AffichageGraphique.main(args);
    }

    private static void actionPeriodique() {
        jeu.placerFantomes();
        jeu.bougerFantomes();
    }

    public static void retirerSuper() {
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    private static void actionSuper() {
        jeu.enleverSuper();
        t.stop();
    }
}
