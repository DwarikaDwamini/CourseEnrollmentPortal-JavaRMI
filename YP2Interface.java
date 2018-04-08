
import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface YP2Interface extends Remote {
   
    // this is the interface which acts as link between server and client.
  
    void selectedCourse(String selectedCourse, String user, String opt) throws RemoteException;
    
    // these methods are used to get the attributes of each object and return the values.
    String getCourse() throws RemoteException;
       String getcapacity() throws RemoteException;
    String getprof() throws RemoteException;
    String getroomNo() throws RemoteException;
    String gettimeID() throws RemoteException;
    String getsection() throws RemoteException;
    String getword2() throws RemoteException;
    String getword1() throws RemoteException;
    String getword0() throws RemoteException;
    String getId() throws RemoteException;
    void incCount(String option, String course) throws RemoteException;
}
