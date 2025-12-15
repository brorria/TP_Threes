package Projet_Thees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Grille {
    public static final int TAILLE = 4;
    private final Tuile[][] cases;

    public Grille() {
        cases = new Tuile[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                cases[i][j] = new Tuile();
            }
        }
    }

    public Tuile getTuile(int ligne, int col) {
        return cases[ligne][col];
    }
    
    public boolean estVide(int ligne, int col) {
    return cases[ligne][col].estVide();
}

public void placerTuile(int ligne, int col, int valeur) {
    if (!estVide(ligne, col)) {
        throw new IllegalStateException("Case (" + ligne + "," + col + ") non vide");
    }
    cases[ligne][col].setValeur(valeur);
}

public List<int[]> casesVides() {
    List<int[]> vides = new ArrayList<>();
    for (int i = 0; i < TAILLE; i++) {
        for (int j = 0; j < TAILLE; j++) {
            if (cases[i][j].estVide()) {
                vides.add(new int[]{i, j});
            }
        }
    }
    return vides;
}

public boolean placerAleatoire1ou2(Random rng) {
    List<int[]> vides = casesVides();
    if (vides.isEmpty()) return false;

    int[] pos = vides.get(rng.nextInt(vides.size()));
    int valeur = rng.nextBoolean() ? 1 : 2;
    placerTuile(pos[0], pos[1], valeur);
    return true;
}


public int getValeur(int ligne, int col) {
    return cases[ligne][col].getValeur();
}

public void setValeur(int ligne, int col, int valeur) {
    cases[ligne][col].setValeur(valeur);
}

public List<int[]> casesVidesSurBord(Direction bord) {
    List<int[]> res = new ArrayList<>();

    switch (bord) {
        case LEFT -> {
            for (int r = 0; r < TAILLE; r++) {
                if (getValeur(r, 0) == 0) res.add(new int[]{r, 0});
            }
        }
        case RIGHT -> {
            for (int r = 0; r < TAILLE; r++) {
                if (getValeur(r, TAILLE - 1) == 0) res.add(new int[]{r, TAILLE - 1});
            }
        }
        case UP -> {
            for (int c = 0; c < TAILLE; c++) {
                if (getValeur(0, c) == 0) res.add(new int[]{0, c});
            }
        }
        case DOWN -> {
            for (int c = 0; c < TAILLE; c++) {
                if (getValeur(TAILLE - 1, c) == 0) res.add(new int[]{TAILLE - 1, c});
            }
        }
    }
    return res;
}



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                sb.append(String.format("%4d", cases[i][j].getValeur()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

