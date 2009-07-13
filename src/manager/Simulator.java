package manager;

import java.util.Timer;

/**
 * Classe de simulation
 * Le simulateur est cadenc� par une horloge.
 * A chaque top, les actions sont effectu�es
 * sur le r�seau et son affichage est mis � jour.
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
	 * Modifie la p�riode du simulateur en ms
	 */
	public void setRate(long rate){
		this.rate = rate;
	}

	/**
	 * Indique au simulateur le r�seau sur lequel il doit agir
	 */
	public void setNetwork(Network network){
		hisNetwork = network;
	}
	
	/**
	 * D�marre la simulation
	 */
	public void start(){
		hisNetworkDisplayer.display( hisNetwork );
		timer.schedule( timerTask , rate , rate );
	}
	
	/**
	 * Donne le nombre de tours �coul�sdepuis le d�but de la simulation
	 */
	public int getStepNumber(){
		return stepNumber;
	}
	
	/**
	 * Incr�mente le num�ro de l'�tape
	 */
	public void incStepNumber(){
		stepNumber++;
	}
	
	/**
	 * Fonction appel�e � chaque top d'horloge.
	 * Effectue les actions sur le r�seau et met � jour son affichage.
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
