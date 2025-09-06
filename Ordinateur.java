import java.util.Random;
import java.util.ArrayList;

/**
 * La classe Ordinateur représente un joueur ordinateur dans le jeu de Master
 * Mind.
 * Elle hérite de la classe Joueur et permet de jouer contre un ordinateur qui
 * choisi aléatoirement une {@link #tentative(ArrayList, int)} chaque tour d'une
 * partie.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Ordinateur extends Joueur {

    /**
     * Constructeur sans paramètres de la classe Ordinateur.
     * 
     */
    public Ordinateur() {
        super("Ordinateur");
    }

    /**
     * Constructeur avec paramètres de la classe Ordinateur.
     * 
     * @param score Le score du joueur Ordinateur.
     */
    public Ordinateur(int score) {
        super("Ordinateur", score);
    }

    /**
     * @param listeCouleurs La liste des couleurs disponibles.
     * @return Une combinaison de pions aléatoire jouée par l'ordinateur.
     */
    public Combinaison tentative(ArrayList<Couleur> listeCouleurs, int nbPion) {
        Pion[] tabPions = new Pion[nbPion];
        Random random = new Random();
        for (int i = 0; i < nbPion; i++) {
            int index = random.nextInt(listeCouleurs.size());
            Pion pion = new Pion(listeCouleurs.get(index));
            tabPions[i] = pion;
        }
        return new Combinaison(tabPions);
    }
}
