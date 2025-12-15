package Projet_Thees;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Mouvement {

    // Retourne true si au moins une case a changé (déplacement ou fusion)
    public static boolean deplacerUnPas(Grille g, Direction dir) {
        boolean changed = false;

        // Empêche une même tuile destination de fusionner 2 fois pendant le même coup
        boolean[][] merged = new boolean[Grille.TAILLE][Grille.TAILLE];

        switch (dir) {
            case LEFT -> {
                for (int r = 0; r < Grille.TAILLE; r++) {
                    for (int c = 1; c < Grille.TAILLE; c++) {
                        changed |= tryMove(g, merged, r, c, 0, -1);
                    }
                }
            }
            case RIGHT -> {
                for (int r = 0; r < Grille.TAILLE; r++) {
                    for (int c = Grille.TAILLE - 2; c >= 0; c--) {
                        changed |= tryMove(g, merged, r, c, 0, +1);
                    }
                }
            }
            case UP -> {
                for (int c = 0; c < Grille.TAILLE; c++) {
                    for (int r = 1; r < Grille.TAILLE; r++) {
                        changed |= tryMove(g, merged, r, c, -1, 0);
                    }
                }
            }
            case DOWN -> {
                for (int c = 0; c < Grille.TAILLE; c++) {
                    for (int r = Grille.TAILLE - 2; r >= 0; r--) {
                        changed |= tryMove(g, merged, r, c, +1, 0);
                    }
                }
            }
        }

        return changed;
    }

    // Tente de déplacer la tuile (r,c) d’un pas (dr,dc)
    private static boolean tryMove(Grille g, boolean[][] merged, int r, int c, int dr, int dc) {
        int v = g.getValeur(r, c);
        if (v == 0) return false;

        int nr = r + dr;
        int nc = c + dc;

        int dest = g.getValeur(nr, nc);

        // 1) case cible vide -> déplacement
        if (dest == 0) {
            g.setValeur(nr, nc, v);
            g.setValeur(r, c, 0);
            return true;
        }

        // 2) case cible occupée -> fusion possible ?
        if (!merged[nr][nc]) {
            int mergedValue = mergeResult(v, dest);
            if (mergedValue != -1) {
                g.setValeur(nr, nc, mergedValue);
                g.setValeur(r, c, 0);
                merged[nr][nc] = true;
                return true;
            }
        }

        // 3) sinon, rien
        return false;
    }

    // Retourne la valeur fusionnée, ou -1 si fusion impossible
    private static int mergeResult(int a, int b) {
        if ((a == 1 && b == 2) || (a == 2 && b == 1)) return 3;
        if (a == b) return a * 2;
        return -1;
    }
    
}

