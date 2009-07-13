package mobileElements;
/**
 * 
 */


public abstract class MotorizedSubElement extends MobileSubElement {

	// Constantes de l'orientation du sous �l�ment motoris� dans un �l�ment mobile
	protected static int FORWARD = 0;
	protected static int BACKWARD = 1;
	
	/**
	 * Orientation du sous �l�ment motoris�
	 */
	private int orientation;
	
	/**
	 * Constructeur d'un sous �l�ment motoris�
	 * @param length
	 * @param nbattach
	 * @param orientation
	 */
	protected MotorizedSubElement(int length, int nbattach, int orientation) {
		super(length, nbattach);
		// si l'orientation sp�cifi�e est valide
		if(orientation== this.FORWARD || orientation == this.BACKWARD){
			this.orientation = orientation;
		}
	}

	/**
	 * @uml.property  name="speed"
	 */
	protected int speed;
	
	/**
	 * Getter of the property <tt>speed</tt>
	 * @return  Returns the speed.
	 * @uml.property  name="speed"
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setter of the property <tt>speed</tt>
	 * @param speed  The speed to set.
	 * @uml.property  name="speed"
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Retourne l'orientation du sous �l�ment motoris�
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}
	
}
