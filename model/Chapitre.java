package model;

import java.util.ArrayList;
import java.util.List;

public class Chapitre {
    private int id;
    private String titre;
    private String texte;
    private List<Choix> choix;
    private boolean estFinal;
    
    public Chapitre(int id, String titre, String texte) {
        this.id = id;
        this.titre = titre;
        this.texte = texte;
        this.choix = new ArrayList<>();
        this.estFinal = false;
    }
    
    public void ajouterChoix(Choix choix) {
        this.choix.add(choix);
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getTexte() {
        return texte;
    }
    
    public List<Choix> getChoix() {
        return choix;
    }
    
    public boolean estFinal() {
        return estFinal;
    }
    
    public void setEstFinal(boolean estFinal) {
        this.estFinal = estFinal;
    }
}