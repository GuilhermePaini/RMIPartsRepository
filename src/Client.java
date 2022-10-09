import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		//ip server = 10.202.5.44
		String host = "10.202.5.44";

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IRepository server = (IRepository) registry.lookup("Server");

			String menuOption = "";
			Scanner scan = new Scanner(System.in);

			System.out.println("Welcome to the Part System. \n Type 'help' for the command list.");

			while(!menuOption.equals("quit")){
				System.out.print(">");
				menuOption = scan.nextLine();

				switch (menuOption) {
					case "listp":
						//System.out.println(server.helloUwU());
						break;
					case "getp":
						break;

					case "showp":
						System.out.println(server.getCurrentPart());
						break;

					case "clearlist":
						break;

					case "addsubpart":
						break;

					case "addp":
						// TODO: add user input to define name and description
						System.out.println(server.addPart("bom", "dia"));
						break;

					case "help":
						System.out.println("List of available commands:");
						System.out.println("---------------------------");
						System.out.println(
							"listp - List of parts available.\n" +
							"getp - Get a part using it's id.\n" +
							"showp - Shows the current part's attributes.\n" +
							"clearlist - Clear the part's list.\n" +
							"addsubpart - Add a subpart to the subpart's list of the current part.\n" +
							"addp - Add a part to the current repository.\n" +
							"quit - Ends the client execution."
						);
						break;

					case "quit":
						System.out.println(server.quit());
						break;

					default:
						System.out.println("This option is not available at the moment, contact the system administrators.");
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("Succesfully connected with RMI!");
	}
}