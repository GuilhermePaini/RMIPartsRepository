import java.util.List;
import java.util.UUID;

public interface IRepository extends java.rmi.Remote
{
	public List<IPart> listParts() throws java.rmi.RemoteException;
	public void getPartById(UUID partId) throws java.rmi.RemoteException;
	public String getCurrentPart() throws java.rmi.RemoteException;
	public String clearSubParts() throws java.rmi.RemoteException;
	public String addSubPart(IPart subpart, Integer quantity) throws java.rmi.RemoteException;
	public String addPart(String name, String description) throws java.rmi.RemoteException;
	public String quit() throws java.rmi.RemoteException;
}