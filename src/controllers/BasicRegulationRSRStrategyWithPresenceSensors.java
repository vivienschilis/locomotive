/**
 * Cette classe représente le comportement "générique" d'un
 * {@link ControlSystem}. Elle permet de synchroniser des {@link PresenceSensor},
 * des {@link Sempahore} et des {@link RailSwitchRoad}. Les sémaphores étant placés 
 * de part et d'autre des {@link RailSwitchRoad}
 */
package controllers;

import java.util.Iterator;
import java.util.Vector;

import semaphores.Semaphore;
import sensors.PresenceListenable;
import sensors.SensorListenable;
import sensors.SensorListenableAdvancedCollection;
import wayelement.RailSwitchRoad;
import wayelement.RailTrack;
import wayelement.Way;
import wayelement.WayElement;

/** 
 */
public class BasicRegulationRSRStrategyWithPresenceSensors extends RegulationStrategy {
	
	private Vector<PresenceListenable> presenceNotified;/*ensemble d'objets
	observables par des capteurs de présence qui ont émis une notification*/
	private Vector<RailTrack> justLinked;/*l'ensemble des rails venant juste d'être liés
	dans les aiguillages*/

	/**
	 * Sous Classe utilisée pour grouper un rail et son aiguillage correspondant
	 */
	private class BindingRailToSwitch{
		
		private RailTrack railTrack;//le rail
		private RailSwitchRoad railSwitchRoad;//l'aiguillage correspondant
		
		/**
		 * Constructeur
		 * @param rail
		 * le rail
		 * @param rsr
		 * l'aiguillage
		 */
		public BindingRailToSwitch(RailTrack rail, RailSwitchRoad rsr){
			super();
			this.railSwitchRoad = rsr;
			this.railTrack = rail;
		}
		
		public RailSwitchRoad getRailSwitchRoad(){
			return railSwitchRoad;
		}
		
		public RailTrack getRailTrack(){
			return railTrack;
		}
	}
	
	/**
	 * Construit un {@link BasicRegulationRSRStrategyWithPresenceSensors}
	 * @param semaphores
	 * La liste des sémaphores à controler
	 * @param railSwitchRoads
	 * la liste des aiguillage a controler
	 * @param watchedItems
	 * la liste des éléments observables par des capteurs que cette strategie
	 * se charge de gérer
	 */
	public BasicRegulationRSRStrategyWithPresenceSensors(Vector<Semaphore> semaphores, Vector<RailSwitchRoad> railSwitchRoads, Vector<SensorListenable> watchedItems){
		super(semaphores,railSwitchRoads,watchedItems);
		presenceNotified = new Vector<PresenceListenable>();
		justLinked = new Vector<RailTrack>();
	}
	
	public BasicRegulationRSRStrategyWithPresenceSensors(){
		super();
		presenceNotified = new Vector<PresenceListenable>();
		justLinked = new Vector<RailTrack>();
	}	
	
	@Override
	/**
	 * Régule le traffic à partir d'un {@link SensorListenableAdvancedCollection}
	 * d'objets observables ayant effectués une notification.
	 */
	public void regulate(SensorListenableAdvancedCollection eventSensorListenables) {
		extractPresenceSensors(eventSensorListenables);//extraction des PresenceListenable
		unlockSemaphores();//débloquage des sémaphores controlés
		Iterator<PresenceListenable> it = presenceNotified.iterator();
		WayElement we;
		BindingRailToSwitch b;
		RailTrack rail;
		RailTrack railOut;
		RailSwitchRoad rsr;
		while(it.hasNext()){
			we = it.next().getLocation();//récupération de la location de l'objet observé
			b = searchBindingRailToSwitch(we);//récupération du rail et de l'aiguillage correspondant
			rail = b.getRailTrack();
			rsr = b.getRailSwitchRoad();
			//Comme il y a une présence et se trouvant au bord de l'aiguillage
			//on va lié le rail où se trouve la présence à un rail de sortie de l'aiguillage
			//si ceci n'a pas déjà été fait.
			if(!rsr.isLinked(rail)){
				//On lie le rail
				justLinked.add(rail);//ajout le rail courant au rail venant d'être lié
				railOut = returnFreeRail(rsr.getPossibleConnexions(rail));/*récupération
				des connections possibles du rail courant aux autres rails de l'aiguillage*/
				if(railOut == null){
					//s'il n'y pas de connection possible, on bloque l'éventuelle
					//sémaphore se trouvant sur le rail de manière à ce que cette présence
					//ne puisse faire de progression sur l'aiguillage et ainsi de dérailler.
					Semaphore s = rail.getSemaphore(rsr.getWay(rail,rsr.getPossibleConnexions(rail).firstElement()));
					if(s!=null)
						s.lock();
				}else{	
					//On lie les rails
					rsr.link(rail, railOut);
					//et on débloque leur sémaphore de manière à laisser circuler la présence
					//détectée
					Semaphore s = rail.getSemaphore(rsr.getWay(rail,rsr.getPossibleConnexions(rail).firstElement()));
					s.unlock();
					s = railOut.getSemaphore(rsr.getWay(railOut,rsr.getPossibleConnexions(railOut).firstElement()));
					s.unlock();
				}
			}
			lockSemaphores(rsr, rail);	//on bloque les autres sémaphores aux abords 
			//de l'aiguillage de manière à éviter un probable accident.
		}
		justLinked = new Vector<RailTrack>();//réinitialisation des rails venant d'être liés
		
	}

/**
 * Débloque tous les sémaphores sous controle
 */
	private void unlockSemaphores() {
		Iterator<RailSwitchRoad> itRsr = railSwitchRoads.iterator();
		RailSwitchRoad rsr;
		while(itRsr.hasNext()){
			rsr = itRsr.next();
			Iterator<Vector<RailTrack>> itVector = rsr.getAllRailTracks().iterator();
			//en itérant sur les rails amonts puis sur les rails aval
			int way = 0;
			Semaphore s;
			while(itVector.hasNext()){
				Iterator<RailTrack> itr = itVector.next().iterator();
				RailTrack rail;
				//récupération du rail
				while(itr.hasNext()){
					rail = itr.next();
					//déblocage de son sémaphore dans le sens de l'aiguillage
					s = rail.getSemaphore(way);
					if(s!=null && s.isLocked())
						s.unlock();
				}
				way++;//changement de sens
			}
		}	
	}
		
/**
 * Bloque un ensemble de sémaphores autour d'un aiguillage tel que ceux si ne soit pas lié
 * à un certain rail.
 * @param rsr
 * L'aiguillage
 * @param railOrigin
 * le certain rail.
 */
	private void lockSemaphores(RailSwitchRoad rsr, RailTrack railOrigin) {
		Iterator<Vector<RailTrack>> itVector = rsr.getAllRailTracks().iterator();
		int way = -1;
		int way2 = -1;
		Semaphore s,s2;
		//pour tout les rails de l'aiguillage
		while(itVector.hasNext()){
			Iterator<RailTrack> itr = itVector.next().iterator();
			RailTrack rail;
			while(itr.hasNext()){
				rail = itr.next();
				//si le rail courant n'est pas le rail d'origine ou s'il n'est pas lié à celui ci
				if(!rsr.linkedTo(railOrigin).equals(rail) && !railOrigin.equals(rail)){
					//récupération des sens vers l'aiguillage a partir du rail d'origine
					//et le rail qui lui est lié
					way = rsr.getWay(railOrigin,rail);
					way2 = rsr.getWay(rsr.linkedTo(railOrigin),rail);

					//récupération de leur sémaphore associé.
					s = rail.getSemaphore(Way.inverseWay(way));
					s2 = rail.getSemaphore(Way.inverseWay(way2));
					//bloquage des sémaphores
					if(s!=null){
						s.lock();
					}else{				
					}
					if(s2!=null){
						s2.lock();
					}else{				
					}
				}
			}
		}
	}


/**
 * 
 * @param possibleConnexions
 * un ensemble de rail
 * @return
 * Retourne un rail non déjà lié qui est libre de notification
 */
	private RailTrack returnFreeRail(Vector<RailTrack> possibleConnexions) {
		Iterator<RailTrack> it = possibleConnexions.iterator();
		RailTrack rail;
		while(it.hasNext()){
			rail = it.next();
			//pour tout les objets observés
			Iterator<SensorListenable> itSens = watchedItems.iterator();
			SensorListenable item;
			while(itSens.hasNext()){
				item = itSens.next();
				if(item.getLocation().isJoinedTo(rail)){
					//si ce rail n'a pas detecté de présence ou s'il ne vient pas d'etre lié.
					if(!presenceNotified.contains(item) && !justLinked.contains(rail)){
						justLinked.add(rail);//ajout aux rails venant d'être liés.
						return rail;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param we
	 * un élément de voie
	 * @return
	 * Le rail associé à cet élément de voie ainsi que l'aiguillage
	 * associé à ce rail sous forme d'un {@link BindingRailToSwitch}
	 */
	private BindingRailToSwitch searchBindingRailToSwitch(WayElement we) {
		Iterator<RailSwitchRoad> it = railSwitchRoads.iterator();
		RailSwitchRoad rsr;
		//pour tout les aiguillages
		while(it.hasNext()){
			rsr = it.next();
			Iterator<Vector<RailTrack>> itVectors = rsr.getAllRailTracks().iterator();
			//pour tout les rails de l'aiguillage courant
			Iterator<RailTrack> railsIt;
			while(itVectors.hasNext()){
				railsIt = itVectors.next().iterator();
				RailTrack rail;
				while(railsIt.hasNext()){
					rail = railsIt.next();
					//si l'élément de voie est joint au rail courant
					if(we.isJoinedTo(rail)){
						return new BindingRailToSwitch(rail,rsr);
					}
				}
			}
			
		}
		return null;
	}

	/**
	 * Récupère l'ensemble des {@link PresenceListenable} ayant émis une notification
	 * dans un ensemble de {@link SensorListenable}
	 * @param eventSensorListenables
	 * l'ensemble des {@link SensorListenable} ayant émis des notifications diverses
	 */
	protected void extractPresenceSensors(SensorListenableAdvancedCollection eventSensorListenables) {
		presenceNotified = eventSensorListenables.getPresenceListenables();
		
	}
	
}
