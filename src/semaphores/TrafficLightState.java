/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Interface de l'etat d'un feu de circulation
 */
public interface TrafficLightState {
	
	
	public void lock();
	
	public void unlock();
	
	public boolean isLocked();

	public void executeAlgorithm (MobileElement mobileElement);
	
}
