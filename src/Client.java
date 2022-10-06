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
				int guess = 0;
				int min = 0;
				int max = 0;

				switch (menuOption) {
					case 1:
						System.out.println(server.helloUwU());
						break;
					case 2:
						System.out.println(server.getSO());
						break;
					case 3:
						System.out.println("Type a number:");
						guess = scan.nextInt();
						System.out.println(server.guessTheNumber(guess));
						break;
					case 4:
						System.out.println("Enter a minimum number and a maximum number:");
						min = scan.nextInt();
						max = scan.nextInt();
						System.out.println(server.randomNumberBetween(min, max));
						break;

					case 9:
						System.out.println(server.goodbyeUwU());
						break;

					default:
						System.out.println("Aeiou");
				}
			}
			//myServerObject.adiciona("XPTO");
			//System.out.println("No servidor tem agora estes elementos" + myServerObject.consulta() );
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("Succesfully connected with RMI!");
	}
}