/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2303frmalherbe
 */
public class Chemin extends Case {
    
    private Aliment aliment;
    
    public Chemin (Aliment a) {
        this.aliment = a;
    }
    
    public Chemin () {
        this.aliment = null;
    }
    
    public Aliment getAliment () {
        return this.aliment;
    }
    
    public void mangerAliment () {
        this.aliment = null;
    }

//    public TypeAliments getTypeAliment() {
//        if (this.aliment != null) {
//            return aliment.getType();
//        }
//        return null;
//    }

    @Override
    public boolean estMur() {
        return false;
    }
}
