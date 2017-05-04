package programmingExercices;

public class Dico
{
    Noeud racine ;

    Dico ()
    {
	racine = null ;
    }

    public static void inserer (Dico d, String m)
    {
	d.racine = insererArbre (d.racine, m) ;
    }

    static Noeud insererArbre (Noeud n, String m)
    {
	if (n == null) return new Noeud (m, null, null) ;
	if (m.compareTo (n.str) == 0) return n ; // le mot est deja la
	if (m.compareTo (n.str) < 0)
	    n.filsGauche = insererArbre (n.filsGauche, m) ;
	else n.filsDroit = insererArbre (n.filsDroit, m) ;
	return n ;
    }

    public static boolean existe (Dico d, String m)
    {
	return existeArbre (d.racine, m) ;
    }

    static boolean existeArbre (Noeud n, String m)
    {
	if (n == null) return false ;
	if (m.compareTo (n.str) == 0) return true ;
	if (m.compareTo (n.str) < 0)
	    return existeArbre (n.filsGauche, m) ;
	return existeArbre (n.filsDroit, m) ;
    }

    public static void main (String[] args)
    {
	Dico d = new Dico () ;
	for (int i=1 ; i<args.length ; ++i)
	    inserer (d, args[i]) ;
	System.out.println (existe (d, args[0])) ;
	System.out.println (hauteur (d) + " " + taille (d)) ;
    }

    // Solution de 2.1 :
    static int hauteur (Dico d)
    {
	return hauteurArbre (d.racine) ;
    }
    static int hauteurArbre (Noeud n)
    {
	if (n == null) return 0 ;
	int hg = hauteurArbre (n.filsGauche) ;
	int hd = hauteurArbre (n.filsDroit) ;
	if (hg > hd) return hg + 1 ;
	return hd + 1 ;
    }

    static int taille (Dico d)
    {
	return tailleArbre (d.racine) ;
    }
    static int tailleArbre (Noeud n)
    {
	if (n == null) return 0 ;
	return tailleArbre (n.filsGauche) + tailleArbre (n.filsDroit) + 1 ;
    }
}
