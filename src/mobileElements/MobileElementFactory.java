package mobileElements;
/**
 * 
 */


public abstract class MobileElementFactory {

		
			
	/**
	 * M�thode abstraite permettant de cr�er un �l�ment mobile
	 * @param nbMotorizedElements
	 * @param nbTowedElements
	 * @return
	 */
	public abstract MobileElement createMobileElement(int nbMotorizedElements, int nbTowedElements);
			
		

}
