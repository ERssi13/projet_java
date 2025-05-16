package model;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {
    private List<Item> items;
    
    public Inventaire() {
        this.items = new ArrayList<>();
    }
    
    public void ajouterItem(Item item) {
        items.add(item);
    }
    
    public boolean retirerItem(String nom) {
        return items.removeIf(item -> item.getNom().equals(nom));
    }
    
    public boolean contient(String nom) {
        return items.stream().anyMatch(item -> item.getNom().equals(nom));
    }
    
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}