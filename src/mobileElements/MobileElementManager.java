package mobileElements;


import java.util.Vector;
import java.util.Collection;/**
 * 
 */


public class MobileElementManager {

	/**
	 * @uml.property  name="mobileElementFactory"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="mobileElementManager:MobileElementFactory"
	 */
	private Vector mobileElementFactory;

	/**
	 * Getter of the property <tt>mobileElementFactory</tt>
	 * @return  Returns the mobileElementFactory.
	 * @uml.property  name="mobileElementFactory"
	 */
	public Vector getMobileElementFactory() {
		return mobileElementFactory;
	}

	/**
	 * Setter of the property <tt>mobileElementFactory</tt>
	 * @param mobileElementFactory  The mobileElementFactory to set.
	 * @uml.property  name="mobileElementFactory"
	 */
	public void setMobileElementFactory(Vector mobileElementFactory) {
		this.mobileElementFactory = mobileElementFactory;
	}

	/** 
	 * @uml.property name="mobileElement"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="mobileElementManager:MobileElement"
	 */
	private Vector mobileElement;

	/** 
	 * Getter of the property <tt>mobileElement</tt>
	 * @return  Returns the mobileElement.
	 * @uml.property  name="mobileElement"
	 */
	public Vector getMobileElement() {
		return mobileElement;
	}

	/** 
	 * Setter of the property <tt>mobileElement</tt>
	 * @param mobileElement  The mobileElement to set.
	 * @uml.property  name="mobileElement"
	 */
	public void setMobileElement(Vector mobileElement) {
		this.mobileElement = mobileElement;
	}

}
