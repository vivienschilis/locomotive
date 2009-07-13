package controllers;

import static org.junit.Assert.*;

import java.util.Vector;

import mobileElements.BasicTrainFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import semaphores.Semaphore;
import semaphores.TrafficLight2Colors;
import semaphores.TrafficLight3Colors;

import wayelement.RailSwitchRoad;
import wayelement.RailTrack;
import wayelement.Way;
import wayelement.WayElementFactory;

public class ControlSystemTest {

	Vector<RailTrack> ram = new Vector<RailTrack>();
	Vector<RailTrack> rav = new Vector<RailTrack>();
	RailSwitchRoad rsr;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testRegulate() throws ControlSystemException {

		WayElementFactory f = new WayElementFactory();
		BasicTrainFactory b = new BasicTrainFactory();
		Vector<Semaphore> s = new Vector<Semaphore>();
		for(int i =0; i< 3;i++){
			rav.add(f.getRailsWithSimpleJunctions(1).firstElement());
			ram.add(f.getRailsWithSimpleJunctions(1).firstElement());
			rav.get(i).putSemaphore(new TrafficLight2Colors(), Way.WAY_RL);
			rav.get(i).setId("Aval " + String.valueOf(i));
			ram.get(i).putSemaphore(new TrafficLight2Colors(), Way.WAY_LR);
			ram.get(i).setId("Amont " + String.valueOf(i));
			s.add(rav.get(i).getSemaphore(Way.WAY_RL));
			s.add(ram.get(i).getSemaphore(Way.WAY_LR));
		}
		
		rsr = f.setSwitchRoad(ram,rav,2);
		for(int i =0; i< 3;i++){
			rav.get(i).setId("Aval " + String.valueOf(i));
			ram.get(i).setId("Amont " + String.valueOf(i));
		}	
		Vector<RailSwitchRoad> rsrs = new Vector<RailSwitchRoad>();
		rsrs.add(rsr);
		ControlSystem cs = new ControlSystem(rsrs,s);
		for(int i = 0; i<3;i++){
			ram.get(i).getSection(3, Way.WAY_LR).attach(cs);
			rav.get(i).getSection(3, Way.WAY_RL).attach(cs);
		}
		cs.setRegulationStrategy(new BasicRegulationRSRStrategyWithPresenceSensors());
		
		rav.get(2).getSection(3, Way.WAY_RL).addMobileElement(b.createMobileElement(1, 2));
		ram.get(1).getSection(3, Way.WAY_LR).addMobileElement(b.createMobileElement(1, 2));
		ram.get(2).getSection(3, Way.WAY_LR).addMobileElement(b.createMobileElement(1, 2));
		//ram.get(0).getSection(3, Way.WAY_LR).addMobileElement(b.createMobileElement(1, 2));	
		printRSRStates();
		printSemaphoresStates();
		cs.regulate();
		printRSRStates();
		printSemaphoresStates();		
		cs.regulate();
		printRSRStates();
		printSemaphoresStates();
	}

	private void printSemaphoresStates(){
		System.out.println("");
		System.out.println("Semaphores States :");
		for(int i = 0; i<3;i++){
			System.out.println("Semaphore amont n°"
					+ i
					+ " locked : " + ram.get(i).getSemaphore(Way.WAY_LR).isLocked());
			
			System.out.println("Semaphore aval n°"
					+ i
					+ " locked : " + rav.get(i).getSemaphore(Way.WAY_RL).isLocked());
		}
		System.out.println("");
	}
	
	private void printRSRStates(){
		System.out.println("");
		System.out.println("RSR States :");
		String q;
		for(int i = 0; i<3;i++){
			
			if(rsr.linkedTo(ram.get(i))==null){
				q = null;
			}else {
				q = rsr.linkedTo(ram.get(i)).getId();
			}
			System.out.println("Rail n°"
					+ ram.get(i).getId()
					+" linked to Rail n°" 
					+q);
		}
		System.out.println("");
	}
}
