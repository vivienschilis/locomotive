package mobileElements;
import wayelement.Section;

/**
 * 
 */


public abstract class MobileSubElement {
	/** 
	 * Taille en nombre de {@link Section}
	 * @uml.property name="length" readOnly="true"
	 */
	protected static int length;
	/**
	 * Nombre d'attaches du sous-�l�ment mobile
	 * @uml.property  name="nbAttach"
	 */
	protected static int nbAttach;
	
	/**
	 * Constructeur d'un sous �l�ment mobile
	 * @param length
	 * @param nbattach
	 */
	protected MobileSubElement(int length, int nbattach){
		this.length = length;
		this.nbAttach = nbattach;
	}
	
}
