/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Projet_Thees;

/**
 *
 * @author martincorria
 */
import java.util.Scanner;

public class Projet_Thees_V01 {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        System.out.println("Commandes: W=haut, A=gauche, S=bas, D=droite, Q=quitter");
        System.out.println(game.getGrille());

        while (true) {
            System.out.print("Move: ");
            String s = sc.nextLine().trim().toUpperCase();
            if (s.equals("Q")) break;

            Direction dir = switch (s) {
                case "W" -> Direction.UP;
                case "A" -> Direction.LEFT;
                case "S" -> Direction.DOWN;
                case "D" -> Direction.RIGHT;
                default -> null;
            };
            if (dir == null) continue;

            game.jouerCoup(dir);
            System.out.println(game.getGrille());
        }
    }
}

