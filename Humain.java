import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Humain représente un joueur humain dans le jeu de Master Mind.
 * Elle hérite de la classe Joueur et permet aux joueurs humains de saisir
 * manuellement
 * leurs tentatives de combinaison à l'aide de la méthode
 * {@link #tentative(ArrayList, int)}.
 * Chaque joueur humain possède également un nom et un score.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Humain extends Joueur {

    /**
     * Constructeur par défaut de la classe Humain.
     */
    public Humain() {
        super();
    }

    /**
     * Constructeur avec paramètres de la classe Humain.
     * 
     * @param nom   Le nom du joueur humain.
     * @param score Le score du joueur humain.
     */
    public Humain(String nom, int score) {
        super(nom, score);
    }

    /**
     * Constructeur avec paramètres de la classe Humain.
     * 
     * @param nom Le nom du joueur humain.
     */
    public Humain(String nom) {
        super(nom);
    }

    /**
     * @param listeCouleurs La liste des couleurs disponibles.
     * @return Une combinaison de pions saisie par l'utilisateur.
     * @throws IndexOutOfBoundsException Si l'entrée de l'utilisateur n'est pas un
     *                                   entier ou que l'entier est négatif ou
     *                                   supérieur à la taille de la liste de
     *                                   couleurs.
     */
    public Combinaison tentative(ArrayList<Couleur> listeCouleurs, int nbPion) {

        Pion[] tabPions = new Pion[nbPion];
        Scanner scanner = new Scanner(System.in);
        System.out.print(this.getNom() + " saisissez l'entier de la couleur que vous souhaitez: ");

        for (int j = 0; j < listeCouleurs.size(); j++) {
            System.out.print(j + "." + listeCouleurs.get(j) + " ");
        }
        System.out.println("");

        for (int i = 0; i < tabPions.length; i++) {
            System.out.print("Pion " + i + " :");
            boolean inputValidation = false;
            int nb = -1;

            while (!inputValidation) {
                if (scanner.hasNextInt()) {
                    nb = scanner.nextInt();
                    if (nb < 0 || nb >= listeCouleurs.size()) {
                        System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    } else {

                        inputValidation = true;
                    }
                } else {
                    System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    scanner.next();
                }
            }

            System.out.println("");
            if (nb < 0 || nb > listeCouleurs.size()) {
                throw new IndexOutOfBoundsException(
                        "Le nombre n'est pas dans l'intervalle autorisé " + 0 + " et " + listeCouleurs.size());
            }
            tabPions[i] = new Pion(listeCouleurs.get(nb));
        }
        return new Combinaison(tabPions);
    }
}
