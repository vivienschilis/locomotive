package mobileElements;

import java.util.Vector;

import wayelement.OutOfWayException;
import wayelement.RailTrack;
import wayelement.Section;

/**
 * 
 */


public class Train extends MobileElement {
	
	/**
	 * Cr�� un train compos� de wagons et de locomotives
	 * @param locomotiveNumber
	 * @param wagonNumber
	 */

	protected Train(int locomotiveNumber, int wagonNumber){
		super();
		// Un train ne peut avoir au plus que 2 locomotives
		// un train doit avoir au moins une locomotive
		// un train doit avoir au moins un wagon
		if((locomotiveNumber==1 || locomotiveNumber==2)
				&& (wagonNumber>=1)){

			// ajout des locomotives au train
			// la premi�re est dans le sens du d�placement
			Locomotive mainLoco = new Locomotive(Locomotive.FORWARD);
			this.getMotorizedSubElements().add(mainLoco);
			// la seconde dans l'autre sens (l'attache d'une locomotive est toujours dans son dos)
			if(locomotiveNumber==2){
				Locomotive secondLoco = new Locomotive(Locomotive.BACKWARD);
				this.getMotorizedSubElements().add(secondLoco);
			}
			
			// ajout des wagons au train
			for(int i=0; i<wagonNumber; i++){
				Wagon currentWagon = new Wagon();
				this.getTowedElements().add(currentWagon);
			}
						
		}else{
			//TODO throw an exception
		}
	}

	/**
	 * D�place le train sur le r�seau � chaque unit� de temps
	 * @throws DerailmentException 
	 */
	public void move() throws DerailmentException{
		
		Section previousHeadingSection = this.getCurrentHeadingSection();
		
		Section currentSection = previousHeadingSection;
		int i=0;
		while(i<this.getSpeed()){ // la vitesse peu �tre modifi�e en cours de parcours
			if(currentSection.getRailTrack().getLastSection(this.getWay()).isSemaphorePresent(this.getWay())){
				// si un s�maphore est pr�sent sur le rail, ex�cuter son algorithme
				currentSection.getRailTrack().getLastSection(this.getWay()).getSemaphore(this.getWay()).executeAlgorithm(this);
			}
			try {
				currentSection = currentSection.getNextSection(this.getWay());
			} catch (OutOfWayException e) {
				this.setSpeed(0);
				System.out.println( e.getMessage() );
				throw new DerailmentException("Train " + this.getId() + " went out of rail track");
			}
			if( currentSection == null){
				this.setSpeed(0);
				throw new DerailmentException("Train " + this.getId() + " arrived at end of track at full speed !");
			}
			i++;
		}

		this.setCurrentHeadingSection(currentSection);
	}
	
	
	/**
	 * Augmente la vitesse courante du train
	 * @param amount
	 */
	public void accelerate(int amount){
		// pour acc�l�rer, on ne consid�re que la locomotive de t�te
		Locomotive loco = (Locomotive)this.getMotorizedSubElements().get(0);
		// Si la vitesse suite � l'acc�l�ration ne d�passe pas la capacit� du moteur de la loco
		if(this.getSpeed()+amount <= this.MAX_SPEED){
			loco.setSpeed(this.getSpeed()+amount);
		}else{
			if(this.getSpeed()!= this.MAX_SPEED){
				// mettre � fond
				loco.setSpeed(this.MAX_SPEED);
			}
		}
	}
	
	/**
	 * Baisse la vitesse courante du train
	 * @param amount
	 */
	public void decelerate(int amount){
		// pour d�c�lerer, ici on utilise la locomotive de t�te
		Locomotive loco = (Locomotive)this.getMotorizedSubElements().get(0);
		// Si la vitesse suite � la d�c�l�ration n'est pas inf�rieure � zero
		if(this.getSpeed()-amount >= 0){
			loco.setSpeed(this.getSpeed()-amount);
		}else{
			if(this.getSpeed()!= this.MAX_SPEED){
				// arr�ter le train
				loco.setSpeed(0);
			}
		}
	}

	/**
	 * Modifie la vitesse du train
	 */
	@Override
	public void setSpeed(int speed) {
		if(this.getSpeed()>speed){
			decelerate(this.getSpeed()-speed);
		}else if(this.getSpeed()<speed){
			accelerate(speed-this.getSpeed());
		}
	}
	
}
