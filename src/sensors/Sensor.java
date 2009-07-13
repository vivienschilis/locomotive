/**
 * 
 */
package sensors;

/** 
 * Interface abstraite reprÃ©sentant un capteur. Tous les capteurs doivent dÃ©river de cette interface.
 */
public abstract interface Sensor {
		
	/**
	 * Ajoute un élément observé à ce capteur
	 * @param s
	 * l'élément à observer
	 */
	public void addWatchedItem(SensorListenable s);
	
	/**
	 * Retire un élément observé à ce capteur
	 * @param s
	 * l'élément à retirer
	 */	
	public void removeWatchedItem(SensorListenable s);	
}
