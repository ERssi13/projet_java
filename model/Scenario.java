package model;

import java.util.HashMap;
import java.util.Map;

public class Scenario {
    private String titre;
    private String description;
    private Map<Integer, Chapitre> chapitres;
    private int chapitreInitial;
    
    public Scenario(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.chapitres = new HashMap<>();
        this.chapitreInitial = 1;
    }
    
    public void ajouterChapitre(Chapitre chapitre) {
        chapitres.put(chapitre.getId(), chapitre);
    }
    
    public void setChapitreInitial(int id) {
        if (chapitres.containsKey(id)) {
            this.chapitreInitial = id;
        }
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Chapitre getChapitre(int id) {
        return chapitres.get(id);
    }
    
    public Chapitre getChapitreInitial() {
        return chapitres.get(chapitreInitial);
    }
}