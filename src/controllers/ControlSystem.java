/**
 */
package controllers;

import java.util.Vector;
import wayelement.RailSwitchRoad;
import wayelement.Section;
import semaphores.Semaphore;
import sensors.PresenceSensor;
import sensors.Sensor;
import sensors.SensorListenable;
import sensors.SensorListenableAdvancedCollection;
import events.PresenceEvent;

/**
 * Repr�sente un �l�ment de r�gulation de traffic. Cette classe agit comme un capteur.
 */
public class ControlSystem implements PresenceSensor{

	/**
	 * @uml.property name="railSwitchRoads"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="controlSystem:wayelement.RailSwitchRoad"
	 */
	private Vector<RailSwitchRoad> railSwitchRoads;//les aiguillages control�s
	private SensorListenableAdvancedCollection slac;/*une collection d'objets observ�s ayant
	�mis une notification
	*/
	
	
	/**
	 * @uml.property name="regulationStrategy"
	 * @uml.associationEnd multiplicity="(1 1)" aggregation="composite"
	 *                     inverse="controlSystem:controllers.RegulationStrategy"
	 */
	private RegulationStrategy regulationStrategy = null;//strat�gie de r�gulation
	
	private Vector<SensorListenable> watchedItems;//objets surveill�s.

	/**
	 * Constructeur
	 * @param railSwitchRoads
	 * Ses aiguillages � surveiller
	 * @param semaphores
	 * Sess s�maphores
	 */
	public ControlSystem(Vector<RailSwitchRoad> railSwitchRoads, Vector<Semaphore> semaphores){
		this.railSwitchRoads = railSwitchRoads;
		this.semaphores = semaphores;
		watchedItems = new Vector<SensorListenable>();
		slac = new SensorListenableAdvancedCollection();
	}
	
	/**
	 * Getter of the property <tt>railSwitchRoad</tt>
	 * 
	 * @return Returns the railSwitchRoad.
	 * @uml.property name="railSwitchRoads"
	 */
	public Vector<RailSwitchRoad> getRailSwitchRoads() {
		return railSwitchRoads;
	}

	/**
	 * Getter of the property <tt>regulationStrategy</tt>
	 * 
	 * @return Returns the regulationStrategy.
	 * @uml.property name="regulationStrategy"
	 */
	public RegulationStrategy getRegulationStrategy() {
		return regulationStrategy;
	}

	/**
	 * Setter of the property <tt>regulationStrategy</tt>
	 * 
	 * @param regulationStrategy
	 *            The regulationStrategy to set.
	 * @uml.property name="regulationStrategy"
	 */
	public void setRegulationStrategy(RegulationStrategy regulationStrategy) {
		this.regulationStrategy = regulationStrategy;
		regulationStrategy.setRailSwitchRoads(railSwitchRoads);
		regulationStrategy.setSemaphores(semaphores);
		regulationStrategy.setWatchedItems(watchedItems);
	}

	/**
	 * @uml.property name="semaphores"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true"
	 *                     inverse="controlSystem:semaphores.Semaphore"
	 */
	private Vector<Semaphore> semaphores;

	/**
	 * Getter of the property <tt>semaphores</tt>
	 * 
	 * @return Returns the semaphore.
	 * @uml.property name="semaphores"
	 */
	public Vector<Semaphore> getSemaphores() {
		return semaphores;
	}

	@Override
	public void presenceDetected(PresenceEvent presenceEvent) {
		slac.addPresenceListenable(presenceEvent.getSource());
	}
	
	/**
	 * Effectue la r�gulation
	 * @throws ControlSystemException
	 * si le {@link ControlSystem} est mal initialis�.
	 */
	public void regulate() throws ControlSystemException{
		if(regulationStrategy == null)
			throw new ControlSystemException("regulationStrategy not initialized");
		regulationStrategy.regulate(slac);
		slac = new SensorListenableAdvancedCollection();//r�initialisation de la collection
	}
	
	@Override
	public void addWatchedItem(SensorListenable s){
		watchedItems.add(s);
	}

	@Override
	public void removeWatchedItem(SensorListenable s) {
		watchedItems.removeElement(s);
		
	}
}
