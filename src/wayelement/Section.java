/**
 * 
 */
package wayelement;

import java.util.Iterator;
import java.util.Vector;

import events.PresenceEvent;

import mobileElements.MobileElement;
import sensors.PresenceListenable;
import sensors.PresenceSensor;
import semaphores.*;


/** 
 * @author jaguar
 */
public class Section implements WayElement, PresenceListenable{

	/** 
	 * @uml.property name="mobileElements"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="currentHeadingSection:mobileElements.MobileElement"
	 */

	private Vector<MobileElement>  mobileElements;
	private Vector<PresenceSensor> presenceSensors;
	
	private Semaphore semaphore;
	
	private RailTrack railTrack;
	
	public Section(){
		mobileElements = new Vector<MobileElement>();
		presenceSensors = new Vector<PresenceSensor>();
	}
	
	public Section(RailTrack railTrack){
		this.railTrack = railTrack;
		mobileElements = new Vector<MobileElement>();
		presenceSensors = new Vector<PresenceSensor>();	
	}

	/** 
	 * Getter of the property <tt>mobileElements</tt>
	 * @return  Returns the mobileElements.
	 * @uml.property  name="mobileElements"
	 */
	public Vector<MobileElement>  getMobileElements() {
		return mobileElements;
	}

	/** 
	 * Setter of the property <tt>mobileElements</tt>
	 * @param mobileElements  The mobileElements to set.
	 * @uml.property  name="mobileElements"
	 */
	public void setMobileElements(Vector<MobileElement> mobileElements) {
		this.mobileElements = mobileElements;
	}

	@Override
	public void attach(PresenceSensor presenceSensor) {
		presenceSensors.add(presenceSensor);
		presenceSensor.addWatchedItem(this);
	}

	@Override
	public void attach(Vector<PresenceSensor> presenceSensors) {
		this.presenceSensors.addAll(presenceSensors);
		Iterator<PresenceSensor> it = presenceSensors.iterator();
		while(it.hasNext()){
			it.next().addWatchedItem(this);
		}
	}

	@Override
	public void detach(PresenceSensor presenceSensor) {
		presenceSensors.removeElement(presenceSensor);
		presenceSensor.removeWatchedItem(this);
	}

	@Override
	public Vector<PresenceSensor> getPresenceSensors() {
		return presenceSensors;
	}

	@Override
	public void notifyAllSensors() {
		Iterator<PresenceSensor> it = presenceSensors.iterator();
		PresenceEvent pe = new PresenceEvent(this);
		while(it.hasNext()){
			it.next().presenceDetected(pe);
		}
	}

	@Override
	public void detach(Vector<PresenceSensor> presenceSensors) {
		this.presenceSensors.removeAll(presenceSensors);
		Iterator<PresenceSensor> it = presenceSensors.iterator();
		while(it.hasNext()){
			it.next().removeWatchedItem(this);
		}
	}

	/**
	 * Ajoute un {@link MobileElement} sur la Section
	 */
	public void addMobileElement(MobileElement m){
		mobileElements.add(m);
		notifyAllSensors();
	}
	
	public void removeMobileElement(MobileElement m){
		mobileElements.remove(m);
	}	

	public RailTrack getRailTrack() {
		return railTrack;
	}

	public void setRailTrack(RailTrack railTrack) {
		this.railTrack = railTrack;
	}
	
	public boolean hasCollision(){
		return mobileElements.size()>1;
	}
	
	public Section getNextSection(int way) throws OutOfWayException{
		return railTrack.getNextSection(this, way);
	}

	@Override
	public boolean isJoinedTo(WayElement wayElement) {
		return railTrack.equals(wayElement);
	}

	@Override
	public final int getLength() {
		return 1;
	}

	@Override
	public WayElement getLocation() {
		return this;
	}
	
	
	public Semaphore getSemaphore(int way) {
		if( railTrack.getLastSection(way).equals(this) )
			return semaphore;
		else 
			return null;
	}
	
	public boolean isSemaphorePresent (int way) {
		if(getSemaphore(way) != null) return true;
		else return false;
	}
	
	public void setSemaphore(Semaphore sem) {
		semaphore = sem;
	}
	
	public int getId(){
		return railTrack.getSectionId(this);
	}
}
