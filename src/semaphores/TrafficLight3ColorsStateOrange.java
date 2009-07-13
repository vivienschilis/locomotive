/**
 * 
 */
package semaphores;
import mobileElements.MobileElement;

/** 
 * Etat Orange d'un feux tricolore
 */
public class TrafficLight3ColorsStateOrange extends TrafficLight3ColorsState {

	public TrafficLight3ColorsStateOrange(TrafficLight3Colors trafficLight) {
		this.trafficLight=trafficLight;
	}
	
	public void lock() {
		// le feu passe au rouge
		trafficLight.setState(new TrafficLight3ColorsStateRed(trafficLight));
	}
	
	public void unlock() {
		// il revient au vert
		trafficLight.setState(new TrafficLight3ColorsStateGreen(trafficLight));
	}

	public boolean isLocked() {
		return false;
	}
	
	public void executeAlgorithm (MobileElement mobileElement) {
		mobileElement.setSpeed(mobileElement.getSpeed()/2);
	}
}
