package model;

public class Personnage {
    private String nom;
    private Inventaire inventaire;
    private int sante;
    private int force;
    private int habilete;
    
    public Personnage(String nom) {
        this.nom = nom;
        this.inventaire = new Inventaire();
        this.sante = 100;
        this.force = 10;
        this.habilete = 10;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Inventaire getInventaire() {
        return inventaire;
    }
    
    public int getSante() {
        return sante;
    }
    
    public void setSante(int sante) {
        this.sante = Math.max(0, Math.min(100, sante));
    }
    
    public int getForce() {
        return force;
    }
    
    public void setForce(int force) {
        this.force = force;
    }
    
    public int getHabilete() {
        return habilete;
    }
    
    public void setHabilete(int habilete) {
        this.habilete = habilete;
    }
    
    public boolean possede(String nomItem) {
        return inventaire.contient(nomItem);
    }
}   