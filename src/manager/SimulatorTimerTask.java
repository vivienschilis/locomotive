package manager;

import java.util.TimerTask;

/**
 * TimerTask utilis�e par la classe Simulator
 */
public class SimulatorTimerTask extends TimerTask {
	
	private Simulator hisSimulator;
	
	SimulatorTimerTask(Simulator simulator){
		hisSimulator = simulator;
	}
	
	@Override
	public void run() {
		hisSimulator.nextStep();
	}

}
