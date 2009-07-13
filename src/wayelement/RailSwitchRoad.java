/**
 * 
 */
package wayelement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.Collection;

/** 
 * @author jaguar
 */
public class RailSwitchRoad extends JunctionElement {

	/** 
	 * @uml.property name="railTracksAmont"
	 * @uml.associationEnd multiplicity="(1 -1)" ordering="true" aggregation="composite" inverse="railSwitchRoad:wayelement.RailTrack"
	 */
	//private Vector railTracksAmont;

	private int nbConnections;
	private Vector<Vector<RailTrack>> allRailTracks;
	private Vector<RailTrack[]> linkedRails;

	protected RailSwitchRoad (Vector<RailTrack> railsIn, Vector<RailTrack> railsOut, int nbConnections) {
		linkedRails = new Vector<RailTrack[]>();
		allRailTracks = new Vector<Vector<RailTrack>>(Way.NB_MAX_WAY);
		allRailTracks.add(Way.WAY_LR, railsIn);
		allRailTracks.add(Way.WAY_RL, railsOut);
		this.nbConnections = nbConnections;
	}

	
	/**
	* Lie un rail aval avec un rail amont
	* @param RailTrack rail1
	* @param RailTrack rail2 
	* @return boolean retourn faux si la laison est impossible sinon vrai
	*/
	public boolean link (RailTrack rail1, RailTrack rail2) {

 		if( (allRailTracks.elementAt(Way.WAY_LR).contains(rail1) &&allRailTracks.elementAt(Way.WAY_RL).contains(rail2)) ||
			allRailTracks.elementAt(Way.WAY_LR).contains(rail2) && allRailTracks.elementAt(Way.WAY_RL).contains(rail1)) {

			RailTrack[] road;
			for(int i=0; i<linkedRails.size(); i++) {
				road = linkedRails.elementAt(i);
				if(road[0]==rail1 || road[0]==rail2 || road[1]==rail1 || road[1]==rail2) linkedRails.removeElementAt(i);
			}
			
			if(linkedRails.size() == nbConnections)
				linkedRails.removeElementAt(0);
			
			road = new RailTrack[2];
			road[0] = rail1;
			road[1] = rail2;
			linkedRails.addElement(road);
			return true;
		}
		return false;
	}


	/*
	* Retourne le rail suivant
	* @param RailTrack sender rail qui demande le suivant
	* @return RailTrack rail opposé
	*/
	@Override
	public RailTrack getNextRail(RailTrack sender)throws OutOfWayException {

		RailTrack[] road;
		for(int i=0; i<linkedRails.size(); i++) {
			road = linkedRails.elementAt(i);
			if(road[0]==sender) {
				if (road[1] == null)
					throw new OutOfWayException("Out of way after "+getId());
				else
					return road[1];
			}
			
			if(road[1]==sender) {
				if (road[0] == null)
					throw new OutOfWayException("Out of way after junction "+getId());
				else
					return road[0];
			}
		}
		
		throw new OutOfWayException("Rail in is not connected to junction "+getId());
	}


	@Override
	public boolean isJoinedTo(WayElement wayElement) {
		Iterator<RailTrack> it;
		Iterator<Vector<RailTrack>> itv = allRailTracks.iterator();
		while(itv.hasNext()){
			it = itv.next().iterator();
			while(it.hasNext()){
				if(it.next().equals(wayElement)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Vérifie si un rail est connecté
	 * @param rail
	 * @return
	 * Vrai si le rail est connecté.
	 * <p>Faux sinon ou si le rail ne fait pas parti de l'aiguillage</p>
	 * 
	 */
	public boolean isLinked(RailTrack rail){
		
		RailTrack[] road;
		for(int i=0; i<linkedRails.size(); i++) {
			road = linkedRails.elementAt(i);
			if(road[Way.WAY_LR]==rail || road[Way.WAY_RL]==rail) return true;
		}
		return false;

	}
	
	/**
	 * Lie un rail Amont (resp. Aval) à un rail quelconque Aval (resp. Amont).
	 * @param rail
	 * Le Rail à lier
	 */
	public void link(RailTrack rail){
	
		if( allRailTracks.elementAt(0).contains(rail) || allRailTracks.elementAt(1).contains(rail) ) {
			RailTrack[] road = new RailTrack[2];
			for(int i=0; i<linkedRails.size(); i++) {
				road = linkedRails.elementAt(i);
				if(road[0]==rail || road[1]==rail) linkedRails.removeElementAt(i);
			}
			
			if(allRailTracks.elementAt(0).contains(rail))
				road[0] = allRailTracks.elementAt(1).elementAt( ((int)Math.random()) % allRailTracks.elementAt(1).size() );
			else
				road[0] = allRailTracks.elementAt(0).elementAt( ((int)Math.random()) % allRailTracks.elementAt(0).size() );
			
			linkedRails.addElement(road);
		}
	}
	
	/**
	 * 
	 * @param rail
	 * @return
	 * Le RailTrack lié au rail donné en paramètre par l'aiguillage
	 */
	public RailTrack linkedTo(RailTrack rail){
		
		RailTrack[] road;
		for(int i=0; i<linkedRails.size(); i++) {
			road = linkedRails.elementAt(i);
			if(road[Way.WAY_LR]==rail) return road[Way.WAY_RL];
			else if(road[Way.WAY_RL]==rail) return road[Way.WAY_LR];
		}
		return null;
	}


	public Vector<Vector<RailTrack>> getAllRailTracks() {
		return allRailTracks;
	}


	public void setAllRailTracks(Vector<Vector<RailTrack>> allRailTracks) {
		this.allRailTracks = allRailTracks;
	}

	
	/**
	 * Retourn le Sens en parcourant rail1 puis rail2
	 * @param RailTrack rail1
	 * @param RailTrack rail2
	 * @return int Sens du parcourt
	 */
	public int getWay(RailTrack rail1, RailTrack rail2) {
		if (rail1.equals(rail2)) return Way.WAY_ERROR;
		
		if(allRailTracks.get(0).contains(rail1)){
			if(allRailTracks.get(1).contains(rail2)){
				return Way.WAY_LR;
			}
		}
		
		if(allRailTracks.get(1).contains(rail1)){
			if(allRailTracks.get(0).contains(rail2)){
				return Way.WAY_RL;
			}
		}		
		
		return Way.WAY_ERROR;
	}

	
	/**
	 * 
	 * @param rail
	 * un RailTrack
	 * @return
	 * L'ensemble des rails pouvant être connecté au rail donné en paramètre.
	 */
	public Vector<RailTrack> getPossibleConnexions(RailTrack rail){
		if(allRailTracks.elementAt(0).contains(rail))
			return allRailTracks.elementAt(1);
		if(allRailTracks.elementAt(1).contains(rail))
			return allRailTracks.elementAt(0);		
		return null;
	}

}
