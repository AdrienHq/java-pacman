/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public abstract class Aliment {
    
    //protected final TypeAliments typeAliment;
    protected final int POINTS;
    
//    protected Aliment (int points, TypeAliments type) {
//        this.POINTS = points;
//        this.typeAliment = type;
//    }
    
    protected Aliment (int points) {
        this.POINTS = points;
        //this.typeAliment = type;
    }

//    public Aliment (TypeAliments type) {
//        this.typeAliment = type;
//        this.POINTS = 5;
//    }

//    public TypeAliments getType () {
//        return this.typeAliment;
//    }
    
    public int getPoints () {
        return this.POINTS;
    }
    
}
