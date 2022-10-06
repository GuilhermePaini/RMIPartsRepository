import java.net.*;
import java.rmi.*;

public class Server {
	public static void main(String[] argv) throws RemoteException {

		//System.setProperty("java.rmi.server.hostname", "10.202.5.44");

		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry está pronto.");
			Repository objRemoto = new Repository();

			Naming.rebind("RMIImpl", objRemoto);
			System.out.println("servidor está pronto");

		} catch (MalformedURLException | RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}