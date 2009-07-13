package manager;

import java.util.Iterator;
import java.util.Vector;
import controllers.ControlSystem;
import controllers.ControlSystemException;
import wayelement.RailTrack;
import wayelement.Section;
import mobileElements.DerailmentException;
import mobileElements.MobileElement;

/**
 * Classe permettant de représenter un réseau et
 * d'effectuer dessus des actions globales
 */
public class Network {
	private Vector<MobileElement> hisMobileElements;
	private Vector<RailTrack> hisRailTracks;
	private Vector<ControlSystem> hisControlSystems;
	
	Network(){
		hisRailTracks = new Vector<RailTrack>();
		hisMobileElements = new Vector<MobileElement>();
		hisControlSystems = new Vector<ControlSystem>();
	}
	
	public void addRailTracks( Vector<RailTrack> rt){
		hisRailTracks.addAll(rt);
	}
	
	public void addMobileElement( MobileElement mb ){
		hisMobileElements.add(mb);
	}
	
	public void addControlSystem( ControlSystem cs ){
		hisControlSystems.add(cs);
	}
	
	public Vector<MobileElement> getMobileElements(){
		return hisMobileElements;
	}
	
	/**
	 * Déplace tous les éléments mobiles du réseau selon leur vitesse
	 */
	public void moveMobileElements(){
		Vector<MobileElement> mbs = hisMobileElements;
		Iterator<MobileElement> it = mbs.iterator();
		while(it.hasNext()){
			try {
				it.next().move();
			} catch (DerailmentException e) {
				System.out.println( e.getMessage() );
			}
		}
	}

	/**
	 * Fait réagir selon leur stratégie tous les éléments de régulation
	 */
	public void regulate(){
		Iterator<ControlSystem> it = hisControlSystems.iterator();
		while(it.hasNext()){
			try {
				it.next().regulate();
			} catch (ControlSystemException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Détecte les collisions entre éléments mobiles sur le réseau
	 */
	public void detectCollisions(){
		Vector<Section> occupiedSections = new Vector<Section>();
		
		Iterator<MobileElement> it = hisMobileElements.iterator();
		Iterator<Section> it2 = null;
		Section s = null;
		while(it.hasNext()){
			it2 = it.next().getOccupiedSections().iterator();
			while(it2.hasNext()){
				s = it2.next();
				if( occupiedSections.contains(s) ){
					System.out.println("Collision on rail track " + s.getRailTrack().getId() + ", on section " + s.getId());
				}
				occupiedSections.add(s);
			}
		}
	}
}
