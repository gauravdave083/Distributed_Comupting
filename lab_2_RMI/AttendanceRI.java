
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AttendanceRI extends Remote {
    boolean recordAttendance(String serverIp, String rollNo, String prnNo, String name) throws RemoteException;
}