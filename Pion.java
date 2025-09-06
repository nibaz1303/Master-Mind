
/**
 * La classe Pion défini un objet contenant une couleur unique et spécifique à
 * l'énumération Couleur.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Pion {

    private final Couleur couleur;

    /**
     * Constructeur de la classe Pion.
     * 
     * @param couleur La couleur du pion.
     */
    public Pion(Couleur couleur) {

        this.couleur = couleur;

    }

    /**
     * 
     * @return Le code ANSI de la couleur du pion actuel.
     */
    public int getAnsi() {
        return this.couleur.getAnsi();
    }

    /**
     * 
     * @return La valeur associée à l'objet couleur actuel.
     */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * @return La chaîne de caractères représentant l'affichage textuel le pion.
     */
    public String toString() {

        return "\u001B[" + this.getAnsi() + "m" + "  " + "\u001B[0m";
    }

    /**
     * <p>
     * Compare que la couleur du pion actuelle soit la même au pion passé en
     * paramètre.
     * 
     * @param autrePion Le pion avec lequel on compare la couleur.
     * @return true si les deux pions ont la même couleur, sinon false.
     */
    public boolean comparer(Pion autrePion) {

        return autrePion.getCouleur() == this.couleur;
    }
}
