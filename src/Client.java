import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Client {
	public static void main(String[] args) {

		//ip server = 10.202.5.44
		String host = "10.202.5.44";

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
						break;

					case "clearlist":
						// TODO: implement
						break;

					case "addsubpart":
						// TODO: add user input to define name, description and quantity
						var a = new Part("bom dia", "turma", new HashMap<>());
						System.out.println(server.addSubPart(a, 1));
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