/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Etat Vert d'un feu bicolore
 */
public class TrafficLight2ColorsStateRed extends TrafficLight2ColorsState {


	public TrafficLight2ColorsStateRed(TrafficLight2Colors trafficLight) {
		this.trafficLight=trafficLight;
	}
	
	public void lock() {
		// le feu reste au rouge
	}
	
	public void unlock() {
		// le feu passe au vert
		trafficLight.setState(new TrafficLight2ColorsStateGreen(trafficLight));
	}

	public boolean isLocked() {
		return true;
	}
	
	public void executeAlgorithm (MobileElement mobileElement) {
		if(mobileElement.getSpeed()!= MobileElement.MIN_SPEED){
			mobileElement.setSpeed(MobileElement.MIN_SPEED);
		}
	}
}
