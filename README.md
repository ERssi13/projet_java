# Projet de Livre Dont Vous Êtes le Héros

## Description
Ce projet est une implémentation d'un jeu de type "Livre Dont Vous Êtes le Héros" en Java, permettant aux joueurs de vivre une histoire interactive avec des choix multiples qui influencent le déroulement de l'aventure.

## Objectifs
- Développer une architecture logicielle modulaire et extensible
- Permettre la création et l'exécution d'un scénario interactif
- Offrir une gestion fluide des choix et des conséquences des actions du joueur
- Fournir une interface graphique en Swing (prioritaire) et une version en ligne de commande

## Architecture du Projet

### Package model (Modèle)
- **Scenario** : Contient l'ensemble des chapitres et leurs connexions
- **Chapitre** : Représente une section du livre avec un texte et des choix
- **Choix** : Associe un texte de choix et la référence vers le chapitre suivant
- **Personnage** : (optionnel) Contient des informations sur le joueur (inventaire, statistiques, etc.)

### Package controller (Contrôleur)
- **GameController** : Gère la progression de l'histoire et l'interaction utilisateur
- **ScenarioLoader** : Charge les scénarios depuis un fichier JSON, XML ou base de données

### Package view (Vue)
- **SwingUI** : Interface graphique principale en Swing
- **TextUI** : Interface en ligne de commande en option pour tester le jeu plus rapidement

## Fonctionnalités Principales
- Affichage d'un chapitre et de ses choix
- Saisie du choix par le joueur et navigation entre les chapitres
- Chargement dynamique d'un scénario depuis un fichier externe
- Possibilité de sauvegarder et charger une partie

## Évolutivité
Le projet est conçu pour être extensible avec les fonctionnalités suivantes prévues :
- Ajout d'un système d'inventaire et de gestion des statistiques du joueur
- Création d'un éditeur de scénarios
- Possibilité d'intégrer d'autres bibliothèques graphiques

## Organisation du Projet
- **Nombre de participants** : 2 personnes
- **Outils utilisés** : 
  - GitHub pour la gestion des versions et le travail collaboratif
  - Trello pour la gestion de projet
- **Technologies** :
  - Java (JDK 17 ou supérieur)
  - Swing pour l'interface graphique
  - Jackson pour la gestion du JSON

## Livrables
- Un document détaillant l'analyse et la conception du projet
  - Diagrammes UML des classes et des packages
  - Description détaillée de l'architecture
  - Spécifications techniques
  - Maquettes de l'interface utilisateur
- Code source documenté
  - Documentation JavaDoc complète
  - Commentaires explicatifs dans le code
  - Tests unitaires avec couverture de code
- Une partie jouable du projet avec un minimum de fonctionnalités fonctionnelles
  - Au moins 3 chapitres jouables
  - Interface graphique Swing fonctionnelle
  - Système de sauvegarde/chargement
  - Gestion des choix et navigation entre les chapitres

## Installation et Utilisation

### Prérequis
- Java JDK 17 ou supérieur
- Maven 3.8 ou supérieur
- Git

### Installation
1. Cloner le repository
   ```bash
   git clone https://github.com/votre-username/projet_java.git
   cd projet_java
   ```

2. Compiler le projet avec Maven
   ```bash
   mvn clean install
   ```

3. Exécuter le jeu
   ```bash
   # Version avec interface graphique Swing
   java -jar target/projet_java-1.0-SNAPSHOT.jar --gui
   
   # Version en ligne de commande
   java -jar target/projet_java-1.0-SNAPSHOT.jar --cli
   ```

### Structure du Projet
```
projet_java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/
│   │   │   │   ├── Scenario.java
│   │   │   │   ├── Chapitre.java
│   │   │   │   ├── Choix.java
│   │   │   │   └── Personnage.java
│   │   │   ├── controller/
│   │   │   │   ├── GameController.java
│   │   │   │   └── ScenarioLoader.java
│   │   │   └── view/
│   │   │       ├── SwingUI.java
│   │   │       └── TextUI.java
│   │   └── resources/
│   │       └── scenarios/
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## Planning
Le projet se déroule du 6 mai au 21 mai 2024.

### Semaine 19 (06/05 - 10/05) : Développement de base
- Implémentation des classes du modèle (Scenario, Chapitre, etc.)
- Développement du contrôleur (GameController, ScenarioLoader)
- Création de l'interface graphique en Swing
- Mise en place des tests unitaires

### Semaine 20 (13/05 - 17/05) : Tests et améliorations
- Tests unitaires et correction de bugs
- Ajustement de l'interface graphique
- Implémentation du système de sauvegarde
- Création du scénario de test

### Semaine 21 (20/05 - 21/05) : Finalisation et rendu
- Préparation de la version fonctionnelle du jeu
- Rédaction de la documentation technique et utilisateur
- Présentation du projet et démonstration

## Contribution
Les contributions sont les bienvenues. Veuillez suivre les conventions de code établies et créer une branche pour chaque nouvelle fonctionnalité.

## Licence
Ce projet est sous licence .
