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
		return this.parts;
	}

	@Override
	public void getPartById(UUID partId) throws RemoteException {
		this.parts.stream().filter(p -> p.getId().equals(partId)).findFirst().ifPresent(
				part -> this.currentPart = part
		);
	}

	@Override
	public String getCurrentPart() throws RemoteException {
		if(this.currentPart != null) {
			return "id: " + this.currentPart.getId()
					+ "\nname: " + this.currentPart.getName()
					+ "\ndescription: " + this.currentPart.getDescription();
		}

		return "No current part.";
	}

	@Override
	public String clearSubParts() throws RemoteException {
		this.currentPart.clearSubParts();
		return "Subpart cleared!!";
	}

	@Override
	public String addSubPart(IPart subpart, Integer quantity) throws RemoteException {

		if (this.currentPart != null && this.currentPart.addSubPart(subpart, quantity)) {
			return "Success";
		}

		return "No current part selected, use 'addp' or 'getp' to select a part.";
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