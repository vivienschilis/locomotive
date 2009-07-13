/**
 * 
 */
package sensors;

import wayelement.WayElement;

/**
 * Interface abstraite définissant un sujet observable selon
 * <p>
 * une caratéristique. Tous les sujets observables doivent
 * </p>
 * <p>
 * dériver de cette interface.
 * </p>
 * 
 * @uml.dependency supplier="sensors.Sensor" stereotypes="Standard::Call"
 */
public abstract interface SensorListenable {

	/**
	 * Notifie tous les {@link Sensor} observant le sujet courant
	 * <p>
	 * de la survenue d'un évènement.
	 * </p>
	 */
	public abstract void notifyAllSensors();
	
	/**
	 * 
	 * @return
	 * Le {@link WayElement} o� se trouve le capteur.
	 */
	public abstract WayElement getLocation();

}
