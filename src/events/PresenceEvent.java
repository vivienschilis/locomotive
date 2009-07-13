/**
 * 
 */
package events;

import sensors.PresenceListenable;

/** 
 * Cette classe est un évènement qui permet la notification
 * <p>de la présence d'un {@link PresenceListenable} à un {@link PresenceSensor}.
 */
public class PresenceEvent extends Event {

	/** 
	 * Elément emetteur de l'évènement.
	 * @uml.property name="source" readOnly="true"
	 */
	private PresenceListenable source;

	/** 
	 * Getter of the property <tt>source</tt>
	 * @return  Returns the source.
	 * @uml.property  name="source"
	 */
	public PresenceListenable getSource() {
		return source;
	}

		
			
			/** 
			 * Construit un PresenceEvent à partir de son {@link PresenceListenable} source.
			 * @param source Le {@link PresenceListenable} source de l'évènement.
			 */
			public PresenceEvent(PresenceListenable source){
				this.source = source;
			}

}
