/**
 * Cette classe implement un jonction de type Butée.
 * Elle ne peut etre connecté que d'un coté
 */
package wayelement;

/** 
 * @author jaguar
 */
public class EndStop extends JunctionElement {

	private RailTrack railTrack;

	public EndStop(RailTrack rail, int way) {
		railTrack = rail;		
	}

	// Une jonction n'a pas de rail suivant
	@Override
	public RailTrack getNextRail(RailTrack sender) {
		return null;
	}

	@Override
	public boolean isJoinedTo(WayElement wayElement) {
		
		return railTrack.equals(wayElement);
	}
}
