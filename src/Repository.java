import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Repository extends UnicastRemoteObject implements IRepository
{
	private List<IPart> parts;
	private IPart currentPart;
	public Repository() throws java.rmi.RemoteException {
		super();
	}
	@Override
	public String helloUwU() throws RemoteException {
		return "H-Hey, you found the x3 sewvew, nyow whispers to self you awe connyected!? OwO";
	}

	@Override
	public IPart getCurrentPart() throws RemoteException {
		return null;
	}
}