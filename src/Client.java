import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {
	public static void main(String[] args) {

		//ip server = 10.202.5.44
		String host = "localhost";

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IRepository server = (IRepository) registry.lookup("Server");

			String menuOption = "";
			Scanner scan = new Scanner(System.in);

			System.out.println("Welcome to the Part System. Type 'help' for the command list.");

			while(!menuOption.equals("quit")){

				System.out.println("########################################");
				System.out.println(server.getCurrentPart());
				System.out.println("########################################");

				System.out.print(">");

				String[] arguments = scan.nextLine().split(" ");
				menuOption = arguments[0];

				switch (menuOption) {
					case "listp":
						// TODO: make it look beautiful
						var listParts = server.listParts();

						for (IPart a: listParts) {
							System.out.println(a.getId());
						}

						break;
					case "getp":
						if(arguments.length == 1 || Objects.equals(arguments[1], "")) {
							System.out.println("Missing argument 'id'. ");
							break;
						}
						server.getPartById(UUID.fromString(arguments[1]));
						break;

					case "showp":

						System.out.println(server.getCurrentPart() + "\n");

						if(server.listSubParts().isEmpty()) {
							System.out.println("\nList of subparts is empty.");
							break;
						}

						// TODO: make it look beautiful
						System.out.println("List of subparts: ###################");

						for (var entry : server.listSubParts().entrySet()) {
							var part = entry.getKey();
							System.out.println("Name: " + part.getName());
							System.out.println("Description: " + part.getDescription());
							System.out.println("Quantity: " + entry.getValue() + "\n");
						}

						break;

					case "clearsubs":
						System.out.println(server.clearSubParts());
						break;

					case "addsubpart":

						if(arguments.length < 4
							|| Objects.equals(arguments[1], "")
							|| Objects.equals(arguments[2], "")
							|| Objects.equals(arguments[3], "")
						) {
							System.out.println("Missing arguments. ");
							break;
						}

						try {
							var quantity = Integer.parseInt(arguments[3]);
							var a = new Part(arguments[1], arguments[2], new HashMap<>());
							System.out.println(server.addSubPart(a, quantity));
						}catch (Exception ex) {
							System.out.println("Something went wrong!!");
						}

						break;

					case "addp":
						if(arguments.length < 3
								|| Objects.equals(arguments[1], "")
								|| Objects.equals(arguments[2], "")
						) {
							System.out.println("Missing arguments. ");
							break;
						}

						System.out.println(server.addPart(arguments[1], arguments[2]));
						break;

					case "help":
						System.out.println("List of available commands:");
						System.out.println("---------------------------");
						System.out.println(
							"listp - List of parts available.\n" +
							"getp - Get a part using it's id.\n" +
							"showp - Shows the current part's attributes.\n" +
							"REMOVED - clearlist - Clear the part's list.\n" +
							"clearsubs - Clear the subpart's list of the current part.\n" +
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