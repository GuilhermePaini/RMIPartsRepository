import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Repository extends UnicastRemoteObject implements IRepository
{
	private List<IPart> parts;
	private IPart currentPart;
	public Repository() throws java.rmi.RemoteException {
		super();
		this.parts = new ArrayList<>();
	}

	@Override
	public List<IPart> listParts() throws RemoteException {
		return null;
	}

	@Override
	public IPart getPartById(UUID partId) throws RemoteException {
		var part = this.parts.stream().filter(p -> p.getId().equals(partId)).findFirst().orElse(null);

		if(part != null) {
			this.currentPart = part;
			return this.currentPart;
		}

		return part;
	}

	@Override
	public String getCurrentPart() throws RemoteException {
		// TODO: add subparts name, description and quantity to this toString
		return "id: " + this.currentPart.getId()
				+ "\n name: " + this.currentPart.getName()
				+ "\n description: " + this.currentPart.getDescription();
	}

	@Override
	public String clearSubParts() throws RemoteException {
		this.currentPart.clearSubParts();
		return "Subpart cleared!!";
	}

	@Override
	public String addSubPart(IPart subpart, Integer quantity) throws RemoteException {
		return "";
	}

	@Override
	public String addPart(String name, String description) throws RemoteException {
		this.currentPart = new Part(name, description, new HashMap<>());
		this.parts.add(this.currentPart);
		return this.getCurrentPart();
	}

	@Override
	public String quit() throws RemoteException {
		return "bye!";
	}
}