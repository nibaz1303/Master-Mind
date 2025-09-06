import java.util.ArrayList;
import java.util.Random;

/**
 * Cette énumération définit les couleurs possibles d'un pion.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public enum Couleur {

	GRIS(100, "gris"),
	ROUGE(41, "rouge"),
	VERT(42, "vert"),
	JAUNE(43, "jaune"),
	BLEU(44, "bleu"),
	VIOLET(45, "violet"),
	CYAN(106, "cyan"),
	BLANC(47, "blanc");

	private final int ansi;
	private final String nom;

	/**
	 * Constructeur de l'énumération Couleur.
	 * 
	 * @param ansi Code ANSI de la représentation textuelle d'une couleur.
	 * @param nom  Nom de la couleur.
	 */
	Couleur(int ansi, String nom) {
		this.ansi = ansi;
		this.nom = nom;
	}

	/**
	 * @return La valeur entière du code ANSI de la couleur actuelle.
	 */
	public int getAnsi() {
		return this.ansi;
	}

	/**
	 * 
	 * @return Le nom de la couleur actuelle.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Vérifie que la chaine de caractère test soit équivalente au nom d'une des
	 * couleurs disponible dans l'énumération.
	 * 
	 * @param test   Le nom de la couleur rechercher.
	 * @param nb_max Le nombre maximum de couleurs à parcourir.
	 * @return true si la couleur est présente dans l'énumération, sinon false.
	 */
	public static boolean contains(String test, int nb_max) {
		int compteur = 0;
		for (Couleur c : Couleur.values()) {
			compteur = compteur + 1;
			if (compteur > nb_max) {
				return false;
			}
			if (c.getNom().equals(test)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Création d'une liste aléatoire d'un certain nombre de couleurs sans doublon.
	 * 
	 * @param nbCouleur Le nombre de couleurs qu'on souhaite dans la liste.
	 * @return La liste de couleur aléatoire.
	 */
	public static ArrayList<Couleur> listCouleursAlea(int nbCouleur) {
		ArrayList<Couleur> listeCouleurs = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < nbCouleur; i++) {
			int index = random.nextInt(Couleur.values().length);
			Couleur couleur = Couleur.values()[index];
			if (!listeCouleurs.contains(couleur)) {
				listeCouleurs.add(couleur);
			} else {
				i--;
			}

		}
		return listeCouleurs;

	}
}
