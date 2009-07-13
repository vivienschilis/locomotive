package manager;

import java.util.Vector;

import semaphores.Semaphore;
import semaphores.TrafficLight2Colors;

import controllers.BasicRegulationRSRStrategyWithPresenceSensors;
import controllers.ControlSystem;

import mobileElements.BasicTrainFactory;
import mobileElements.Train;

import wayelement.OutOfWayException;
import wayelement.RailSwitchRoad;
import wayelement.RailTrack;
import wayelement.Section;
import wayelement.Way;
import wayelement.WayElementFactory;


/**
 * 
 * Un exemple de réseau
 *
 */
public class NetworkTest extends Network {
	
	NetworkTest(){
		
		super();
				
		Vector<RailTrack> railsIn = new Vector<RailTrack>();
		Vector<RailTrack> railsOut = new Vector<RailTrack>();
		
		WayElementFactory factory = new WayElementFactory();
		
		BasicTrainFactory factory2 = new BasicTrainFactory();
		
		Section section = null;
		
		RailSwitchRoad railSwitch = null;
		
		
		//Mise en place des rails
		
		Vector<RailTrack> railTracks0 = factory.getRailsWithSimpleJunctions(5);
		addRailTracks(railTracks0);
		factory.setEndStop(railTracks0.firstElement(), Way.WAY_RL);
		
		
		Vector<RailTrack> railTracks1 = factory.getRailsWithSimpleJunctions(2);
		addRailTracks(railTracks1);
		factory.setEndStop(railTracks1.firstElement(), Way.WAY_RL);
		Vector<RailTrack> railTracks2 = factory.getRailsWithSimpleJunctions(7);
		addRailTracks(railTracks2);
		
		Vector<RailTrack> railTracks3 = factory.getRailsWithSimpleJunctions(5);
		addRailTracks(railTracks3);
		
		// Aiguillage
		railsIn.add(railTracks0.lastElement());
		railsIn.add(railTracks1.lastElement());
		railsIn.add(railTracks2.lastElement());
		railsOut.add(railTracks2.firstElement());
		railsOut.add(railTracks3.firstElement());
		railSwitch = factory.setSwitchRoad( railsIn , railsOut , 2 );
		railSwitch.link( railTracks0.lastElement() , railTracks2.firstElement() );
		railSwitch.link( railTracks2.lastElement() , railTracks3.firstElement() );
		
		Vector<RailTrack> railTracks4 = factory.getRailsWithSimpleJunctions(5);
		addRailTracks(railTracks4);
		 factory.setEndStop(railTracks4.firstElement(), Way.WAY_RL);
		
		Vector<RailTrack> railTracks5 = factory.getRailsWithSimpleJunctions(6);
		addRailTracks(railTracks5);
		
		// Aiguillage
		railsIn.add(railTracks3.lastElement());
		railsIn.add(railTracks4.lastElement());
		railsOut.add(railTracks5.firstElement());
		factory.setSwitchRoad( railsIn , railsOut , 1 );		
		
		Vector<RailTrack> railTracks6 = factory.getRailsWithSimpleJunctions(4);
		addRailTracks(railTracks6);
		factory.setEndStop(railTracks6.elementAt(0), Way.WAY_RL);
		
		Vector<RailTrack> railTracks7 = factory.getRailsWithSimpleJunctions(6);
		addRailTracks(railTracks7);
		factory.setEndStop(railTracks7.lastElement(), Way.WAY_RL);
		
		Vector<RailTrack> railTracks8 = factory.getRailsWithSimpleJunctions(11);
		addRailTracks(railTracks8);
		factory.setEndStop(railTracks8.lastElement(), Way.WAY_RL);
		
		// Aiguillage
		railsIn.add(railTracks5.lastElement());
		railsIn.add(railTracks6.lastElement());
		railsOut.add(railTracks7.firstElement());
		railsOut.add(railTracks8.firstElement());
		factory.setSwitchRoad( railsIn , railsOut , 1 );
		
		
		//Mise en place des trains
		
		Train tr = factory2.createMobileElement(1, 1);
		tr.setId(1133);
		addMobileElement( tr );
		tr.setSpeed(1);
		tr.setWay( Way.WAY_LR );
		
		try {
			section = railTracks0.firstElement().getFirstSection(Way.WAY_LR).getNextSection(Way.WAY_LR);
		} catch (OutOfWayException e) {
			e.printStackTrace();
		}
		tr.setCurrentHeadingSection(section);
		
		Train tr2 = factory2.createMobileElement(1, 3);
		tr2.setId(2096);
		addMobileElement( tr2 );
		tr2.setSpeed(1);
		tr2.setWay( Way.WAY_LR );
		
		section = railTracks0.lastElement().getFirstSection(Way.WAY_LR);
		tr2.setCurrentHeadingSection(section);
		
		
		// Mise en place des sémaphores
		
		TrafficLight2Colors tl1 = new TrafficLight2Colors();
		railTracks0.lastElement().putSemaphore(tl1, Way.WAY_LR);
		
		TrafficLight2Colors tl2 = new TrafficLight2Colors();
		railTracks2.lastElement().putSemaphore(tl2, Way.WAY_LR);
		
		TrafficLight2Colors tl3 = new TrafficLight2Colors();
		railTracks2.firstElement().putSemaphore(tl3, Way.WAY_RL);
		
		TrafficLight2Colors tl4 = new TrafficLight2Colors();
		railTracks3.firstElement().putSemaphore(tl4, Way.WAY_RL);
		
		
		// Mise en place des capteurs
		
		Vector<Semaphore> sems = new Vector<Semaphore>();
		sems.add(tl1);
		sems.add(tl2);
		sems.add(tl3);
		sems.add(tl4);
		
		Vector<RailSwitchRoad> rsr = new Vector<RailSwitchRoad>();
		rsr.add( railSwitch );
		
		ControlSystem cs1 = new ControlSystem( rsr , sems  );
		addControlSystem( cs1 );
		railTracks0.lastElement().getFirstSection(Way.WAY_LR).attach( cs1 );
		railTracks2.lastElement().getFirstSection(Way.WAY_LR).attach( cs1 );
		railTracks2.firstElement().getLastSection(Way.WAY_LR).attach( cs1 );
		railTracks3.firstElement().getLastSection(Way.WAY_LR).attach( cs1 );
		cs1.setRegulationStrategy( new BasicRegulationRSRStrategyWithPresenceSensors() );
		
	}
	
}
