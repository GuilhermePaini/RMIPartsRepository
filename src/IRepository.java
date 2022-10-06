public interface IRepository extends java.rmi.Remote{
	
	public String helloUwU () throws java.rmi.RemoteException;
	public IPart getCurrentPart() throws java.rmi.RemoteException;
}