package manager;

import java.util.Iterator;
import java.util.Vector;

import mobileElements.MobileElement;

/**
 * 
 * Classe d'affichage d'un réseau en mode console
 *
 */
public class ConsoleNetworkDisplayer extends NetworkDisplayer {

	/**
	 * Affiche l'état d'un réseau dans la console
	 */
	public void display( Network nw ){
		Vector<MobileElement> mbs = nw.getMobileElements();
		Iterator<MobileElement> it = mbs.iterator();
		MobileElement mb = null;
		
		// Parcours des éléments mobiles du réseau
		while(it.hasNext()){
			mb = it.next();
			System.out.println( "Mobile element " + mb.getId() );
			System.out.println( "\tSpeed : " + mb.getSpeed() );
			System.out.println( "\tRail track : " + mb.getCurrentHeadingSection().getRailTrack().getId() );
			System.out.println( "\tSection : " + mb.getCurrentHeadingSection().getId() );
		}
	}
	
}
