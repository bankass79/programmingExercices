package programmingExercices;

import Noeud;

public class Dico2
{
    Noeud racine ;

    Dico2 ()
    {
	racine = null ;
    }

    public static void inserer (Dico2 d, String m)
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
	n = equilibrer (n) ;
	return n ;
    }

   static Noeud equilibrer (Noeud x)
   {
      if (x == null) return null ;
      int hg = Noeud.hauteur (x.filsGauche),
	  hd = Noeud.hauteur (x.filsDroit) ;
      boolean gaucheGrand = false ;
      if (hg > hd + 1) gaucheGrand = true ;
      else if (hd <= hg + 1) 
      {
	 Noeud.calcHauteur (x) ;
	 return x ;
      }
      Noeud fg = x.filsGauche, fd = x.filsDroit ;
      Noeud u = gaucheGrand ? fg : fd ;
      int phg = Noeud.hauteur (u.filsGauche), phd = Noeud.hauteur (u.filsDroit) ;
      if (phg == phd)
      {
	 System.err.println ("Erreur dans l'equilibrage...\n\n") ;
	 System.exit (1) ;
	 return x ;
      }
// Rotation simple
      else if (gaucheGrand && phg > phd)
      {
	 x.filsGauche = fg.filsDroit ;
	 Noeud.calcHauteur (x) ;
	 return new Noeud (fg.str, fg.filsGauche, x) ;
      }
      else if  (!gaucheGrand && phd > phg)
      {
	 x.filsDroit = fd.filsGauche ;
	 Noeud.calcHauteur (x) ;
	 return new Noeud (fd.str, x, fd.filsDroit) ;
      }
// Rotation double
      else
      {
	 Noeud v = gaucheGrand ? fg.filsDroit : fd.filsGauche ;
	 if (gaucheGrand)
	 {
	    x.filsGauche = v.filsDroit ;
	    Noeud.calcHauteur (x) ;
	    fg.filsDroit = v.filsGauche ;
	    Noeud.calcHauteur (fg) ;
	    return new Noeud (v.str, fg, x) ;
	 }
	 else
	 {
	    x.filsDroit = v.filsGauche ;
	    Noeud.calcHauteur (x) ;
	    fd.filsGauche = v.filsDroit ;
	    Noeud.calcHauteur (fd) ;
	    return new Noeud (v.str, x, fd) ;
	 }
      }
   }

    public static boolean existe (Dico2 d, String m)
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
	Dico2 d = new Dico2 () ;
	for (int i=1 ; i<args.length ; ++i)
	    inserer (d, args[i]) ;
	System.out.println (existe (d, args[0])) ;
	supprimer (d, args[0]) ;
	System.out.println (existe (d, args[0])) ;
	System.out.println (hauteur (d) + " " + taille (d)) ;
    }

    static int hauteur (Dico2 d)
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

    static int taille (Dico2 d)
    {
	return tailleArbre (d.racine) ;
    }
    static int tailleArbre (Noeud n)
    {
	if (n == null) return 0 ;
	return tailleArbre (n.filsGauche) + tailleArbre (n.filsDroit) + 1 ;
    }

    // Question subsidiaire :
    static void supprimer (Dico2 d, String m)
    {
	d.racine = supprimerArbre (d.racine, m) ;
    }
   static Noeud supprimerArbre (Noeud n, String m)
   {
      if (n == null) return null ;
      if (n.str.compareTo (m) > 0)
	 n.filsGauche = supprimerArbre (n.filsGauche, m) ;
      else if (n.str.compareTo (m) < 0)
	 n.filsDroit = supprimerArbre (n.filsDroit, m) ;
      else
      {
	 // Cas 1 : n n'a aucun fils
	 if (n.filsGauche == null && n.filsDroit == null) return null ;
	 // Cas 2 : n a un seul fils
	 if (n.filsGauche == null) return n.filsDroit ;
	 if (n.filsDroit == null) return n.filsGauche ;
	 // Cas 3 : n a deux fils
	 Noeud suivant = n.filsDroit ;
	 while (suivant.filsGauche != null)
	    suivant = suivant.filsGauche ;
	 n.str = suivant.str ;
	 n.filsDroit = supprimerArbre (n.filsDroit, suivant.str) ;
      }
      n = equilibrer (n) ;  // Ici !!!!!!!!!!!
      return n ;
   }
}
