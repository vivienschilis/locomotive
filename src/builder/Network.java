package builder;

import wayelement.*;
import semaphores.*;

import java.util.Vector;


public class Network {
		
		
	public static void main(String[] args) {
		
	System.out.println("[BUILD start]");
		
		
		TrafficLight3Colors tr1 = new TrafficLight3Colors();
		
		if(tr1.isLocked()) System.out.println("isLocked"); else System.out.println("isNotLocked");
		tr1.lock();
		if(tr1.isLocked()) System.out.println("isLocked"); else System.out.println("isNotLocked");
		tr1.lock();
		if(tr1.isLocked()) System.out.println("isLocked"); else System.out.println("isNotLocked");
		tr1.unlock();
		if(tr1.isLocked()) System.out.println("isLocked"); else System.out.println("isNotLocked");
		
		
		/*
		Network ntk = new Network();
		WayElementFactory factory = new WayElementFactory();
				
		Vector<RailTrack> rails = factory.getRailsWithSimpleJunctions(6);
		Vector<RailTrack> rails2 = factory.getRailsWithSimpleJunctions(6);
		Vector<RailTrack> rails3 = factory.getRailsWithSimpleJunctions(6);
		Vector<RailTrack> rails4 = factory.getRailsWithSimpleJunctions(6);
		Vector<RailTrack> rails5 = factory.getRailsWithSimpleJunctions(6);

		//rails.elementAt(2).putSemaphore(new TrafficLight3Colors(), Way.WAY_LR);
		
		Vector<RailTrack> railsIn = new Vector<RailTrack> ();
		Vector<RailTrack> railsOut = new Vector<RailTrack> ();
		
		int[] wayIn = new int[1];
		int[] wayOut = new int[2];
		
		wayIn[0] = Way.WAY_LR;
		wayOut[1] = Way.WAY_LR;
		wayOut[0] = Way.WAY_RL;
		
		railsIn.addElement(rails.lastElement());
		railsIn.addElement(rails2.lastElement());
		railsOut.addElement(rails3.firstElement());
		railsOut.addElement(rails4.firstElement());
		railsOut.addElement(rails5.firstElement());
		//railsOut.addElement(rails2.lastElement());
		RailSwitchRoad switch1 = factory.setSwitchRoad(railsIn,railsOut, 2);
		
		switch1.link(rails1,rails4);
		switch1.link(rails2,rails5);
		
		System.out.println("[add BUTE]");
		
		//factory.setEndStop(rails.firstElement() ,Way.WAY_RL);		
		//factory.setEndStop(rails2.lastElement(),Way.WAY_LR);
		//factory.setEndStop(rails2.lastElement() ,Way.WAY_LR);		

		try {
			int way = Way.WAY_RL;
			Section curSection, prevSection;
			curSection = rails2.elementAt(0).getFirstSection(way);
			prevSection = curSection;
			while(curSection != null ) {
				//if(s.isSemaphorePresent(Way.WAY_RL)) System.out.println("SEMAPHORE sur");
				way = Way.getNewWay(prevSection.getRailTrack(), curSection.getRailTrack(), way);
				prevSection=curSection;
				curSection=curSection.getNextSection(way);

			}
		
		}catch(OutOfWayException e) {
			System.out.println(e.getMessage());
		}
				
		System.out.println("[BUILD end]");
		*/
	}
	
}
