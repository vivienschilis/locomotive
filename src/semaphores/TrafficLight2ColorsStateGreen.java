/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Etat Vert d'un feu bicolore
 */
public class TrafficLight2ColorsStateGreen extends TrafficLight2ColorsState {

	public TrafficLight2ColorsStateGreen(TrafficLight2Colors trafficLight) {
		this.trafficLight=trafficLight;
	}
	
	public void lock() {
		// le feu est vert, il passe au rouge
		trafficLight.setState(new TrafficLight2ColorsStateRed(trafficLight));
	}
	
	public void unlock() {
		// le feu reste au vert
	}
	
	
	public boolean isLocked() {
		return false;
	}
	
	public void executeAlgorithm (MobileElement mobileElement) {
		if(mobileElement.getSpeed() == MobileElement.MIN_SPEED){
			mobileElement.setSpeed(MobileElement.MAX_SPEED);
		}
	}

}
