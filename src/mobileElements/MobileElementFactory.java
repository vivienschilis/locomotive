package mobileElements;
/**
 * 
 */


public abstract class MobileElementFactory {

		
			
	/**
	 * Méthode abstraite permettant de créer un élément mobile
	 * @param nbMotorizedElements
	 * @param nbTowedElements
	 * @return
	 */
	public abstract MobileElement createMobileElement(int nbMotorizedElements, int nbTowedElements);
			
		

}
