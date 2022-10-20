import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {
	public static void main(String[] args) {

		String host = "localhost";

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IRepository server = (IRepository) registry.lookup("Server");

			String menuOption = "";
			Scanner scan = new Scanner(System.in);

			System.out.println("Welcome to the Part System. Type 'help' for the command list.");

			while(!menuOption.equals("quit")){

				System.out.println("########################################");
				System.out.println("Current part selected:");
				System.out.println(server.getCurrentPart());
				System.out.println("########################################");

				System.out.print(">");

				String[] params = scan.nextLine().split(" ");
				menuOption = params[0];

				switch (menuOption) {
					case "listp":
						var listParts = server.listParts();

						System.out.println("List of available parts:");

						for (IPart part: listParts) {
							System.out.println("----------------------------------------");
							System.out.println("id: " + part.getId());
							System.out.println("name: " + part.getName());
							System.out.println("description: " + part.getDescription());
						}

						System.out.println("----------------------------------------");

						break;

					case "getp":
						if(params.length == 1 || Objects.equals(params[1], "")) {
							System.out.println("Missing argument 'id'.");
							break;
						}
						server.getPartById(UUID.fromString(params[1]));
						break;

					case "showp":

						System.out.println("Current part selected:");
						System.out.println(server.getCurrentPart() + "\n");

						if(server.listSubParts().isEmpty()) {
							System.out.println("\nList of subparts is empty.");
							break;
						}

						System.out.println("List of subparts:");

						for (var entry : server.listSubParts().entrySet()) {
							var part = entry.getKey();
							System.out.println("----------------------------------------");
							System.out.println("Name: " + part.getName());
							System.out.println("Description: " + part.getDescription());
							System.out.println("Quantity: " + entry.getValue());
						}

						break;

					case "clearsubs":
						System.out.println(server.clearSubParts());
						break;

					case "addsubpart":

						if(params.length < 4
							|| Objects.equals(params[1], "")
							|| Objects.equals(params[2], "")
							|| Objects.equals(params[3], "")
						) {
							System.out.println("Missing arguments. ");
							break;
						}

						try {
							var quantity = Integer.parseInt(params[3]);
							var a = new Part(params[1], params[2], new HashMap<>());
							System.out.println(server.addSubPart(a, quantity));
						}catch (Exception ex) {
							System.out.println("Something went wrong!!");
						}

						break;

					case "addp":
						if(params.length < 3
								|| Objects.equals(params[1], "")
								|| Objects.equals(params[2], "")
						) {
							System.out.println("Missing arguments. ");
							break;
						}

						System.out.println(server.addPart(params[1], params[2]));
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