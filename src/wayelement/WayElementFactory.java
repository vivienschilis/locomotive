package wayelement;

import java.util.Vector;
import wayelement.*;

public class WayElementFactory
{
	static int cpt=0;

	/**
	 * @param int nbRails Nombre de rails join par une simple jonction
	 * @return Vector<RailTrack> Vecteur de rail créé
	 */
	public Vector<RailTrack> getRailsWithSimpleJunctions(int nbRails) {

		Vector<RailTrack> rails = new Vector<RailTrack>();
		
		if( nbRails < 1 ) return rails;
		
		// Il y a au moins un rails de retourné;
		RailTrack firstRail = new RailTrack();
		firstRail.setId("rail-"+cpt); cpt++;
		rails.addElement(firstRail);
		
		for (int i=1; i < nbRails; i++) {
			
			// Nouveau rail, nouvelle jonction
			RailTrack r = new RailTrack();
			r.setId("rail-"+cpt); cpt++;
			// Ajout du rail à la liste retourné
			rails.addElement(r);
			
			// Jonction des rails
			SimpleJunction j = new SimpleJunction(rails.elementAt(i-1), rails.elementAt(i));
			j.setId("Jct"+(i-1));

			rails.elementAt(i-1).setJunctionElement(j,Way.WAY_LR);
			rails.elementAt(i).setJunctionElement(j,Way.WAY_RL);
						
		}
		return rails;
	}

	/**
	* Ajoute une butée a un rail dans le sens de way
	*/
	public boolean setEndStop(RailTrack rail, int way) {
		if(rail.getJunctionElement(way) != null) return false;
		else {
			EndStop j = new EndStop(rail, way);
			j.setId("j-end");
			rail.setJunctionElement(j,way);
		}
		return true;
	}
	
	/**
	* Ajoute un aiguillage entre des rails Aval et des rails Amont
	* @param Vector<RailTrack> railsIn Rails Aval
	* @param Vector<RailTrack> railsOut Rails Amont
	* @param int nbConnection Nombre de connection maximal en meme temps
	* @return RailSwitchRoad Jonction crée
	*/
	public RailSwitchRoad  setSwitchRoad (Vector<RailTrack> railsIn, Vector<RailTrack> railsOut, int nbConnections) {
		if( (railsIn==null || railsIn.size()==0) && (railsOut==null|| railsOut.size()==0) ){
			return null;
		}
		
		RailSwitchRoad j = new RailSwitchRoad(railsIn,railsOut, nbConnections);
		j.setId("switch");
		for(int i=0; i<railsIn.size(); i++) {
			railsIn.elementAt(i).setJunctionElement(j,Way.WAY_LR);	
		}

		
		for(int i=0; i<railsOut.size(); i++) {
			railsOut.elementAt(i).setJunctionElement(j,Way.WAY_RL);		
		}
		
		j.link(railsIn.firstElement(), railsOut.elementAt(0));
		
		return j;
	}
	
	/**
	* Ajoute un aiguillage entre des rails Aval et des rails Amont en préciser les sens sur lesquel connecté les rails a la jonction 
	* @param Vector<RailTrack> railsIn Rails Aval
	* @param 
	* @param Vector<RailTrack> railsOut Rails Amont
	* @param int nbConnection Nombre de connection maximal en meme temps
	* @return RailSwitchRoad Jonction crée
	*/
	public RailSwitchRoad setSwitchRoad (Vector<RailTrack> railsIn, Vector<RailTrack> railsOut, int[]wayIn, int[]wayOut, int nbConnections) {
		if( (railsIn==null || railsIn.size()==0) && (railsOut==null|| railsOut.size()==0) ){
			return null;
		}
		
		RailSwitchRoad j = new RailSwitchRoad(railsIn,railsOut, nbConnections);
		j.setId("switch");
		for(int i=0; i<railsIn.size(); i++) {
			railsIn.elementAt(i).setJunctionElement(j, wayIn[i]);	
		}

		
		for(int i=0; i<railsOut.size(); i++) {
			railsOut.elementAt(i).setJunctionElement(j, wayOut[i]);		
		}
		
		j.link(railsIn.firstElement(), railsOut.elementAt(0));
			
		return j;
	}
	
}

