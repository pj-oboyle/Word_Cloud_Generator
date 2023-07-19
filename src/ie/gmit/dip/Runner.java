package ie.gmit.dip;

public class Runner {
	
	public static void main(String[] args) {
		
		
		Menu menu = new Menu();
		Settings settings = menu.menu();

		// toExecute() checks that all execution preconditions are met
		if (settings.toExecute()) {
			Generator newCloud = new GenerateCloud(settings);
			newCloud.generate();
		}else { 
			// quit program console message
			System.out.println("\nProgram Closed");

		}

	}


}
