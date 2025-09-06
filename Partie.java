
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;

/**
 * La classe Partie représente une partie du jeu de Master Mind.
 * Elle gère le paramètrage du nombre de tentatives, de couleurs possibles, de
 * pions dans une combinaison et de joueurs. On associe à une partie une
 * combinaison secrète que le joueur devra découvrir avec la combinaison
 * tentative.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Partie {
    private final int nbTentative;
    private final int nbCouleur;
    private final int nbPion;
    private final int nbJoueur;
    private final int typeIndication;
    private final ArrayList<Couleur> listeCouleurs;
    private Combinaison secret;
    private Combinaison tentative;

    /**
     * Constructeur de la classe Partie.
     * <p>
     * Initialise les paramètres d'une partie: le nombre de tentatives, de couleurs,
     * de pions et de joueurs.
     * En fonction du nombre de couleurs, la méthode créé une liste aléatoire de
     * couleurs et initialise finalement la combinaison secrète de la partie.
     * 
     * @param nbJoueur Le nombre de joueurs dans la partie.
     */
    public Partie(int nbJoueur) {
        this.nbTentative = setEntierIntervalle(10, 12, "tentatives");
        this.nbCouleur = setEntierIntervalle(6, 8, "couleurs");
        this.nbPion = setEntierIntervalle(4, 5, "pions");
        this.typeIndication = setEntierIntervalle(1, 2, "type");
        this.nbJoueur = nbJoueur;
        this.listeCouleurs = Couleur.listCouleursAlea(this.nbCouleur);
        this.secret = createCombinaisonAlea(this.listeCouleurs);
    }

    /**
     * @param min
     * @param max
     * @param string La description du nombre dans l'intervalle.
     * @throws IndexOutOfBoundsException Si la valeur n'est pas dans l'intervalle
     *                                   autorisé.
     * @return L'entier saisi par l'utilisateur
     */
    private int setEntierIntervalle(int min, int max, String string) {
        Scanner scanner = new Scanner(System.in);
        int inputUser = min - 1;
        boolean inputValidation = false;

        if (string.equals("type")) {
            System.out.println("Veuillez choisir le type d'indication de position des pions :");
            System.out.println("1. pour chaque pion.");
            System.out.println("2. par nature de position.");
        } else {
            System.out.println("Entrez le nombre souhaitez de " + string + " entre " + min + " et " + max + ":");
        }

        while (!inputValidation) {
            if (scanner.hasNextInt()) {
                inputUser = scanner.nextInt();
                if (inputUser < min || inputUser > max) {
                    System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                } else {
                    inputValidation = true;
                }
            } else {
                System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                scanner.next();
            }
        }

        if (inputUser < min || inputUser > max) {
            throw new IndexOutOfBoundsException("Le nombre n'est pas dans l'intervalle autorisé " + min + " et " + max);
        }
        return inputUser;
    }

    /**
     * @param listeCouleurs La liste des couleurs disponibles.
     * @return Une combinaison de pions aléatoire.
     */
    public Combinaison createCombinaisonAlea(ArrayList<Couleur> listeCouleurs) {
        Pion[] tabPions = new Pion[this.nbPion];
        Random random = new Random();
        for (int i = 0; i < this.nbPion; i++) {
            int index = random.nextInt(this.nbCouleur);
            Pion pion = new Pion(listeCouleurs.get(index));
            tabPions[i] = pion;
        }
        return new Combinaison(tabPions);
    }

    /**
     * À partir de {@link Combinaison#compareCombi(Combinaison)}, vérifie que chaque
     * pions de la tentative soit à sa bonne position.
     * 
     * @return true si les pions sont tous bien positionnés. Sinon, false.
     */
    public boolean verificationCombi() {
        int[] result = this.secret.compareCombi(this.tentative);
        for (int index = 0; index < result.length; index++) {
            if (result[index] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * En fonction du type d'indication choisi en début de partie, cette méthode
     * réalise le style d'affichage des positons.
     * Soit spécifiquement pour chaque pions, soit par natures de position de pion.
     */
    private void printCombiVerification() {
        int[] result = this.secret.compareCombi(this.tentative);
        if (this.typeIndication == 1) {
            for (int i = 0; i < result.length; i++) {
                switch (result[i]) {
                    case 1:
                        System.out.println("Le pion " + i + " est bien placé !");
                        break;
                    case 2:
                        System.out.println("Le pion " + i + " est mal placé ...");
                        break;
                    default:
                        System.out.println("Le pion " + i + " n'est pas de la bonne couleur :(");
                        break;
                }
            }
        } else {
            int[] sumResult = new int[3];
            for (int i = 0; i < result.length; i++) {
                switch (result[i]) {
                    case 1:
                        sumResult[0]++;
                        break;
                    case 2:
                        sumResult[1]++;
                        break;
                    default:
                        sumResult[2]++;
                        break;
                }
            }
            if (sumResult[0] != 0) {
                System.out.println(sumResult[0] + " est/sont bien positionné(s) !");
            }
            if (sumResult[1] != 0) {
                System.out.println(sumResult[1] + " est/sont mal positionné(s) ...");
            }
            if (sumResult[2] != 0) {
                System.out.println(sumResult[2] + " ne est/sont pas de la bonne couleur :(");
            }
        }
    }

    /**
     * <p>
     * Débute une nouvelle partie de jeu. Continue à tourner tant que le ou les
     * utilisateurs ont encore des tentatives possibles à réaliser.
     * 
     * @throws InterruptedException Si il y a une interruption pendant la pause de
     *                              l'execution du programme.
     * @return Un tableau d'entier stockant les scores du ou des joueurs.
     */
    public int[] newPartie(ArrayList<Joueur> joueurs) {
        int[] tabScores = new int[this.nbJoueur];

        for (int j = 0; j < this.nbJoueur; j++) {
            Plateau plateau_jeu = new Plateau();
            System.out.print("\033\143");
            System.out.println("Au tour de " + joueurs.get(j).getNom() + ":");
            for (int i = 0; i < this.nbTentative; i++) {
                System.out.println("\nTentative " + (i + 1) + " :");

                this.tentative = joueurs.get(j).tentative(this.listeCouleurs, this.nbPion);

                plateau_jeu.addEssai(tentative);

                boolean boolComparaison = this.verificationCombi();

                if (boolComparaison == true) {
                    System.out.print("\033\143");
                    System.out.println("Vous avez gagné " + joueurs.get(j).getNom()
                            + " ! La combinaison secrete était: " + this.secret);
                    tabScores[j] = this.nbTentative - i;
                    try {
                        Thread.sleep(5000);
                        System.out.print("\033\143");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                if (boolComparaison == false && i + 1 == this.nbTentative) {
                    System.out.print("\033\143");
                    System.out.println("Vous avez perdu " + joueurs.get(j).getNom()
                            + " :( La combinaison secrete était: " + this.secret);
                    tabScores[j] = 0;
                    try {
                        Thread.sleep(5000);
                        System.out.print("\033\143");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                System.out.print("\033\143");
                System.out.println(plateau_jeu);

                printCombiVerification();
                if (joueurs.get(j).getNom() == "Ordinateur") {
                    System.out.print("Appouer sur un touche pour passer au suivant.");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();

                }
            }
        }

        return tabScores;
    }
}
