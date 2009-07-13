package wayelement;
/**
 * Interface Abstraite qui fournit les propri�t�s d'�l�ment de voie.
 * @author Jaguar
 *
 */
public abstract interface WayElement {
	
	/**
	 * 
	 * @param wayElement
	 * L'�l�ment � tester.
	 * @return 
	 * Vrai si le WayElement courant est connect� au WayElement donn� en param�tre.
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
