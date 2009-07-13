/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * classe Abstraite des Feu de circulation
 */
public abstract class TrafficLight extends Semaphore {
	
	// Etat dans le quel se trouve le feu
	protected TrafficLightState state;
	

	@Override
	public void lock() {
		state.lock();
	}
	
	@Override
	public void unlock() {
		state.unlock();
	}
	
	@Override
	public boolean isLocked() {
		return state.isLocked ();
	}
	
	// Agir sur le mobileElement apres changement d'etat
	@Override
	public void executeAlgorithm(MobileElement mobileElement) {
		state.executeAlgorithm(mobileElement);
	}
}
