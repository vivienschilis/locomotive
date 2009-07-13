/**
 * 
 */
package wayelement;

/** 
 * @author jaguar
 */

public class SimpleJunction extends JunctionElement {

	/**
	 * method
	 */
	private final static int MAXRAILTRACKS = 2;
	private RailTrack[] railTracks;
	
	/*
	* Constructeur
	* @params RailTrack railIn Rail en aval de la jonction
	* @params RailTrack railOut Rail en amont de la jonction
	*/
	protected SimpleJunction (RailTrack railIn, RailTrack railOut) {
		railTracks = new RailTrack[MAXRAILTRACKS];
		railTracks[1] = railIn;
		railTracks[0] = railOut;
	}
	
	/*
	* Retourne le rail suivant
	* @param RailTrack sender rail qui demande le suivant
	* @return RailTrack rail opposé
	*/
	@Override
	public RailTrack getNextRail(RailTrack sender) throws OutOfWayException {
		if (railTracks[0]==sender){
			if (railTracks[1] == null) // sortie du chemin
				throw new OutOfWayException("Sortie du chemin apres la jonction "+getId());
			else
				return railTracks[1];
		}
		
		if (railTracks[1]==sender) {
			if (railTracks[0] == null) // sortie du chemin
				throw new OutOfWayException("Sortie du chemin apres la jonction "+getId());
			else
				return railTracks[0];
		}

		throw new OutOfWayException("Le rail d'entrée n'est pas connecté à la jonction "+getId());
	}

	@Override
	public boolean isJoinedTo(WayElement wayElement) {
		
		int i = 0;
		while(i<railTracks.length){
			if(railTracks[i++].equals(wayElement))
				return true;
		}
		return false;
	}
}
