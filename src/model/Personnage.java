package model;

public class Personnage {
    
    protected Position pos = null;
    
    public Position getPosition () {
        return this.pos;
    }
    
    public int getI () {
        return this.pos.getI();
    }
    
    public int getJ () {
        return this.pos.getJ();
    }
    
    public void deplace (Direction d) {
        this.pos.move(d);
    }
}
