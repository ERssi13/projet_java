package main;

import controller.GameController;

public class Main {
    public static void main(String[] args) {
        boolean utiliserSwing = true;
        GameController controller = new GameController(utiliserSwing);
        if (!utiliserSwing) {
            controller.getTextUI().demanderNomJoueur();
        }
    }
}