package programmingExercices;

public class TestDocument {

    public static void main(String[] args) {
        Document unDocument = new Document(1001, "Les misérables");
        Document unLivre    = new Livre(1002, "Guerre et paix", "L. Tolstoi", 2400);
        Document unRoman    = new Roman(1003, "Les raisins de la colère", "J. Steinbeck", 650, Roman.NOBEL | Roman.ACADEMIE);
        Document unManuel   = new Manuel(1004, "Algebre", "E. Galois", 120, 2);
        Document uneRevue   = new Revue(1005, "Informatique & psychanalyse", 4, 2004);
        Document unDico     = new Dictionnaire(1006, "Caramba!", Dictionnaire.ESPAGNOL);

        System.out.println("un document: " + unDocument);
        System.out.println("un livre:    " + unLivre);
        System.out.println("un roman:    " + unRoman);
        System.out.println("un manuel:   " + unManuel);
        System.out.println("une revue:   " + uneRevue);
        System.out.println("un dico:     " + unDico);
    }
}