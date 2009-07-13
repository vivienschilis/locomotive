/**
 * 
 */
package semaphores;

import mobileElements.MobileElement;

/** 
 * Feu tricolore
 */
public class TrafficLight3Colors extends TrafficLight {

	// L'etat initial d'un feu est Vert
	public TrafficLight3Colors () {
		super();
		state = new TrafficLight3ColorsStateGreen(this);
	}
	
	// Modification de l'etat. Un feu TrafficLight2Colors ne pourra prendre comme état que des instance de TrafficLight3ColorsState
	public void setState(TrafficLight3ColorsState state){
		this.state=state;
	}
}
