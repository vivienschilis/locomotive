package mobileElements;
/**
 * 
 */


public class Locomotive extends MotorizedSubElement {

	/**
	 * Constructeur d'une locomotive
	 * @param orientation
	 */
	protected Locomotive(int orientation){
		// une locomotive est un �l�ment motoris� de longueur 1 et ayant une attache
		super(1, 1, orientation);
		// selon le placement de la locomotive, la vitesse de base varie
		if(orientation == this.FORWARD){
			// si la locomotive est � l'avant, elle est lanc�e � pleine vitesse
			this.speed = MobileElement.MAX_SPEED;
		}else{
			// si elle est � l'arri�re, sa vitesse est minimale (nulle)
			this.speed = MobileElement.MIN_SPEED;
		}
	}	

}
