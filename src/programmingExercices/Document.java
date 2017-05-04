package programmingExercices;

public class Document {
    private int numeroEnreg;
    private String titre;

    public Document(int numeroEnreg, String titre) {
        this.numeroEnreg = numeroEnreg;
        this.titre = titre;
    }

    public int numeroEnreg() {
        return numeroEnreg;
    }

    public String titre() {
        return titre;
    }

    public String toString() {
        return titre + " [N° " + numeroEnreg + "]";
    }
    
    public boolean equals(Object obj) {
        return obj instanceof Document 
                && ((Document) obj).numeroEnreg == numeroEnreg;
    }
}