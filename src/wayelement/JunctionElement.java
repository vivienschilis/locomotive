package wayelement;
/**
 * 
 */


public abstract class JunctionElement implements WayElement {


	String id;

	public void setId(String myId) {
		id = myId;
	}

	public String getId() {
		return id;
	}

	/**
	 * @uml.property  name="railTrack"
	 * @uml.associationEnd  multiplicity="(0 -1)" dimension="1" ordering="true" inverse="junctionElement:wayelement.RailTrack"
	 */
	private RailTrack[] railTracks = new wayelement.RailTrack[2];

	/**
	 * Getter of the property <tt>railTrack</tt>
	 * @return  Returns the railTracks.
	 * @uml.property  name="railTrack"
	 */
	public RailTrack[] getRailTrack() {
		return railTracks;
	}

	/**
	 * Setter of the property <tt>railTrack</tt>
	 * @param railTrack  The railTracks to set.
	 * @uml.property  name="railTrack"
	 */
	public void setRailTrack(RailTrack[] railTrack) {
		railTracks = railTrack;
	}


	public abstract RailTrack getNextRail(RailTrack sender) throws OutOfWayException;
	
	@Override
	public final int getLength() {
		return 0;
	}

	
}
