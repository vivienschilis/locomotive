/**
 * 
 */
package wayelement;

import java.util.Vector;

import java.util.Iterator;

import semaphores.*;

/** 
 * @author jaguar
 */
public class RailTrack implements WayElement{

	String id;

	public void setId(String myId) {
		id = myId;
	}

	public String getId() {
		return id;
	}

	/**
	 * @uml.property  name="junctionElement"
	 * @uml.associationEnd  inverse="railTrack:wayelement.JunctionElement"
	 */
	private JunctionElement junctionElement[];
	private static final int length = 4;

	/**
	 * method
	 */
	protected RailTrack() {
		junctionElement = new JunctionElement[2];
		sections = new Vector<Section>();

		for(int i=0; i<length; i++) {
			Section s = new Section();
			s.setRailTrack(this);
			sections.addElement(s);
		}
	}
	/**
	 * Getter of the property <tt>junctionElement</tt>
	 * @return  Returns the junctionElement.
	 * @uml.property  name="junctionElement"
	 */
	public JunctionElement getJunctionElement(int way) {
		return junctionElement[way];
	}

	public JunctionElement[] getJunctionElements() {
		return junctionElement;
	}
	
	/**
	 * Setter of the property <tt>junctionElement</tt>
	 * @param junctionElement  The junctionElement to set.
	 * @uml.property  name="junctionElement"
	 */
	public void setJunctionElement(JunctionElement junctionElement, int way) {
		this.junctionElement[way] = junctionElement;
	}

	public void setJunctionElement(JunctionElement[] junctionElement) {
		this.junctionElement = junctionElement;
	}
	
	/**
	 * @uml.property  name="sections"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" aggregation="composite" inverse="railTrack:wayelement.Section"
	 */
	private Vector<Section> sections;

	/**
	 * Getter of the property <tt>sections</tt>
	 * @return  Returns the sections.
	 * @uml.property  name="sections"
	 */
	public Vector getSections() {
		return sections;
	}

	/**
	 * Setter of the property <tt>sections</tt>
	 * @param sections  The sections to set.
	 * @uml.property  name="sections"
	 */
	public void setSections(Vector<Section> sections) {
		this.sections = sections;
	}


	public Section getNextSection(Section section, int way) throws OutOfWayException {
		
		int idx = sections.indexOf(section);

		if(way == Way.WAY_LR) {
			if(idx==sections.size()-1) {
				JunctionElement j = getJunctionElement(way);
				if(j==null)  throw new OutOfWayException("Out of rail track " + getId() + " (no junction)");

				RailTrack nextRail = j.getNextRail(this);
				if(nextRail==null) return null;

				return nextRail.getFirstSection(way);
			}
			else{
				return sections.elementAt(idx+1);
			}
		}
		else {
				if(idx==0) {
					JunctionElement j = getJunctionElement(way);
					if(j==null)  throw new OutOfWayException("Out of rail track " + getId() + " (no junction)");

					RailTrack nextRail = j.getNextRail(this);
					if(nextRail==null) return null;
					return nextRail.getFirstSection(way);
				}
				else{
					return sections.elementAt(idx-1);
				}
		}	
	}

	public Section getSection(int nbSection, int way) {
		if(nbSection > sections.size() || nbSection < 0) return null;

		if(way == Way.WAY_LR)
			return sections.elementAt(nbSection);
		else
			return sections.elementAt(sections.size()-1-nbSection);
	}

	public Section getFirstSection(int way) {
		if(way == Way.WAY_LR) {
			return sections.elementAt(0);
		}
		else {
			return sections.elementAt(sections.size()-1);
		}
	}
	
	public Section getLastSection(int way) {
		return getSection(sections.size()-1, way);
	}
	

	@Override
	public boolean isJoinedTo(WayElement wayElement) {
		return false;
	}

	@Override
	public final int getLength() {
		return length;
	}
	
	public void putSemaphore(Semaphore semaphore, int way) {
		getLastSection(way).setSemaphore(semaphore);
	}
	
	public Semaphore getSemaphore(int way) {
		return getLastSection(way).getSemaphore(way);
	}
	
	public int getSectionId(Section section){
		return sections.indexOf( section );
	}
	
	public boolean hasCollision(){
		Iterator<Section> it = sections.iterator(); 
		while(it.hasNext()){
			if(it.next().hasCollision()) return true;
		}
		return false;
	}
}
