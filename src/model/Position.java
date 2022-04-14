/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mad
 */
public class Position {
        
        private int i;
        private int j;
        
        public Position (int i, int j) throws IllegalArgumentException {
            setI(i);
            setJ(j);
        }
        
        public int getI () {
            return this.i;
        }
        
        public int getJ () {
            return this.j;
        }
        
        private void setI (int i) {
            if (i >= 0 && i < Labyrinthe.HAUTEUR) {
                this.i = i;
            } else {
                throw new IllegalArgumentException ("i is not in the authorized range");
            }
        }
        
        private void setJ (int j) {
            if (j >= 0 && j < Labyrinthe.LONGUEUR) {
                this.j = j;
            } else {
                throw new IllegalArgumentException ("j is not in the authorized range");
            }
        }
                
        public void move (Direction d) {
            switch (d) {
                case HAUT : this.i--; break;
                case BAS : this.i++; break;
                case GAUCHE : this.j--;break;
                case DROITE : this.j++; break;
            }
        }
        
        public boolean equals (Position pos) {
            return pos.getI() == this.i && pos.getJ() == this.j;
        }
        
        @Override
        public String toString () {
            return "i: " + this.i + "  j: " + this.j;
        }
        
    }
