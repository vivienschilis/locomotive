package sensors;

import java.util.Vector;
/**
 * Classe permettant la collection et le dispatching d'�l�ments observables par 
 * des capteurs quelconques {@link SensorListenable} ayant �mis une notification
 *
 */
public class SensorListenableAdvancedCollection {
	//liste des PresenceListenable ayant �mis une notification
	private Vector<PresenceListenable> presenceListenables = new Vector<PresenceListenable>();
	//liste des SensorListenable ayant �mis une notification
	private Vector<SensorListenable> sensorListenables = new Vector<SensorListenable>();	
	
	public Vector<PresenceListenable> getPresenceListenables() {
		return presenceListenables;
	}

	public void setPresenceListenables(
			Vector<PresenceListenable> presenceListenables) {
		this.presenceListenables = presenceListenables;
		sensorListenables.addAll(presenceListenables);
	}
	
	public void addPresenceListenable(PresenceListenable p){
		presenceListenables.add(p);
		sensorListenables.add(p);
	}

	public Vector<SensorListenable> getSensorListenables() {
		return sensorListenables;
	}

	public void setSensorListenables(Vector<SensorListenable> sensorListenables) {
		this.sensorListenables = sensorListenables;
	}
	
	public void addSensorListenable(SensorListenable p){
		sensorListenables.add(p);
	}
}
