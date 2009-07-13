/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Etat rouge d'un feux tricolore rouge
 */
public class TrafficLight3ColorsStateRed extends TrafficLight3ColorsState {

	public TrafficLight3ColorsStateRed(TrafficLight3Colors trafficLight) {
		this.trafficLight=trafficLight;
	}
	
	public void lock() {
		// ne Fait rien
	}
	
	public void unlock() {
		trafficLight.setState(new TrafficLight3ColorsStateGreen(trafficLight));
	}

	public boolean isLocked() {
		// Le feu rouge est bloquant
		return true;
	}
	
	public void executeAlgorithm (MobileElement mobileElement) {
		if(mobileElement.getSpeed()!= MobileElement.MIN_SPEED){
			mobileElement.setSpeed(MobileElement.MIN_SPEED);
		}
	}
}
