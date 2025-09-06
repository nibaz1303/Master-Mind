
/**
 * La classe Combinaison représente une combinaison de pions.
 * Une combinaison est un tableau stockant des objets de classe Pion.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Combinaison {

    private final Pion[] tab;

    /**
     * <p>
     * Recherche la position du pion key dans la combinaison combi à partir de la
     * position start dans le tableau.
     * 
     * @param combi La combinaison dans laquelle on recherche le pion.
     * @param key   Le pion à trouver.
     * @param start L'indice de départ.
     * @return la position du pion dans la combinaison. Sinon, -1 s'il n'est pas
     *         trouvé.
     */
    public static int indexOfPionCombi(Combinaison combi, Pion key, int start) {
        int returnvalue = -1;
        for (int i = start; i < combi.getTailleCombi(); ++i) {
            if (key.comparer(combi.getPionCombi(i))) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    /**
     * Constructeur de la classe Combinaison.
     * <p>
     * Ajoute au tableau de la combinaison actuelle les pions de la combinaison
     * combi.
     * 
     * @param combi La combinaison de pions actuelle.
     * @throws IndexOutOfBoundsException Si la taille du tableau combi n'est pas de
     *                                   taille 4 ou 5.
     */
    public Combinaison(Pion[] combi) {
        if (combi.length < 4 || combi.length > 5) {

            throw new IndexOutOfBoundsException("Combinaison de pion de taille invalide");
        } else {

            Pion[] tab = new Pion[combi.length];
            for (int i = 0; i < tab.length; i++) {

                tab[i] = combi[i];
            }
            this.tab = tab;
        }

    }

    /**
     * 
     * @return La taille de la combinaison.
     */
    public int getTailleCombi() {
        return tab.length;
    }

    /**
     * 
     * @param index Entier représentant une position dans un tableau.
     * @return Un pion de la même couleur que le pion à la position index dans la
     *         combinaison.
     */
    public Pion getPionCombi(int index) {
        return new Pion(tab[index].getCouleur());
    }

    /**
     * <p>
     * Compare la combinaison actuelle avec une autre combinaison combi de même
     * taille passée en paramètre.
     * Vérifie pour chaque pions qu'ils soient de la même couleur et à la même
     * position (1), ils soient de la même couleur mais mal placé (2) ou qu'ils ne
     * soient pas de la même couleur (3).
     * 
     * @param combi La combinaison à comparer.
     * @throws IllegalArgumentException Si les combinaisons ne sont pas de la même
     *                                  taille.
     * @return Un tableau d'entiers représentant le résultat de la comparaison.
     */
    public int[] compareCombi(Combinaison combi) {

        if (this.tab.length != combi.getTailleCombi()) {

            throw new IllegalArgumentException("Comparaison de combinaisons de taille differentes ");
        } else {
            int[] result = new int[this.tab.length];
            boolean[] seenTab = new boolean[this.tab.length];
            for (int i = 0; i < tab.length; i++) {

                if (combi.getPionCombi(i).comparer(this.tab[i])) {
                    result[i] = 1;
                    seenTab[i] = true;
                }

            }
            for (int i = 0; i < tab.length; i++) {

                if (result[i] != 0) {
                    continue;
                }
                int index = Combinaison.indexOfPionCombi(combi, this.tab[i], 0);
                while (index != -1 && seenTab[index] == true) {
                    index = Combinaison.indexOfPionCombi(combi, this.tab[i], index + 1);

                }
                if (index == -1) {

                    result[i] = 3;
                } else {
                    result[i] = 2;
                    seenTab[index] = true;

                }
            }
            return result;

        }

    }

    /**
     * @return Une chaine de caractères représentant l'affichage textuel la
     *         combinaison.
     */
    public String toString() {

        StringBuilder affichage = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {

            affichage.append(tab[i].toString());
            affichage.append(" ");
        }
        return affichage.toString();
    }

}
