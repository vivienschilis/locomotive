/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Etat Vert d'un feu tricolore
 */
public class TrafficLight3ColorsStateGreen extends TrafficLight3ColorsState {
	
	public TrafficLight3ColorsStateGreen(TrafficLight3Colors trafficLight) {
		this.trafficLight=trafficLight;
	}
	
	public void lock() {
		// le feu passe a l'orange
		trafficLight.setState(new TrafficLight3ColorsStateOrange(trafficLight));
	}
	
	public void unlock() {
		// ne fait rien
	}
	
	
	public boolean isLocked() {
		// le semaphore n'est pas verouillé
		return false;
	}
	
	public void executeAlgorithm (MobileElement mobileElement) {
		if(mobileElement.getSpeed() == MobileElement.MIN_SPEED){
			mobileElement.setSpeed(MobileElement.MAX_SPEED);
		}
	}

}
