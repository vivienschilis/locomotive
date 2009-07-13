package manager;

/**
 * Classe contenant le main
 */
public class MainClass {

	public static void main(String[] args) {
		System.out.println("Starting...");
		Simulator simulator = new Simulator();
		simulator.setNetwork( new NetworkTest() );
		simulator.start();
	}

}
