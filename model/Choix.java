package model;

public class Choix {
    private String texte;
    private int chapitreDestination;
    private String conditionItem;
    
    public Choix(String texte, int chapitreDestination) {
        this.texte = texte;
        this.chapitreDestination = chapitreDestination;
        this.conditionItem = null;
    }
    
    public Choix(String texte, int chapitreDestination, String conditionItem) {
        this.texte = texte;
        this.chapitreDestination = chapitreDestination;
        this.conditionItem = conditionItem;
    }
    
    public String getTexte() {
        return texte;
    }
    
    public int getChapitreDestination() {
        return chapitreDestination;
    }
    
    public String getConditionItem() {
        return conditionItem;
    }
    
    public boolean requiertItem() {
        return conditionItem != null;
    }
}