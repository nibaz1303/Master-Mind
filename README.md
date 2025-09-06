# Mastermind (Mastermind2024)

Version console en Java du jeu Mastermind.

## Description
Ce projet implémente une version console du jeu Mastermind. Le joueur humain et l'ordinateur sont représentés par des classes distinctes ; le jeu se joue depuis le terminal sans dépendances externes.

## Prérequis
- Java JDK 11+ (ou JDK 8 minimum).
- Terminal (zsh sur Linux recommandé).

## Structure du projet
Les fichiers principaux situés à la racine :
- `Main.java` — point d'entrée du programme.
- `Jeu.java` — logique principale du déroulement d'une partie.
- `Partie.java` — gestion d'une session de jeu (tours, essais).
- `Joueur.java` — abstraction/interface d'un joueur.
- `Humain.java` — implémentation du joueur humain (saisie console).
- `Ordinateur.java` — implémentation d'un joueur IA/ordinateur.
- `Combinaison.java` — représentation d'une combinaison de pions.
- `Pion.java` — représentation d'un pion/position.
- `Couleur.java` — énumération ou représentation des couleurs disponibles.
- `Plateau.java` — historique des tentatives et affichage.

(adapté de la structure fournie dans le dépôt)

## Règles de jeu (résumé)
- Une combinaison secrète est choisie par l'ordinateur ou le joueur.
- Le but est de deviner la combinaison en un nombre limité de coups.
- Après chaque essai, des indices sont fournis : nombre de pions bien placés et nombre de pions présents mais mal placés.
- Les paramètres (longueur de la combinaison, nombre de couleurs, nombre de tentatives) sont définis dans le code ou via des constantes.

## Compiler et exécuter
Depuis la racine du projet, compilez puis lancez :

```bash
# Compiler tous les fichiers .java
javac *.java

# Lancer le programme (classe contenant main)
java Main
```

Si votre `Main` se trouve dans un package ou si vous préférez générer les .class dans un dossier `bin` :

```bash
javac -d bin $(find . -maxdepth 1 -name "*.java")
java -cp bin Main
```

## Utilisation
- Suivez les instructions affichées dans la console pour sélectionner le mode (Humain vs Ordinateur, difficultés, etc.).
- Saisissez vos propositions de combinaisons au format attendu (consultez le code de `Humain.java` pour le format exact s'il y a un parsing particulier).

Exemples courants :
- Proposer une combinaison : `ROUGE BLEU VERT JAUNE`
- Quitter : utilisez la commande prévue par le jeu (souvent `q` ou `quit`) ou Ctrl+C.

## Personnalisation
- Modifier les constantes dans `Jeu.java` ou `Partie.java` pour changer la longueur de la combinaison, le nombre de tentatives ou les couleurs disponibles.
- Améliorer l'IA en éditant `Ordinateur.java` : stratégie naïve, stratégie par filtrage d'espace d'états, etc.

## Tests et améliorations suggérées
- Ajouter des tests unitaires (JUnit) pour `Combinaison`, `Plateau` et l'évaluation d'essais.
- Ajouter un script `build.sh` ou un `Makefile` pour automatiser compilation/exécution.
- Externaliser la configuration (longueur, couleurs) dans un fichier JSON ou properties.

## Licence
Ajoutez une licence si nécessaire (ex : MIT, Apache-2.0). Par défaut, aucun licence explicite n'est fournie.

## Contact
Pour contribuer, ouvrir des issues ou proposer des améliorations, modifiez le dépôt et soumettez une PR.

