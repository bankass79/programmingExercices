package programmingExercices;

public class Roman extends Livre {
    public static final int NEANT      = 0;
    public static final int NOBEL      = 1;
    public static final int GONCOURT   = 2;
    public static final int MEDICIS    = 3;
    public static final int INTERALLIE = 4;
    public static final int ACADEMIE   = 5;
    
    private int prix;

    public Roman(int numeroEnreg, String titre, String auteur, int nbrPages,
            int prix) {
        super(numeroEnreg, titre, auteur, nbrPages);
        this.prix = prix;
    }
    
    public int prix() {
        return prix;
    }
    
    public String toString() {
        String res = super.toString();
        if (prix == GONCOURT)
            res += " - GONCOURT"; 
        if (prix == MEDICIS)
            res += " - MEDICIS"; 
        if (prix == INTERALLIE)
            res += " - INTERALLIE"; 
        if (prix == ACADEMIE)
            res += " - ACADEMIE FRANCAISE"; 
        return res;
    }
}
