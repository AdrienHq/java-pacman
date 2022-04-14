/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author 2303frmalherbe
 */
public class Fantome extends Personnage {
    
    private static int ID = 0;
    private final int IDfantome;
    private Direction direction;
    
    private static final Random rand = new Random();
    private Position previous = new Position(0, 0);
    private boolean mort = false;
    
    public Fantome (Position pos) {
        this.pos = pos;
        this.IDfantome = ID++;
        this.direction = Direction.values()[rand.nextInt(Direction.values().length)];
    }
    
    public int getID () {
        return this.IDfantome;
    }
    
    public Direction choisirDirection () {
        this.direction = Direction.values()[rand.nextInt(Direction.values().length)];
        return this.direction;
    }
    
    public void deplace () {
        if (this.direction == null) {
            choisirDirection();
        }
        this.previous = new Position(pos.getI(),pos.getJ());
        this.pos.move(this.direction);
    }

    int getPreviousI() {
        return this.previous.getI();
    }

    int getPreviousJ() {
        return this.previous.getJ();
    }

    Direction getDirection() {
        return this.direction;
    }

    void positionDepart(Position p) {
        this.pos = new Position(p.getI(), p.getJ());
    }

    void setMort() {
        this.mort = true;
    }

    boolean estMort() {
        return this.mort;
    }
    
}
