import java.util.LinkedList;

/**
 * La classe Plateau représente l'affichage texutelle des tentatives réalisé
 * durant une {@link Partie}.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Plateau {

    private final LinkedList<Combinaison> essais;

    /**
     * Constructeur de la classe Plateau.
     * Initialise une liste chaînée contenant les tentatives réalisées.
     */
    public Plateau() {
        this.essais = new LinkedList<Combinaison>();

    }

    /**
     * Ajoute la dernière combinaison réalisée à la fin de la liste chainée.
     * 
     * @param essai
     */
    public void addEssai(Combinaison essai) {
        essais.addLast(essai);

    }

    /**
     * @return Une chaîne de caractères représentant le plateau de jeu.
     */
    public String toString() {

        StringBuilder affichage = new StringBuilder();
        affichage.append("|----------MASTERMIND------------|\n");
        for (Combinaison combi : essais) {

            affichage.append(combi.toString());
            affichage.append("\n");
            affichage.append("|--------------------------------|\n");
        }
        return affichage.toString();

    }
}