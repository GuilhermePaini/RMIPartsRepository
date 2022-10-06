import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		//ip server = 10.202.5.44
		String host = "10.202.5.44";
// para executar de uma forma segura, pode-se utilizar um arquivo de configuração com polices ...(premissao.policy) 
// e para executar tem que colocar: java -Djava.security.policy=permissao.policy RMIClient 
		
		//System.setSecurityManager(new SecurityManager());
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IRepository server = (IRepository) registry.lookup("RMIImpl");

			int menuOption = 0;
			Scanner scan = new Scanner(System.in);

			while(menuOption != 9){

				System.out.println("Client menu - Choose an option:");
				System.out.println("------------------");
				System.out.println("1 - Say hello!");
				System.out.println("2 - Which SO is the server using?");
				System.out.println("3 - Guess the number?");
				System.out.println("4 - Give me a random number between other numbers!");
				System.out.println("9 - Say goodbye!");

				menuOption = scan.nextInt();

				switch (menuOption) {
					case 1:
						System.out.println(server.helloUwU());
						break;
					case 2:
						break;
					case 3:
						System.out.println("Type a number:");
						break;
					case 4:
						System.out.println("Enter a minimum number and a maximum number:");
						break;

					case 9:
						break;

					default:
						System.out.println("Aeiou");
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