package programmingExercices;

public class Manuel extends Livre {
    private int niveau;
    
    public Manuel(int numeroEnreg, String titre, String auteur,
            int nombrePages, int niveau) {
        super(numeroEnreg, titre, auteur, nombrePages);
        this.niveau = niveau;
    }
    
    public int niveau() {
        return niveau;
    }
    
    public String toString() {
        return super.toString() + " - classe de " + niveau + "°";
    }
}