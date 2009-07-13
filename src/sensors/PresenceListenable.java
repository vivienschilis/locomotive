/**
 * 
 */
package sensors;

import java.util.Vector;

import wayelement.Section;

/**
 * Interface permettant à une classe d'acquérir les propriétés
 * <p>
 * de sujet observable par un {@link PresenceSensor} .
 * </p>
 * 
 * @uml.dependency supplier="events.PresenceEvent"
 */
public interface PresenceListenable extends SensorListenable {

	/**
	 * @return Returns the presenceSensors.
	 * @uml.property name="presenceSensors"
	 * @uml.associationEnd readOnly="true" multiplicity="(0 -1)" ordering="true"
	 *                     container="java.util.Vector"
	 *                     inverse="presenceListenable:sensors.PresenceSensor"
	 */
	public Vector<PresenceSensor> getPresenceSensors();

	/**
	 * Attache un {@link PresenceSensor} au sujet observé.
	 * 
	 * @param presenceSensor
	 *            Le {@link PresenceSensor} à attacher.
	 */
	public abstract void attach(PresenceSensor presenceSensor);

	/**
	 * Détache un {@link PresenceSensor} au sujet observé.
	 * 
	 * @param presenceSensor
	 *            Le {@link PresenceSensor} à détacher.
	 */
	public abstract void detach(PresenceSensor presenceSensor);

	/**
	 * Attache un ensemble de {@link PresenceSensor} au sujet observé.
	 * 
	 * @param presenceSensors
	 *            un ensemble de {@link PresenceSensor} .
	 */
	public abstract void attach(Vector<PresenceSensor> presenceSensors);

	/**
	 * Détache un ensemble de {@link PresenceSensor} du sujet observé.
	 * 
	 * @param presenceSensors
	 *            un ensemble de {@link PresenceSensor}.
	 */
	public abstract void detach(Vector<PresenceSensor> presenceSensors);

}
