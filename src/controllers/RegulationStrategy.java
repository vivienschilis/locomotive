/**
 * 
 */
package controllers;

import java.util.Vector;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import semaphores.Semaphore;
import sensors.Sensor;
import sensors.SensorListenable;
import sensors.SensorListenableAdvancedCollection;
import wayelement.RailSwitchRoad;

/** 
 * Classe abstraite repr�sentant la strat�gie de r�gulation de traffic
 * d'un {@link ControlSystem}
 */
public abstract class RegulationStrategy {
	
	protected Vector<Semaphore> semaphores;//s�maphores � controler
	protected Vector<RailSwitchRoad> railSwitchRoads;//aiguillages � controler
	protected Vector<SensorListenable> watchedItems;//objets observables � controler
	
	/**
	 * Construit une {@link RegulationStrategy}
	 * @param semaphores
	 * Ses s�maphores
	 * @param railSwitchRoads
	 * Ses aiguillages
	 * @param watchedItems
	 * Ses objets observ�s
	 */
	public RegulationStrategy(Vector<Semaphore> semaphores, Vector<RailSwitchRoad> railSwitchRoads, Vector<SensorListenable> watchedItems) {
		this.railSwitchRoads = railSwitchRoads;
		this.semaphores = semaphores;
		this.watchedItems = watchedItems;
	}
	
	public RegulationStrategy(){
		super();
	}
	
	/**
	 * R�gule le traffic � partir d'un ensemble d'�l�ments observables 
	 * par des capteurs quelconques.
	 * @param eventSensorsListenable
	 * ensemble d'�l�ments observables par des capteurs quelconques.
	 */
	public abstract void regulate(SensorListenableAdvancedCollection eventSensorsListenable);


	public void setWatchedItems(Vector<SensorListenable> watchedItems) {
		this.watchedItems = watchedItems;
	}

	public void setSemaphores(Vector<Semaphore> semaphores) {
		this.semaphores = semaphores;
	}


	public void setRailSwitchRoads(Vector<RailSwitchRoad> railSwitchRoads) {
		this.railSwitchRoads = railSwitchRoads;
	}
}
