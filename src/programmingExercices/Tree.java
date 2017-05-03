package programmingExercices;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import javax.swing.Action;

import Node;



public class Tree {

    Node root;			// R�f�rence sur la racince de l'arbre
    /** cr�ation d'un arbre vide */
    Tree() { root = new EmptyNode(); }
    /** Ajout d'un �l�ment dans l'arbre binaire de recherche */
    void put(Comparable key) { root = root.put(key); }
    /** Recherche d'une valeur */
    boolean contains(Comparable key) { return root.contains(key); }
    /** Hauteur de l'arbre */
    int height() { return root.height(); }
    /** Parours pr�fixe de l'arbre */
    void prefixRun(Action a) { root.prefixRun(a); }
    /** Parours infixe de l'arbre */
    void infixRun(Action a) { root.infixRun(a); }
    /** Parours suffixe de l'arbre */
    void suffixRun(Action a) { root.suffixRun(a); }
    /** Serialisation */
    public String toString()
    {
	StringBuffer sb = new StringBuffer();
	root.toString(sb);
	return sb.toString();
    }
    /** Affichage �l�gant de l'arbre */
    public void prettyPrint()
    {
	int k;
	int h = height();
	StringBuffer sb = new StringBuffer();

	// Print each level of the tree
	for(k = 0; k < h; k++) {
	    sb.setLength(0);	// Reset BufferString
	    root.toString(sb, k);
	    System.out.println(sb);
	}
    }
    /**
     * It�rateur en largeur d'un arbre.
     * Cette classe d�finit un it�rateur qui parcourt en largeur 
     * les noeuds d'un arbre.
     */
    class BreathFirstIterator implements Iterator {
	// La file est directement impl�ment�e avec une liste
	// puisque l'ajout dans une liste Java se fait en queue.
	List lifo;
	BreathFirstIterator() {
	    List lifo = new ArrayList();
	    // Le premier noeud � traiter est la racine
	    if (root instanceof InternalNode) 
		lifo.add(root);
	}
	public boolean hasNext() {
	    // La file contient la liste des noeuds restant � traiter
	    return !lifo.isEmpty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Le noeud � visiter
		InternalNode n = (InternalNode) lifo.get(0);
		// Les fils gauche et droit du noeud � traiter sont
		// ajout�s � la file s'ils existent.
		if (n.left instanceof InternalNode)
		    lifo.add(n.left);
		if (n.right instanceof InternalNode)
		    lifo.add(n.right);
		return n.value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** Retourne un it�rateur pour un parcours en largeur de l'arbre */
    public Iterator breathFirstIterator() { return new BreathFirstIterator(); }
    /** 
     * Classe de parcours global en profondeur d'un arbre.
     * Cette classe ne fournit pas directement un it�rateur.  Par contre
     * elle permet une programmation simple et uniforme des trois it�rateurs
     * pr�fixe, infixe et suffixe.
     * Les trois it�rateurs qui utilisent cette classes sont les it�rateurs
     * SimplePrefixIterator, SimpleInfixIterator et SimpleSuffixIterator.
     */
    class GlobalIterator {
	InternalNode current;	// Noeud courant
	Stack st;		// Branche de la racine au noeud courant
	// Le noeud courant n'est pas mis dans la pile.
	// Quatre constantes pour d�signer les diff�rentes visites d'un noeud
	final int p = 0;	// Premi�re visite  (Pr�fixe)
	final int i = 1;	// Deuxi�me visite  (Infixe)
	final int s = 2;	// Troisi�me visite (Suffixe)
	final int f = 3;	// Parcours termin�
	int state;		// �tat : p, i, s, ou f
	// Constructeur
	GlobalIterator () {
	    if (root instanceof InternalNode) {	
		st = new Stack(); 
		current = (InternalNode) root;
		state = p;
	    } else {		// Si l'arbre est vide
		state = f;	// c'est termin�
	    }
	}
	// Retourne le noeud courant et passe au noeud suivant
	InternalNode next() {
	    InternalNode result = current; // Valeur de retour
	    switch (state) {
	    case p:		// Premi�re visite du noeud
		// Si le fils gauche est interne, il devient le noeud courant
		// et l'ancien noeud courant est empil�.	
		// Sinon, on passe � la deuxi�me visite du m�me noeud.
		if (current.left instanceof InternalNode) {
		    st.push(current);
		    current = (InternalNode) current.left;
		} else 
		    state = i;
		break;
	    case i:		// Deuxi�me visite du noeud
		// Si le fils droit est interne, il devient le noeud courant
		// et l'ancien noeud courant est empil�.
		// Sinon, on passe � la troisi�me visite du m�me noeud.
		if (current.right instanceof InternalNode) { 
		    st.push(current);
		    current = (InternalNode) current.right;	
		    state = p;	
		} else 
		    state = s;	
		break;
	    case s:		// Troisi�me visite du noeud
		if (!st.empty()) { 
		    // Si la pile n'est pas vide, le p�re est retir� de 
		    // la pile et devient le noeud courant.  Si le noeud
		    // courant �tait son fils gauche, alors c'est la 
		    // deuxi�me visite et sinon c'est la trois�me.
		    InternalNode father = (InternalNode) st.pop(); 
		    if (current == father.left) { 
			current = father;	
			state = i; 
		    } else {	
			current = father;	
			state = s; 
		    }
		} else {
		    // Si la pile est vide, c'est termin�
		    state = f;	
		}
	    }
	    return result;
	}
    } 
    /** 
     * It�rateur pr�fixe d'un arbre.
     * Programmation de cet it�rateur � l'aide de l'it�rateur global.
     */
    class SimplePrefixIterator implements Iterator {
	GlobalIterator gi;
	SimplePrefixIterator() {
	    gi = new GlobalIterator();
	}
	public boolean hasNext() {
	    while (gi.state != gi.p && gi.state != gi.f)
		gi.next();
	    return gi.state == gi.p;
	}
	public Object next() 
	{
	    if (hasNext()) {
		return gi.next().value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** 
     * It�rateur infixe d'un arbre.
     * Programmation de cet it�rateur � l'aide de l'it�rateur global.
     */
    class SimpleInfixIterator implements Iterator {
	GlobalIterator gi;
	SimpleInfixIterator() {
	    gi = new GlobalIterator();
	}
	public boolean hasNext() {
	    while (gi.state != gi.i && gi.state != gi.f)
		gi.next();
	    return gi.state == gi.i;
	}
	public Object next() 
	{
	    if (hasNext()) {
		return gi.next().value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** 
     * It�rateur suffixe d'un arbre.
     * Programmation de cet it�rateur � l'aide de l'it�rateur global.
     */
    class SimpleSuffixIterator implements Iterator {
	GlobalIterator gi;
	SimpleSuffixIterator() {
	    gi = new GlobalIterator();
	}
	public boolean hasNext() {
	    while (gi.state != gi.s && gi.state != gi.f)
		gi.next();
	    return gi.state == gi.s;
	}
	public Object next() 
	{
	    if (hasNext()) {
		return gi.next().value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** 
     * It�rateur pr�fixe d'un arbre.
     * Programmation directe de l'it�rateur sans utiliser l'it�rateur global.
     */
    class DirectPrefixIterator implements Iterator {
	 // La pile contient les noeuds qui restent � traiter.
	 // La pile contient les racines des sous-arbres qui restent �
	 // parcourir.  Le prochain noeud � visiter se trouve au sommet de
	 // la pile.  Soit c est le chemin de la racine � ce noeud.  La pile
	 // contient la liste de tous les noeuds qui sont fils droits d'un
	 // noeud de c mais qui ne sont pas sur c, c'est-�-dire lorsque le
	 // chemin c passe d'un noeud � son fils gauche.  
	Stack st;
	DirectPrefixIterator() {
	    st = new Stack();
	    // Le premier noeud � traiter est la racine
	    if (root instanceof InternalNode) 
		st.push(root);
	}
	public boolean hasNext() {
	    return !st.empty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Le noeud � visiter
		InternalNode n = (InternalNode) st.pop();
		// Le prochain noeud � visiter est son fils gauche.
		// Lorsque tout le sous-arbre gauche aura �t� visit�, il
		// faudra visiter le sous-arbre droit.  On commence donc
		// par empiler le fils droit (s'il existe) et le fils
		// gauche qui se retrouve au somment de la pile.
		if (n.right instanceof InternalNode)
		    st.push(n.right);
		if (n.left instanceof InternalNode)
		    st.push(n.left);
		return n.value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** Retourne un it�rateur pour un parcours pr�fixe de l'arbre */
    public Iterator prefixIterator() { return new SimplePrefixIterator(); }
    // Autre possibilit�
    // public Iterator prefixIterator() { return new DirectPrefixIterator(); }
    /** 
     * It�rateur infixe d'un arbre.
     * Programmation directe de l'it�rateur sans utiliser l'it�rateur global.
     */
    class DirectInfixIterator implements Iterator {
	 // La pile contient les noeuds du chemin de la racine au prochain
	 // noeud � visiter.  Ce dernier se trouve au sommet de la pile alors 
	 // que la racine de l'arbre se trouve en bas de la pile.
	Stack st;
	DirectInfixIterator() {
	    st = new Stack();
	    // Le premier noeud � visiter est celui qui a la plus petite
	    // valeur.  Ce noeud se trouve au bout de la branche gauche.
	    // Ce noeud n'a pas de fils gauche mais il peut avoir un fils
	    // droit.  On empile donc les noeuds de la branche gauche.
	    for (Node n = root; 
		 n instanceof InternalNode; 
		 n = ((InternalNode)n).left)
		st.push(n);
	}
	public boolean hasNext() {
	    return !st.empty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Noeud � visiter
		InternalNode current = (InternalNode) st.pop();
		// Si ce noeud n'a pas de fils droit, le prochain noeud
		// � visiter est son p�re qui se trouve au somment de la
		// pile.  Sinon, le prochain noeud � visiter est celui
		// qui a une valeur minimale dans le sous-arbre droit.
		// Ce noeud est atteint en pacourant la branche gauche
		// issue de la racine de sous-arbre, c'est-�-dire de son
		// fils droit.
		for (Node n = current.right; 
		     n instanceof InternalNode; 
		     n = ((InternalNode)n).left)
		    st.push(n);
		return current.value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** Retourne un it�rateur pour un parcours infixe de l'arbre */
    public Iterator infixIterator() { return new SimpleInfixIterator(); }
    // Autre possibilit�
    // public Iterator infixIterator() { return new DirectInfixIterator(); }
    /** 
     * It�rateur suffixe d'un arbre.
     * Programmation directe de l'it�rateur sans utiliser l'it�rateur global.
     */
    class DirectSuffixIterator implements Iterator {
	 // La pile contient les noeuds du chemin de la racine au prochain
	 // noeud � visiter.  Ce dernier se trouve au sommet de la pile alors 
	 // que la racine de l'arbre se trouve en bas de la pile.
	Stack st;
	DirectSuffixIterator() {
	    st = new Stack();
	    // Le premier noeud � visiter est la feuille la plus � gauche.
	    // Cette feuille se trouve au bout de la branche qui descend
	    // autant qu'elle peut en utilisant en priorit� le fils gauche.
	    // Lorsque le fils gauche existe, cette branche descend par ce
	    // fils et sinon elle descend par le fils droit.
	    for (Node n = root; 
		 n instanceof InternalNode; ) {
		st.push(n);
		InternalNode in = (InternalNode) n;
		// Priorit� au fils gauche : si celui existe, on continue �
		// gauche.  Sinon on essaye de continuer � droite.
		if (in.left instanceof InternalNode) 
		    n = in.left;
		else 
		    n = in.right;
	    }
	}
	public boolean hasNext() {
	    return !st.empty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Noeud � visiter
		InternalNode current = (InternalNode) st.pop();
		// Si la pile est maintenant vide, le noeud � visiter
		// est la racine et c'est le dernier noeud � visiter.
		if (!st.empty()) {
		    // Sinon le noeud au somment de la pile est le p�re
		    // du noeud � visiter
		    InternalNode father = (InternalNode) st.peek();
		    // Si le noeud � visiter est le fils droit de son p�re,
		    // ou si le p�re n'a pas de fils gauche, le prochain
		    // noeud � visiter est justement son p�re.  Si le noeud
		    // � visiter est le fils gauche de son p�re et si son
		    // p�re � un fils droit, le prochain noeud � visiter
		    // est la feuille la plus � gauche du sous-arbre droit
		    // de son p�re.  Cette feuille se trouve au bout de la
		    // branche qui part du fils droit de son p�re et qui
		    // descend autant qu'elle peut en utilisant en priorit�
		    // le fils gauche.
		    if (current == father.left) {
			for (Node n = father.right; 
			     n instanceof InternalNode; ) {
			    st.push(n);
			    InternalNode in = (InternalNode) n;
			    // Priorit� au fils gauche : si celui existe,
			    // on continue � gauche.  Sinon on essaye de
			    // continuer � droite.
			    if (in.left instanceof InternalNode) 
				n = in.left;
			    else 
				n = in.right;
			}
		    }
		}
		return current.value;
	    } else {
		throw new NoSuchElementException();
	    }
	}
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    /** Retourne un it�rateur pour un parcours suffixe de l'arbre */
    public Iterator suffixIterator() { return new SimpleSuffixIterator(); }
    // Autre possibilit�
    // public Iterator suffixIterator() { return new DirectSuffixIterator(); }
}

	
	

