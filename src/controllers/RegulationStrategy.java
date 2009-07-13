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
 * Classe abstraite représentant la stratégie de régulation de traffic
 * d'un {@link ControlSystem}
 */
public abstract class RegulationStrategy {
	
	protected Vector<Semaphore> semaphores;//sémaphores à controler
	protected Vector<RailSwitchRoad> railSwitchRoads;//aiguillages à controler
	protected Vector<SensorListenable> watchedItems;//objets observables à controler
	
	/**
	 * Construit une {@link RegulationStrategy}
	 * @param semaphores
	 * Ses sémaphores
	 * @param railSwitchRoads
	 * Ses aiguillages
	 * @param watchedItems
	 * Ses objets observés
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
	 * Régule le traffic à partir d'un ensemble d'éléments observables 
	 * par des capteurs quelconques.
	 * @param eventSensorsListenable
	 * ensemble d'éléments observables par des capteurs quelconques.
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
