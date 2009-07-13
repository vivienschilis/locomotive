/**
 * 
 */
package sensors;

/** 
 * Interface abstraite représentant un capteur. Tous les capteurs doivent dériver de cette interface.
 */
public abstract interface Sensor {
		
	/**
	 * Ajoute un �l�ment observ� � ce capteur
	 * @param s
	 * l'�l�ment � observer
	 */
	public void addWatchedItem(SensorListenable s);
	
	/**
	 * Retire un �l�ment observ� � ce capteur
	 * @param s
	 * l'�l�ment � retirer
	 */	
	public void removeWatchedItem(SensorListenable s);	
}
