package controller;

import model.*;
import view.SwingUI;
import view.TextUI;

import java.io.IOException;

public class GameController {
    private Scenario scenario;
    private Personnage joueur;
    private Chapitre chapitreActuel;
    private SwingUI swingUI;
    private TextUI textUI;
    private boolean utilisationSwing;
    
    public GameController(boolean utilisationSwing) {
        this.utilisationSwing = utilisationSwing;
        if (utilisationSwing) {
            this.swingUI = new SwingUI(this);
        } else {
            this.textUI = new TextUI(this);
        }
    }
    public void initialiserJeu(String nomJoueur) {
        this.joueur = new Personnage(nomJoueur);
        joueur.getInventaire().ajouterItem(new Item("Épée", "Une épée simple mais robuste"));
        
        try {
            ScenarioLoader loader = new ScenarioLoader();
            this.scenario = loader.chargerScenario("resources/scenario.json");
            this.chapitreActuel = scenario.getChapitreInitial();
            afficherChapitre();
        } catch (IOException e) {
            if (utilisationSwing) {
                swingUI.afficherErreur("Erreur lors du chargement du scénario: " + e.getMessage());
            } else {
                textUI.afficherErreur("Erreur lors du chargement du scénario: " + e.getMessage());
            }
        }
    }
    
    public void afficherChapitre() {
        if (chapitreActuel == null) return;
        
        if (utilisationSwing) {
            swingUI.afficherChapitre(chapitreActuel, joueur);
        } else {
            textUI.afficherChapitre(chapitreActuel, joueur);
        }
    }
    
    public void selectionnerChoix(int indexChoix) {
        if (chapitreActuel == null || indexChoix < 0 || indexChoix >= chapitreActuel.getChoix().size()) {
            return;
        }
        
        Choix choixSelectionne = chapitreActuel.getChoix().get(indexChoix);
        
        if (choixSelectionne.requiertItem() && !joueur.possede(choixSelectionne.getConditionItem())) {
            String message = "Vous ne possédez pas l'objet requis: " + choixSelectionne.getConditionItem();
            if (utilisationSwing) {
                swingUI.afficherMessage(message);
            } else {
                textUI.afficherMessage(message);
            }
            return;
        }
        this.chapitreActuel = scenario.getChapitre(choixSelectionne.getChapitreDestination());
        afficherChapitre();
    }
    
    public Personnage getJoueur() {
        return joueur;
    }
    
    public void recommencer() {
        this.chapitreActuel = scenario.getChapitreInitial();
        joueur = new Personnage(joueur.getNom());
        joueur.getInventaire().ajouterItem(new Item("Épée", "Une épée simple mais robuste"));
        afficherChapitre();
    }
    
    public void quitter() {
        System.exit(0);
    }
}