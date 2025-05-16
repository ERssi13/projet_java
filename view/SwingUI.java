package view;

import model.*;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea texteArea;
    private JPanel choixPanel;
    private JPanel statusPanel;
    private JLabel santeLbl;
    private JButton inventaireBtn;
    private JButton recommencerBtn;
    private JButton quitterBtn;
    
    private GameController controller;
    
    public SwingUI(GameController controller) {
        this.controller = controller;
        initialiserUI();
    }
    
    private void initialiserUI() {
        frame = new JFrame("Quête du Graal: Château des Ténèbres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(49, 27, 16)); 

        texteArea = new JTextArea();
        texteArea.setEditable(false);
        texteArea.setLineWrap(true);
        texteArea.setWrapStyleWord(true);
        texteArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        texteArea.setForeground(new Color(255, 234, 190));
        texteArea.setBackground(new Color(100, 60, 30));
        texteArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 85, 40), 3),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollPane = new JScrollPane(texteArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        choixPanel = new JPanel();
        choixPanel.setLayout(new BoxLayout(choixPanel, BoxLayout.Y_AXIS));
        choixPanel.setBackground(new Color(49, 27, 16));
        choixPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        mainPanel.add(choixPanel, BorderLayout.SOUTH);
        
        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(49, 27, 16));
        santeLbl = new JLabel("Santé: 100");
        santeLbl.setForeground(new Color(230, 200, 150));
        santeLbl.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        inventaireBtn = new JButton("Inventaire");
        styleButton(inventaireBtn);
        inventaireBtn.addActionListener(e -> afficherInventaire());
        
        recommencerBtn = new JButton("Recommencer");
        styleButton(recommencerBtn);
        recommencerBtn.addActionListener(e -> controller.recommencer());
        
        quitterBtn = new JButton("Quitter");
        styleButton(quitterBtn);
        quitterBtn.addActionListener(e -> controller.quitter());
        
        statusPanel.add(santeLbl);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(inventaireBtn);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(recommencerBtn);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(quitterBtn);
        
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
        
        SwingUtilities.invokeLater(this::demanderNomJoueur);
    }
    
    private void styleButton(JButton button) {
        button.setBackground(new Color(120, 85, 40));
        button.setForeground(new Color(255, 234, 190));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(75, 50, 25), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    }
    
    private void demanderNomJoueur() {
        String nom = JOptionPane.showInputDialog(frame, 
                "Entrez votre nom, aventurier:", 
                "Quête du Graal", 
                JOptionPane.QUESTION_MESSAGE);
        
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Chevalier Sans Nom";
        }
        
        controller.initialiserJeu(nom);
    }
    
    public void afficherChapitre(Chapitre chapitre, Personnage joueur) {
        texteArea.setText(chapitre.getTitre().toUpperCase() + "\n\n" + chapitre.getTexte());
        santeLbl.setText("Santé: " + joueur.getSante());
        choixPanel.removeAll();
        
        if (chapitre.estFinal()) {
            JButton finBtn = new JButton("Fin de l'aventure - Recommencer");
            styleButton(finBtn);
            finBtn.addActionListener(e -> controller.recommencer());
            choixPanel.add(finBtn);
        } else {
            List<Choix> choix = chapitre.getChoix();
            for (int i = 0; i < choix.size(); i++) {
                final int index = i;
                JButton choixBtn = new JButton((i + 1) + ". " + choix.get(i).getTexte());
                styleButton(choixBtn);
                if (choix.get(i).requiertItem() && !joueur.possede(choix.get(i).getConditionItem())) {
                    choixBtn.setEnabled(false);
                    choixBtn.setText(choixBtn.getText() + " (Nécessite: " + choix.get(i).getConditionItem() + ")");
                }
                
                choixBtn.addActionListener(e -> controller.selectionnerChoix(index));
                choixPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                choixPanel.add(choixBtn);
            }
        }
        
        choixPanel.revalidate();
        choixPanel.repaint();
    }
    
    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(frame, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    private void afficherInventaire() {
        Personnage joueur = controller.getJoueur();
        StringBuilder inventaire = new StringBuilder("Inventaire de " + joueur.getNom() + ":\n\n");
        
        List<Item> items = joueur.getInventaire().getItems();
        if (items.isEmpty()) {
            inventaire.append("Votre inventaire est vide.");
        } else {
            for (Item item : items) {
                inventaire.append("- ").append(item.getNom()).append(": ").append(item.getDescription()).append("\n");
            }
        }
        
        JOptionPane.showMessageDialog(frame, inventaire.toString(), "Inventaire", JOptionPane.INFORMATION_MESSAGE);
    }
}