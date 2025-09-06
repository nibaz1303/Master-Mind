import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe abstraite Joueur représente un joueur dans le jeu du Master Mind.
 * Chaque joueur possède un nom et un score, et peut effectuer des tentatives
 * pour trouver la combinaison secrète d'une partie du jeu.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
abstract class Joueur {

    private String nom;
    private int score;

    /**
     * Constructeur de la classe abstraite Joueur avec nom.
     * <p>
     * Initialise le score d'un joueur à 0 points.
     * 
     * @param nom Le nom du joueur actuel.
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
    }

    public Joueur(String nom, int score) {
        this.nom = nom;
        this.score = score;
    }

    /**
     * Constructeur de la classe abstraite Joueur par défaut.
     * <p>
     * Initialise le score d'un joueur à 0 points et le nom du joueur inconnu à "X".
     */
    public Joueur() {
        this.nom = "X";
        this.score = 0;
    }

    /**
     * @return Le score actuel du joueur.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @return Le nom du joueur actuel.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * <p>
     * Ajoute au score du joueur l'entier gain passé en paramètre.
     * 
     * @param gain Le nombre de points à ajouter au score.
     * @throws IllegalArgumentException Si le gain est négatif.
     */
    public void gagnerScore(int gain) {
        if (gain < 0) {

            throw new IllegalArgumentException("Pas de perte de points");
        }
        this.score = this.score + gain;
    }

    /**
     * <p>
     * Demande à l'utilisateur de saisir le nom du joueur afin de l'initialiser.
     */
    public void initJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Saisissez le nom du joueur: ");
        String nom = scanner.nextLine();
        this.nom = nom;
    }

    /**
     * <p>
     * Définie la signature de la méthode de la tentative d'un
     * joueur. Permettant ainsi de trouver la combinaison secrète d'une partie de
     * jeu.
     * 
     * @param listeCouleurs La liste des couleurs possible pendant une partie.
     * @param nbPion        Le nombre de pions dans la combinaison à deviner.
     */
    public abstract Combinaison tentative(ArrayList<Couleur> listeCouleurs, int nbPion);
}
