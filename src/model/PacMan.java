/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.Jeu.POINTS_MANGER_FANTOME;
import static model.Jeu.POINTS_MANGER_GOMME;

public class PacMan extends Personnage {
    
    //private int score = 0;
    private boolean superPacMan = false;
    private int pacManScore = 0;
    private int vies = 2;
    private final int CASE_DEPART_I = 18;
    private final int CASE_DEPART_J = 11;
    
    public PacMan (Position p) {
        this.pos = new Position(p.getI(), p.getJ());
    }
    
    public int getNbVies () {
        return this.vies;
    }
    
    public boolean isSuperPacMan () {
        return this.superPacMan;
    }
    
    public int getScore () {
        return this.pacManScore;
    }
    
    public void mange (Case c) {
        if (c instanceof Chemin) {
            Aliment a = ((Chemin)c).getAliment();
            if (a != null) {
                incrementerScore(a.getPoints());
                if (a instanceof Fruit) {
                    this.superPacMan = true;
                }
            }
        }
    }
    
    public boolean mangeFantome(int fantomeID) {
        if (this.superPacMan) {
            incrementerScore(POINTS_MANGER_FANTOME);
            return true;
        } else {
            this.vies--;
            this.pos = new Position(CASE_DEPART_I, CASE_DEPART_J);
            return false;
        }
    }
    
    public void incrementerScore (int points) {
        this.pacManScore += points;
    }

    public Position getPositionCourante() {
        return new Position(this.pos.getI(), this.pos.getJ());
    }

    void removeSuper() {
        this.superPacMan = false;
    }
}
