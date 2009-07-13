package mobileElements;


/**
 * @uml.dependency   supplier="mobileElements.Locomotive"
 * @uml.dependency   supplier="mobileElements.Train"
 * @uml.dependency   supplier="mobileElements.Wagon"
 * 
 */


public class BasicTrainFactory extends MobileElementFactory {

	/* (non-Javadoc)
	 * @see MobileElementFactory#createMobileElement(int, int)
	 */
	@Override
	public Train createMobileElement(int nbMotorizedElements,
			int nbTowedElements) {
		
		// Appel le constructeur du train
		Train train = new Train(nbMotorizedElements, nbTowedElements);
		
		return train;
	}

}
