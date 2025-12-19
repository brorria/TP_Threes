/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projet_Thees;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final Grille grille;
    private final Random rng;

    public Game() {
        this.grille = new Grille();
        this.rng = new Random();
        initialiser();
    }

    private void initialiser() {
        grille.placerAleatoire1ou2(rng);
        grille.placerAleatoire1ou2(rng);
    }

    public Grille getGrille() {
        return grille;
    }
    public void jouerCoup(Direction dir) {
    boolean changed = Mouvement.deplacerUnPas(grille, dir);
    if (changed) {
        spawnApresCoup(dir);
    }
}

private void spawnApresCoup(Direction dir) {
    int valeur = rng.nextBoolean() ? 1 : 2;

    Direction bord = oppose(dir); // bord recommandé
    List<int[]> candidats = grille.casesVidesSurBord(bord);

    if (candidats.isEmpty()) {
        // fallback : prendre un autre bord dispo
        List<Direction> bords = new ArrayList<>(List.of(Direction.values()));
        Collections.shuffle(bords, rng);

        for (Direction b : bords) {
            candidats = grille.casesVidesSurBord(b);
            if (!candidats.isEmpty()) {
                bord = b;
                break;
            }
        }
    }

    if (!candidats.isEmpty()) {
        int[] pos = candidats.get(rng.nextInt(candidats.size()));
        grille.setValeur(pos[0], pos[1], valeur); // écriture brute
    }
}

private Direction oppose(Direction d) {
    return switch (d) {
        case LEFT -> Direction.RIGHT;
        case RIGHT -> Direction.LEFT;
        case UP -> Direction.DOWN;
        case DOWN -> Direction.UP;
    };
}


}

