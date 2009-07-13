/**
 * 
 */
package sensors;

import events.PresenceEvent;

/** 
 * Interface permettant à une classe d'avoir les propriétés de capteur de présence.
 */
public interface PresenceSensor extends Sensor {

		
			
			/** 
			 * Notification au capteur de la détection d'une présence.
			 * @param presenceEvent L'évènement notifiant la présence d'un {@link PresenceListenable} .
			 */
			public abstract void presenceDetected(PresenceEvent presenceEvent);
			
		

}
