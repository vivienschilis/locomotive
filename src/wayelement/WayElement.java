package wayelement;
/**
 * Interface Abstraite qui fournit les propriétés d'élément de voie.
 * @author Jaguar
 *
 */
public abstract interface WayElement {
	
	/**
	 * 
	 * @param wayElement
	 * L'élément à tester.
	 * @return 
	 * Vrai si le WayElement courant est connecté au WayElement donné en paramètre.
	 * <p>Non sinon.</p>
	 */
	public abstract boolean isJoinedTo(WayElement wayElement);
	
	/**
	 * 
	 * @return
	 * La taille du WayElement
	 */
	public abstract int getLength();

}
