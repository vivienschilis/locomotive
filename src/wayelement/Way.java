package wayelement;

public class Way {

	public static final int WAY_LR = 0;		// Left to Right = Amont -> Aval
	public static final int WAY_RL = 1;		// Right to Left = Aval -> Amont 
	public static final int NB_MAX_WAY = 2;
	public static final int WAY_ERROR = 2;

	/**
	* Retour le sens suivant apres avoir parcouru les deux rails rail1 et rail2 de le sens de way
	* @param RailTrack rail1
	* @param RailtRack rail2
	* @param int way
	* @return int sens opposé
	*/
	public static int getNewWay(RailTrack rail1, RailTrack rail2, int way) {
		JunctionElement[] j1 = rail1.getJunctionElements();
		JunctionElement[] j2 = rail2.getJunctionElements();
		
		if (rail1==rail2) return way;
		
		if ( (j1[Way.WAY_LR] == j2[Way.WAY_RL]) || (j1[Way.WAY_RL] == j2[Way.WAY_LR]) )
			return way;
		else
			return Way.inverseWay(way);
	}
	
	/**
	* @param int way sens
	* @return int sens opposé
	*/
	public static int inverseWay( int way ) { if (way == Way.WAY_LR) return Way.WAY_RL; else if (way == Way.WAY_RL) return Way.WAY_LR; return Way.WAY_ERROR;}
	
	public static int getWay(RailTrack rail1, RailTrack rail2)
	{
		if (rail1 == rail2 ) return Way.WAY_ERROR;
		
		JunctionElement[] j1 = rail1.getJunctionElements();
		JunctionElement[] j2 = rail2.getJunctionElements();
		
		if ( j1[Way.WAY_LR] == j2[Way.WAY_RL] )
			return Way.WAY_LR ;
			
		if (j1[Way.WAY_RL] == j2[Way.WAY_LR] )
			return Way.WAY_RL;

		return Way.WAY_ERROR;
		
	}
}
