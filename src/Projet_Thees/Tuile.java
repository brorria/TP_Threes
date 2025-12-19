/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projet_Thees;

public class Tuile {
    private int valeur; // 0 = vide

    public Tuile() {
        this.valeur = 0;
    }

    public Tuile(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public boolean estVide() {
        return valeur == 0;
    }
    
}
