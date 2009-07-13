/**
 * 
 */
package semaphores;

import mobileElements.MobileElement;


/*
* Feu bicolor
*/
public class TrafficLight2Colors extends TrafficLight {

	// L'etat initial d'un feu est Vert
	public TrafficLight2Colors () {
		super();
		state = new TrafficLight2ColorsStateGreen(this);
	}
	
	
	// Modification de l'etat. Un feu TrafficLight2Colors ne pourra prendre comme état que des instance de TrafficLight2ColorsState
	public void setState(TrafficLight2ColorsState state){
		this.state=state;
	}
}
