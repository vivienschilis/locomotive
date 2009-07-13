package mobileElements;

import wayelement.OutOfWayException;
import wayelement.RailTrack;
import wayelement.Section;
import wayelement.Way;

import java.util.Vector;

/**
 * Cette Classe repr√©sente un √©l√©ment pouvant se d√©placer sur des
 * {@link RailTrack}.
 */
public abstract class MobileElement {

	// bornes de la vitesse d'un ÈlÈment mobile
	public static int MAX_SPEED = 50;
	public static int MIN_SPEED = 0;

	/**
	 * Constructeur d'un ÈlÈment mobile
	 */
	public MobileElement() {
		motorizedSubElements = new Vector<MotorizedSubElement>();
		towedElements = new Vector<TowedElement>();
	}
	
	/**
	 * DÈplace l'ÈlÈment mobile selon sa vitesse
	 */ 
	public abstract void move() throws DerailmentException;

	/**
	 * Retourne la vitesse de l'√©l√©ment mobile en nombre de {@link Section} par
	 * top d'horloge
	 */
	public int getSpeed() {
		Vector mse = this.getMotorizedSubElements();
		int totalSpeed = 0;
		for (int i = 0; i < mse.size(); i++) {
			MotorizedSubElement currentMse = (MotorizedSubElement) mse.get(i);
			if (currentMse.getOrientation() == MotorizedSubElement.FORWARD) {
				totalSpeed += ((MotorizedSubElement) mse.get(i)).getSpeed();
			} else if (currentMse.getOrientation() == MotorizedSubElement.BACKWARD) {
				totalSpeed -= ((MotorizedSubElement) mse.get(i)).getSpeed();
			}
		}
		return totalSpeed;
	}

	/**
	 * Modifie la vitesse de l'ÔøΩlÔøΩment mobile
	 */
	public abstract void setSpeed(int speed);

	/**
	 * Retourne la longueur de l'√©l√©ment mobile en nombre de troncons
	 * {@link Section}.
	 */
	public int getLength() {

		return this.getMotorizedSubElements().size()
				+ this.getTowedElements().size();
	}

	/**
	 * @uml.property name="currentHeadingSection"
	 * @uml.associationEnd multiplicity="(1 1)"
	 *                     inverse="mobileElements:wayelement.Section"
	 */
	private Section currentHeadingSection = null;

	/**
	 * Getter of the property <tt>currentHeadingSection</tt>
	 * 
	 * @return Returns the currentHeadingSection.
	 * @uml.property name="currentHeadingSection"
	 */
	public Section getCurrentHeadingSection() {
		return currentHeadingSection;
	}

	/**
	 * Setter of the property <tt>currentHeadingSection</tt>
	 * 
	 * @param currentHeadingSection
	 *            The currentHeadingSection to set.
	 * @uml.property name="currentHeadingSection"
	 */
	public void setCurrentHeadingSection(Section currentHeadingSection) {
		if( this.currentHeadingSection!=null )
			this.currentHeadingSection.removeMobileElement(this);
		this.currentHeadingSection = currentHeadingSection;
		this.currentHeadingSection.addMobileElement(this);
	}

	/**
	 * @uml.property name="motorizedSubElements"
	 * @uml.associationEnd readOnly="true" multiplicity="(1 -1)" ordering="true"
	 *                     aggregation="composite"
	 *                     inverse="mobileElement:mobileElements.MotorizedSubElement"
	 */
	private Vector<MotorizedSubElement> motorizedSubElements;

	/**
	 * Getter of the property <tt>motorizedSubElements</tt>
	 * 
	 * @return Returns the motorizedSubElements.
	 * @uml.property name="motorizedSubElements"
	 */
	public Vector<MotorizedSubElement> getMotorizedSubElements() {
		return motorizedSubElements;
	}

	/**
	 * @uml.property name="towedElements"
	 * @uml.associationEnd readOnly="true" multiplicity="(0 -1)" ordering="true"
	 *                     aggregation="composite"
	 *                     inverse="mobileElement:mobileElements.TowedElement"
	 */
	private Vector<TowedElement> towedElements;

	/**
	 * Getter of the property <tt>towedElements</tt>
	 * 
	 * @return Returns the towedElements.
	 * @uml.property name="towedElements"
	 */
	public Vector<TowedElement> getTowedElements() {
		return towedElements;
	}
	
	/**
	 * Retruns sections occupied by the train.
	 * The first section is the section occupied by the head.
	 * 
	 */
	public Vector<Section> getOccupiedSections(){
		Vector<Section> sections = new Vector<Section>();
		Section s = currentHeadingSection;
		sections.add(s);
		int i = 1;
		while(i<getLength()){
			try {
				s = s.getNextSection(Way.inverseWay(way));
				sections.add( s );
			} catch (OutOfWayException e) {}
			i++;
		}
		return sections;
	}

	/**
	 * Sens de dÈplacement de l'ÈlÈment mobile sur le rÈseau
	 */
	private int way = 0;

	/**
	 * Retourne le sens de dÈplacement de l'ÈlÈment mobile
	 * @return way
	 */
	public int getWay() {
		return way;
	}
	
	/**
	 * Modifie le sens de dÈplacement de l'ÈlÈment mobile
	 * @param way
	 */
	public void setWay( int way ) {
		if(  this.way != way ){
			if(currentHeadingSection!=null)
				setCurrentHeadingSection( getOccupiedSections().lastElement() );
			this.way = way;
		}
	}
	
	/**
	 * Identifiant de l'ÈlÈment mobile
	 */
	private int id;
	
	/**
	 * Modifie l'identifiant de l'ÈlÈment mobile
	 * @param id
	 */
	public void setId(int id){
		this.id = id; 
	}
	
	/**
	 * Retourne l'identifiant de l'ÈlÈment mobile 
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
}
