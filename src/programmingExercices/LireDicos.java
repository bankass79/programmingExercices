package programmingExercices;

import java.io.* ;

import File;

class LireDicos
{
    // Lecture d'un fichier avec un mot par ligne.
    // Renvoie le dictionnaire des mots du fichier.
    static Dico2 lireDico (File f)
    {
	Dico2 d = new Dico2 () ;
	try {
	    BufferedReader in = new BufferedReader (new FileReader (f)) ;
	    while (true)
	    {
		String s = in.readLine () ;
		if (s == null) break ;
		s.trim  () ;
		Dico2.inserer (d, s) ;
	    }
	} catch (Exception e) { System.out.println (e) ; }
	return d ;
    }

    // Calcule les dictionnaires obtenus a partir des
    // fichiers dont les noms sont passes en arguments.
    public static void main (String [] args)
    {
	for (int i=0 ; i<args.length ; ++i)
	{
	    File f = new File (args[i]) ;
	    Dico2 d = lireDico (f) ;
	    int h = Dico2.hauteur (d) ;
	    int n = Dico2.taille (d) ;
	    double ln = Math.log ((double) n) / Math.log (2.0) ;
	    System.out.println (args[i] + " " + ln + " " + h) ;
	}
    }
}
