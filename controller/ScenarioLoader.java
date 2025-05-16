package controller;

import model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class ScenarioLoader {
    
    public Scenario chargerScenario(String cheminFichier) throws IOException {
        String contenu = new String(Files.readAllBytes(Paths.get(cheminFichier)));
        JSONObject json = new JSONObject(contenu);
        
        String titre = json.getString("titre");
        String description = json.getString("description");
        Scenario scenario = new Scenario(titre, description);
        
        JSONArray chapitresJson = json.getJSONArray("chapitres");
        for (int i = 0; i < chapitresJson.length(); i++) {
            JSONObject chapitreJson = chapitresJson.getJSONObject(i);
            
            int id = chapitreJson.getInt("id");
            String titreChapitre = chapitreJson.getString("titre");
            String texte = chapitreJson.getString("texte");
            
            Chapitre chapitre = new Chapitre(id, titreChapitre, texte);
            
            if (chapitreJson.has("estFinal")) {
                chapitre.setEstFinal(chapitreJson.getBoolean("estFinal"));
            }
            
            if (chapitreJson.has("choix")) {
                JSONArray choixJson = chapitreJson.getJSONArray("choix");
                for (int j = 0; j < choixJson.length(); j++) {
                    JSONObject choixObj = choixJson.getJSONObject(j);
                    String texteChoix = choixObj.getString("texte");
                    int destination = choixObj.getInt("destination");
                    
                    Choix choix;
                    if (choixObj.has("conditionItem")) {
                        String conditionItem = choixObj.getString("conditionItem");
                        choix = new Choix(texteChoix, destination, conditionItem);
                    } else {
                        choix = new Choix(texteChoix, destination);
                    }
                    
                    chapitre.ajouterChoix(choix);
                }
            }
            
            scenario.ajouterChapitre(chapitre);
        }
        
        if (json.has("chapitreInitial")) {
            scenario.setChapitreInitial(json.getInt("chapitreInitial"));
        }
        
        return scenario;
    }
    
    public void sauvegarderPartie(String cheminFichier, Personnage joueur, int idChapitre) throws IOException {
        JSONObject sauvegarde = new JSONObject();
        sauvegarde.put("nomJoueur", joueur.getNom());
        sauvegarde.put("sante", joueur.getSante());
        sauvegarde.put("force", joueur.getForce());
        sauvegarde.put("habilete", joueur.getHabilete());
        sauvegarde.put("chapitreActuel", idChapitre);
        
        JSONArray itemsJson = new JSONArray();
        for (Item item : joueur.getInventaire().getItems()) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("nom", item.getNom());
            itemJson.put("description", item.getDescription());
            itemsJson.put(itemJson);
        }
        sauvegarde.put("inventaire", itemsJson);
        
        try (FileWriter file = new FileWriter(cheminFichier)) {
            file.write(sauvegarde.toString(2));
        }
    }
}


