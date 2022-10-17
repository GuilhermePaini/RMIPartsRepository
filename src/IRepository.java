import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IRepository extends java.rmi.Remote
{
	List<IPart> listParts() throws java.rmi.RemoteException;
	Map<IPart, Integer> listSubParts() throws java.rmi.RemoteException;
	void getPartById(UUID partId) throws java.rmi.RemoteException;
	String getCurrentPart() throws java.rmi.RemoteException;
	String clearSubParts() throws java.rmi.RemoteException;
	String addSubPart(IPart subpart, Integer quantity) throws java.rmi.RemoteException;
	String addPart(String name, String description) throws java.rmi.RemoteException;
	String quit() throws java.rmi.RemoteException;
}