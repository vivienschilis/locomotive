/**
 * 
 */
package semaphores;

import mobileElements.MobileElement;

public abstract class Semaphore {
	
	public abstract void lock();
	public abstract void unlock();
	public abstract boolean isLocked();

	public abstract void executeAlgorithm(MobileElement mobileElement);
	
}
