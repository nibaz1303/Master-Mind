import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.print("\033\143");
        System.out.println("|----------MASTERMIND------------|\n");
        System.out.println("|          Bienvenu !            |\n");
        System.out.println("|--------------------------------|\n");
        try {
            Thread.sleep(3000);
            System.out.print("\033\143");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> slots = new ArrayList<Integer>();
        for (int i = 1; i < 4; i++) {
            if (new File("save-" + String.valueOf(i) + ".txt").isFile()) { // on regarde si des fichiers de sauvegarde
                                                                           // sont disponibles
                slots.add(i); // on ajoute les fichiers disponibles dans une liste
            }
        }
        if (slots.size() > 0) { // si un fichier est disponible on demande à l'utilisateur si il veut charger
                                // une partie
            System.out.println("Sauvegarde disponible");
            Scanner scanner = new Scanner(System.in);
            int inputUser = 0;
            boolean inputValidation = false;
            System.out
                    .println("Donner le numéro de l'emplacement (1,2,3) pour  charger ou 4 pour une nouvelle partie  ");

            while (!inputValidation) {
                if (scanner.hasNextInt()) {
                    inputUser = scanner.nextInt();
                    if (inputUser != 4 && !slots.contains(inputUser)) {
                        System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    } else {
                        inputValidation = true;
                    }
                } else {
                    System.out.println("\u001B[" + 31 + "m" + "Saisissez une valeur valide." + "\u001B[0m");
                    scanner.next();
                }
            }
            if (inputUser != 4) {
                Jeu jeu = new Jeu(inputUser); // initialisation d'une partie avec une sauvegarde
                jeu.lancerJeu();
                jeu.printScores();
            } else {
                Jeu jeu = new Jeu(); // initialisation d'une partie sans sauvegarde
                jeu.lancerJeu();
                jeu.printScores();
            }
        } else {
            Jeu jeu = new Jeu(); // initialisation d'une partie sans sauvegarde
            jeu.lancerJeu();
            jeu.printScores();
        }

    }
}
