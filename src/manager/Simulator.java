package manager;

import java.util.Timer;

/**
 * Classe de simulation
 * Le simulateur est cadencé par une horloge.
 * A chaque top, les actions sont effectuées
 * sur le réseau et son affichage est mis à jour.
 */
public class Simulator {
	private Timer timer;
	private SimulatorTimerTask timerTask;
	private long rate;
	private Network hisNetwork;
	private int stepNumber;
	private NetworkDisplayer hisNetworkDisplayer;
	
	Simulator(){
		timer = new Timer();
		timerTask = new SimulatorTimerTask(this);
		rate = 200;
		stepNumber = 0;
		hisNetworkDisplayer = new ConsoleNetworkDisplayer();
	}
	
	/**
	 * Modifie la période du simulateur en ms
	 */
	public void setRate(long rate){
		this.rate = rate;
	}

	/**
	 * Indique au simulateur le réseau sur lequel il doit agir
	 */
	public void setNetwork(Network network){
		hisNetwork = network;
	}
	
	/**
	 * Démarre la simulation
	 */
	public void start(){
		hisNetworkDisplayer.display( hisNetwork );
		timer.schedule( timerTask , rate , rate );
	}
	
	/**
	 * Donne le nombre de tours écoulésdepuis le début de la simulation
	 */
	public int getStepNumber(){
		return stepNumber;
	}
	
	/**
	 * Incrémente le numéro de l'étape
	 */
	public void incStepNumber(){
		stepNumber++;
	}
	
	/**
	 * Fonction appelée à chaque top d'horloge.
	 * Effectue les actions sur le réseau et met à jour son affichage.
	 */
	public void nextStep(){
		incStepNumber();
		System.out.println("Step #" + getStepNumber());
		
		hisNetwork.moveMobileElements();
		hisNetwork.regulate();
		hisNetwork.detectCollisions();
		
		hisNetworkDisplayer.display( hisNetwork );
	}
}
