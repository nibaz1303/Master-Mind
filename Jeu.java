
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * La classe Jeu gère l'initialisation d'un certain nombre de parties à partir
 * de la création des joueurs.
 * 
 * @author Stephen BOUCHARDON
 * @author Jean BAREKZAI
 * @version 1.0
 */
public class Jeu {
    private int nbJoueurs;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private int partiesJoues;
    private int nbParties;

    /**
     * Constructeur à paramètres de la classe Jeu.
     * 
     * @param slot Numéro de la sauvegarde qui va servir à initialiser le jeu.
     * @throws IllegalArgumentException Si le numéro de la sauvegarde n'est
     *                                  pas dans l'intervalle autorisé.
     */

    public Jeu(int slot) {
        if (slot < 0 || slot > 3) {
            throw new IllegalArgumentException("Slot de save illegal");
        }

        ArrayList<String> data = this.load(slot); // on charge les données necessaires pour initialiser le jeu depuis
                                                  // l'emplacement de sauvegarde voulu
        this.nbJoueurs = Integer.valueOf(data.get(0));
        this.nbParties = Integer.valueOf(data.get(1));
        this.partiesJoues = Integer.valueOf(data.get(2));
        String nom = data.get(4);
        int score = Integer.valueOf(data.get(3));
        this.joueurs.add(new Humain(nom, score));
        if (this.nbJoueurs == 2) {
            nom = data.get(6);
            score = Integer.valueOf(data.get(5));

            if (nom.equals("Ordinateur")) {

                this.joueurs.add(new Ordinateur(score));
            } else {

                this.joueurs.add(new Humain(nom, score));
            }
        }

    }

    /**
     * Constructeur de la classe Jeu.
     * <p>
     * Les utilisateurs saisissent eux-mêmes les paramètres de jeu: le nombre de
     * joueurs, leurs noms et le nombre de parties dans le menu principal à partir
     * de {@link #menuJeu()}.
     */
    public Jeu() {
        this.partiesJoues = 0;
        this.menuJeu();
        if (nbJoueurs == 2) {
            Scanner scanner = new Scanner(System.in);
            int inputUser = -1;
            boolean inputValidation = false;
            while (!inputValidation) {
                System.out.println("Jouer avec un Ordinateur ? (0 pour non, 1 pour oui)");
                if (scanner.hasNextInt()) {
                    inputUser = scanner.nextInt();
                    if (inputUser != 0 && inputUser != 1) {
                        System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    } else {
                        inputValidation = true;
                    }
                } else {
                    System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    scanner.next();
                }
            }
            if (inputUser == 0) {
                this.initListJoueurs();
            } else {
                Humain joueur = new Humain();
                joueur.initJoueur();
                this.joueurs.add(joueur);
                Ordinateur ordi = new Ordinateur();

                this.joueurs.add(ordi);
            }

        } else {
            this.initListJoueurs();
        }

    }

    /**
     * 
     * @return Le nombre de joueurs actuel.
     */
    public int getNbJoueurs() {
        return this.nbJoueurs;
    }

    /**
     * 
     * @param index
     * @return Le nom du joueur à un certain indice dans la liste de joueurs.
     */
    public String getNomJoueur(int index) {
        return this.joueurs.get(index).getNom();
    }

    /**
     * <p>
     * Initilise la liste des joueurs du jeu à partir de
     * {@link Joueur#initJoueur()}.
     */
    public void initListJoueurs() {
        for (int index = 0; index < this.nbJoueurs; index++) {
            Humain joueur = new Humain();
            joueur.initJoueur();
            this.joueurs.add(joueur);
        }
    }

    /**
     * <p>
     * Affiche les scores des joueurs.
     */
    public void printScores() {
        System.out.println("|----------Résultat du jeu----------|");
        for (int index = 0; index < this.nbJoueurs; index++) {
            System.out.println(this.joueurs.get(index).getNom() + " = " + this.joueurs.get(index).getScore() + " pts");
        }
        System.out.println("|-----------------------------------|");
    }

    /**
     * <p>
     * Génère un certain nombres de parties.
     * À la fin de chaque parties, {@link Partie#newPartie()} renvoie le tableau de
     * scores et incrémente aux joueurs leurs nouveaux points.
     */
    public void lancerJeu() throws IOException {
        for (int index = this.partiesJoues; index < this.nbParties; index++) {
            System.out.print("\033\143");
            System.out.print("Partie ");
            System.out.print(index + 1);
            System.out.println(":");
            Partie partie = new Partie(this.nbJoueurs);
            int[] tabScores = partie.newPartie(joueurs);

            for (int kndex = 0; kndex < this.nbJoueurs; kndex++) {
                this.joueurs.get(kndex).gagnerScore(tabScores[kndex]);
            }
            if (index + 1 != this.nbParties) {

                Scanner scanner = new Scanner(System.in);
                int inputUser = -1;
                boolean inputValidation = false;
                System.out.println("Voulez-vous sauvegarder ? (0 pour non / 1 pour oui)  ");

                while (!inputValidation) {
                    if (scanner.hasNextInt()) {
                        inputUser = scanner.nextInt();
                        if (inputUser != 1 && inputUser != 0) {
                            System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                        } else {
                            inputValidation = true;
                        }
                    } else {
                        System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                        scanner.next();
                    }
                }
                if (inputUser == 1) {

                    inputValidation = false;
                    System.out.println("Quel emplacement ? (1,2,3) ");
                    while (!inputValidation) {

                        if (scanner.hasNextInt()) {
                            inputUser = scanner.nextInt();
                            if (inputUser != 1 && inputUser != 2 && inputUser != 3) {
                                System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                            } else {
                                inputValidation = true;
                            }
                        } else {
                            System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                            scanner.next();
                        }
                    }

                    this.save(inputUser, index + 1);
                }
            }

        }
    }

    /**
     * <p>
     * Réalise l'affichage textuelle de l'entrée dans le jeu. Les utilisateurs y
     * sont démandés de saisir les paramètrages de jeu.
     * 
     * @throws InterruptedException Si il y a une interruption pendant la pause de
     *                              l'éxécution du programme.
     */
    public void menuJeu() {

        Scanner scanner = new Scanner(System.in);

        for (int index = 0; index < 2; index++) {
            boolean inputValidation = false;
            int inputUser = -1;
            if (index == 0) {
                System.out.print("Combien de joueurs êtes-vous ? ");
            } else {
                System.out.print("Combien de parties voulez-vous jouer ? ");
            }

            while (!inputValidation) {
                if (scanner.hasNextInt()) {
                    inputUser = scanner.nextInt();
                    if (index == 0 && (inputUser < 1 || inputUser > 2) || (index == 1 && inputUser <= 0)) {
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
            if (index == 0) {
                this.setNbJoueurs(inputUser);
            } else {
                this.setNbParties(inputUser);
            }

        }

    }

    /**
     * <p>
     * Assigne au nombre de joueurs de jeu l'entier passé en paramètre.
     * 
     * @param inputNbJoueurs Un nombre de joueurs.
     */
    private void setNbJoueurs(int inputNbJoueurs) {
        if (inputNbJoueurs < 1 || inputNbJoueurs > 2) {
            throw new IndexOutOfBoundsException(
                    "Le nombre de joueurs " + inputNbJoueurs + ", n'est pas dans l'intervalle autorisé.");
        }
        this.nbJoueurs = inputNbJoueurs;
    }

    /**
     * <p>
     * Assigne au nombre de parties de jeu l'entier passé en paramètre.
     * 
     * @param inputNbParties Un nombre de parties.
     */
    private void setNbParties(int inputNbParties) {
        if (inputNbParties <= 0) {
            throw new IndexOutOfBoundsException(
                    "Le nombre de parties " + inputNbParties + ", n'est pas dans l'intervalle autorisé.");
        }
        this.nbParties = inputNbParties;
    }

    private void save(int slot, int partiesRestantes) throws IOException {
        if (slot < 0 || slot > 3) {
            throw new IllegalArgumentException("Slot de save illegal");
        }
        File save = new File("save-" + String.valueOf(slot) + ".txt");
        FileWriter fileWriter = new FileWriter(save);

        fileWriter.write(String.valueOf(this.nbJoueurs) + "\n");
        fileWriter.write(String.valueOf(this.nbParties) + "\n");
        fileWriter.write(String.valueOf(partiesRestantes) + "\n");
        fileWriter.write(String.valueOf(this.joueurs.get(0).getScore()) + "\n");
        fileWriter.write(this.joueurs.get(0).getNom() + "\n");
        if (nbJoueurs == 2) {
            fileWriter.write(String.valueOf(this.joueurs.get(1).getScore()) + "\n");
            fileWriter.write(this.joueurs.get(1).getNom() + "\n");
        } else {
            fileWriter.write(String.valueOf(-1) + "\n");
            fileWriter.write("nada" + "\n");
        }

        fileWriter.close();

    }

    private ArrayList<String> load(int slot) {
        if (slot < 0 || slot > 3) {
            throw new IllegalArgumentException("Slot de save illegal");
        }
        ArrayList<String> data = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File("save-" + String.valueOf(slot) + ".txt"));

            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}
