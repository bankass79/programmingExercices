package programmingExercices;

public class Livre extends Document {
    private String auteur;
    private int nbrPages;
    
    public Livre(int numeroEnreg, String titre, String auteur, int nbrPages) {
        super(numeroEnreg, titre);
        this.auteur = auteur;
        this.nbrPages = nbrPages;
    }

    public String auteur() {
        return auteur;
    }
    
    public int nombrePages () {
        return nbrPages;
    }
    
    public String toString() {
        return super.toString() + " - " + auteur + "(" + nbrPages + " pages)";
    }
}
