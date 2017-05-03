package programmingExercices;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import javax.swing.Action;

import Node;



public class Tree {

    Node root;			// Référence sur la racince de l'arbre
    /** création d'un arbre vide */
    Tree() { root = new EmptyNode(); }
    /** Ajout d'un élément dans l'arbre binaire de recherche */
    void put(Comparable key) { root = root.put(key); }
    /** Recherche d'une valeur */
    boolean contains(Comparable key) { return root.contains(key); }
    /** Hauteur de l'arbre */
    int height() { return root.height(); }
    /** Parours préfixe de l'arbre */
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
    /** Affichage élégant de l'arbre */
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
     * Itérateur en largeur d'un arbre.
     * Cette classe définit un itérateur qui parcourt en largeur 
     * les noeuds d'un arbre.
     */
    class BreathFirstIterator implements Iterator {
	// La file est directement implémentée avec une liste
	// puisque l'ajout dans une liste Java se fait en queue.
	List lifo;
	BreathFirstIterator() {
	    List lifo = new ArrayList();
	    // Le premier noeud à traiter est la racine
	    if (root instanceof InternalNode) 
		lifo.add(root);
	}
	public boolean hasNext() {
	    // La file contient la liste des noeuds restant à traiter
	    return !lifo.isEmpty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Le noeud à visiter
		InternalNode n = (InternalNode) lifo.get(0);
		// Les fils gauche et droit du noeud à traiter sont
		// ajoutés à la file s'ils existent.
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
    /** Retourne un itérateur pour un parcours en largeur de l'arbre */
    public Iterator breathFirstIterator() { return new BreathFirstIterator(); }
    /** 
     * Classe de parcours global en profondeur d'un arbre.
     * Cette classe ne fournit pas directement un itérateur.  Par contre
     * elle permet une programmation simple et uniforme des trois itérateurs
     * préfixe, infixe et suffixe.
     * Les trois itérateurs qui utilisent cette classes sont les itérateurs
     * SimplePrefixIterator, SimpleInfixIterator et SimpleSuffixIterator.
     */
    class GlobalIterator {
	InternalNode current;	// Noeud courant
	Stack st;		// Branche de la racine au noeud courant
	// Le noeud courant n'est pas mis dans la pile.
	// Quatre constantes pour désigner les différentes visites d'un noeud
	final int p = 0;	// Première visite  (Préfixe)
	final int i = 1;	// Deuxième visite  (Infixe)
	final int s = 2;	// Troisième visite (Suffixe)
	final int f = 3;	// Parcours terminé
	int state;		// État : p, i, s, ou f
	// Constructeur
	GlobalIterator () {
	    if (root instanceof InternalNode) {	
		st = new Stack(); 
		current = (InternalNode) root;
		state = p;
	    } else {		// Si l'arbre est vide
		state = f;	// c'est terminé
	    }
	}
	// Retourne le noeud courant et passe au noeud suivant
	InternalNode next() {
	    InternalNode result = current; // Valeur de retour
	    switch (state) {
	    case p:		// Première visite du noeud
		// Si le fils gauche est interne, il devient le noeud courant
		// et l'ancien noeud courant est empilé.	
		// Sinon, on passe à la deuxième visite du même noeud.
		if (current.left instanceof InternalNode) {
		    st.push(current);
		    current = (InternalNode) current.left;
		} else 
		    state = i;
		break;
	    case i:		// Deuxième visite du noeud
		// Si le fils droit est interne, il devient le noeud courant
		// et l'ancien noeud courant est empilé.
		// Sinon, on passe à la troisième visite du même noeud.
		if (current.right instanceof InternalNode) { 
		    st.push(current);
		    current = (InternalNode) current.right;	
		    state = p;	
		} else 
		    state = s;	
		break;
	    case s:		// Troisième visite du noeud
		if (!st.empty()) { 
		    // Si la pile n'est pas vide, le père est retiré de 
		    // la pile et devient le noeud courant.  Si le noeud
		    // courant était son fils gauche, alors c'est la 
		    // deuxième visite et sinon c'est la troisème.
		    InternalNode father = (InternalNode) st.pop(); 
		    if (current == father.left) { 
			current = father;	
			state = i; 
		    } else {	
			current = father;	
			state = s; 
		    }
		} else {
		    // Si la pile est vide, c'est terminé
		    state = f;	
		}
	    }
	    return result;
	}
    } 
    /** 
     * Itérateur préfixe d'un arbre.
     * Programmation de cet itérateur à l'aide de l'itérateur global.
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
     * Itérateur infixe d'un arbre.
     * Programmation de cet itérateur à l'aide de l'itérateur global.
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
     * Itérateur suffixe d'un arbre.
     * Programmation de cet itérateur à l'aide de l'itérateur global.
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
     * Itérateur préfixe d'un arbre.
     * Programmation directe de l'itérateur sans utiliser l'itérateur global.
     */
    class DirectPrefixIterator implements Iterator {
	 // La pile contient les noeuds qui restent à traiter.
	 // La pile contient les racines des sous-arbres qui restent à
	 // parcourir.  Le prochain noeud à visiter se trouve au sommet de
	 // la pile.  Soit c est le chemin de la racine à ce noeud.  La pile
	 // contient la liste de tous les noeuds qui sont fils droits d'un
	 // noeud de c mais qui ne sont pas sur c, c'est-à-dire lorsque le
	 // chemin c passe d'un noeud à son fils gauche.  
	Stack st;
	DirectPrefixIterator() {
	    st = new Stack();
	    // Le premier noeud à traiter est la racine
	    if (root instanceof InternalNode) 
		st.push(root);
	}
	public boolean hasNext() {
	    return !st.empty();
	}
	public Object next() 
	{
	    if (hasNext()) {
		// Le noeud à visiter
		InternalNode n = (InternalNode) st.pop();
		// Le prochain noeud à visiter est son fils gauche.
		// Lorsque tout le sous-arbre gauche aura été visité, il
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
    /** Retourne un itérateur pour un parcours préfixe de l'arbre */
    public Iterator prefixIterator() { return new SimplePrefixIterator(); }
    // Autre possibilité
    // public Iterator prefixIterator() { return new DirectPrefixIterator(); }
    /** 
     * Itérateur infixe d'un arbre.
     * Programmation directe de l'itérateur sans utiliser l'itérateur global.
     */
    class DirectInfixIterator implements Iterator {
	 // La pile contient les noeuds du chemin de la racine au prochain
	 // noeud à visiter.  Ce dernier se trouve au sommet de la pile alors 
	 // que la racine de l'arbre se trouve en bas de la pile.
	Stack st;
	DirectInfixIterator() {
	    st = new Stack();
	    // Le premier noeud à visiter est celui qui a la plus petite
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
		// Noeud à visiter
		InternalNode current = (InternalNode) st.pop();
		// Si ce noeud n'a pas de fils droit, le prochain noeud
		// à visiter est son père qui se trouve au somment de la
		// pile.  Sinon, le prochain noeud à visiter est celui
		// qui a une valeur minimale dans le sous-arbre droit.
		// Ce noeud est atteint en pacourant la branche gauche
		// issue de la racine de sous-arbre, c'est-à-dire de son
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
    /** Retourne un itérateur pour un parcours infixe de l'arbre */
    public Iterator infixIterator() { return new SimpleInfixIterator(); }
    // Autre possibilité
    // public Iterator infixIterator() { return new DirectInfixIterator(); }
    /** 
     * Itérateur suffixe d'un arbre.
     * Programmation directe de l'itérateur sans utiliser l'itérateur global.
     */
    class DirectSuffixIterator implements Iterator {
	 // La pile contient les noeuds du chemin de la racine au prochain
	 // noeud à visiter.  Ce dernier se trouve au sommet de la pile alors 
	 // que la racine de l'arbre se trouve en bas de la pile.
	Stack st;
	DirectSuffixIterator() {
	    st = new Stack();
	    // Le premier noeud à visiter est la feuille la plus à gauche.
	    // Cette feuille se trouve au bout de la branche qui descend
	    // autant qu'elle peut en utilisant en priorité le fils gauche.
	    // Lorsque le fils gauche existe, cette branche descend par ce
	    // fils et sinon elle descend par le fils droit.
	    for (Node n = root; 
		 n instanceof InternalNode; ) {
		st.push(n);
		InternalNode in = (InternalNode) n;
		// Priorité au fils gauche : si celui existe, on continue à
		// gauche.  Sinon on essaye de continuer à droite.
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
		// Noeud à visiter
		InternalNode current = (InternalNode) st.pop();
		// Si la pile est maintenant vide, le noeud à visiter
		// est la racine et c'est le dernier noeud à visiter.
		if (!st.empty()) {
		    // Sinon le noeud au somment de la pile est le père
		    // du noeud à visiter
		    InternalNode father = (InternalNode) st.peek();
		    // Si le noeud à visiter est le fils droit de son père,
		    // ou si le père n'a pas de fils gauche, le prochain
		    // noeud à visiter est justement son père.  Si le noeud
		    // à visiter est le fils gauche de son père et si son
		    // père à un fils droit, le prochain noeud à visiter
		    // est la feuille la plus à gauche du sous-arbre droit
		    // de son père.  Cette feuille se trouve au bout de la
		    // branche qui part du fils droit de son père et qui
		    // descend autant qu'elle peut en utilisant en priorité
		    // le fils gauche.
		    if (current == father.left) {
			for (Node n = father.right; 
			     n instanceof InternalNode; ) {
			    st.push(n);
			    InternalNode in = (InternalNode) n;
			    // Priorité au fils gauche : si celui existe,
			    // on continue à gauche.  Sinon on essaye de
			    // continuer à droite.
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
    /** Retourne un itérateur pour un parcours suffixe de l'arbre */
    public Iterator suffixIterator() { return new SimpleSuffixIterator(); }
    // Autre possibilité
    // public Iterator suffixIterator() { return new DirectSuffixIterator(); }
}

	
	

