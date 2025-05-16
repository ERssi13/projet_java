package view;

import model.*;
import controller.GameController;

import java.util.List;
import java.util.Scanner;

public class TextUI {
    private Scanner scanner;
    private GameController controller;
    
    public TextUI(GameController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public void demanderNomJoueur() {
        System.out.println("=== QUÊTE DU GRAAL: CHÂTEAU DES TÉNÈBRES ===");
        System.out.println("Entrez votre nom, aventurier:");
        String nom = scanner.nextLine().trim();
        
        if (nom.isEmpty()) {
            nom = "Chevalier Sans Nom";
        }
        
        controller.initialiserJeu(nom);
    }
    
    public void afficherChapitre(Chapitre chapitre, Personnage joueur) {
        clearConsole();
        System.out.println("=== " + chapitre.getTitre().toUpperCase() + " ===");
        System.out.println("Santé: " + joueur.getSante());
        System.out.println("----------------");
        System.out.println(chapitre.getTexte());
        System.out.println("----------------");
        
        if (chapitre.estFinal()) {
            System.out.println("FIN DE L'AVENTURE");
            System.out.println("Tapez 'R' pour recommencer ou 'Q' pour quitter");
            String choix = scanner.nextLine().toUpperCase();
            if (choix.equals("R")) {
                controller.recommencer();
            } else {
                controller.quitter();
            }
            return;
        }
        
        List<Choix> choix = chapitre.getChoix();
        System.out.println("Que souhaitez-vous faire?");
        for (int i = 0; i < choix.size(); i++) {
            String choixText = (i + 1) + ". " + choix.get(i).getTexte();
            if (choix.get(i).requiertItem() && !joueur.possede(choix.get(i).getConditionItem())) {
                choixText += " (Nécessite: " + choix.get(i).getConditionItem() + ")";
            }
            System.out.println(choixText);
        }
        
        System.out.println("I. Afficher l'inventaire");
        System.out.println("R. Recommencer");
        System.out.println("Q. Quitter");
        
        boolean choixValide = false;
        while (!choixValide) {
            String input = scanner.nextLine().toUpperCase();
            
            if (input.equals("I")) {
                afficherInventaire(joueur);
                System.out.println("\nQue souhaitez-vous faire?");
                continue;
            } else if (input.equals("R")) {
                controller.recommencer();
                choixValide = true;
            } else if (input.equals("Q")) {
                controller.quitter();
                choixValide = true;
            } else {
                try {
                    int choixIndex = Integer.parseInt(input) - 1;
                    if (choixIndex >= 0 && choixIndex < choix.size()) {
                        if (choix.get(choixIndex).requiertItem() && 
                            !joueur.possede(choix.get(choixIndex).getConditionItem())) {
                            System.out.println("Vous ne possédez pas l'objet requis: " 
                                              + choix.get(choixIndex).getConditionItem());
                            System.out.println("Choisissez une autre option:");
                        } else {
                            controller.selectionnerChoix(choixIndex);
                            choixValide = true;
                        }
                    } else {
                        System.out.println("Choix invalide. Veuillez réessayer:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre, 'I', 'R' ou 'Q':");
                }
            }
        }
    }
    
    private void afficherInventaire(Personnage joueur) {
        System.out.println("\n=== INVENTAIRE DE " + joueur.getNom().toUpperCase() + " ===");
        List<Item> items = joueur.getInventaire().getItems();
        if (items.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
        } else {
            for (Item item : items) {
                System.out.println("- " + item.getNom() + ": " + item.getDescription());
            }
        }
    }
    
    public void afficherMessage(String message) {
        System.out.println(message);
        System.out.println("Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    public void afficherErreur(String message) {
        System.out.println("ERREUR: " + message);
        System.out.println("Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}