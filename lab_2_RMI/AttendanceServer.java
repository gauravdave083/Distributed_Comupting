import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AttendanceServer extends UnicastRemoteObject implements AttendanceRI {
    
    protected AttendanceServer() throws RemoteException {
        super();
    }

    public boolean recordAttendance(String serverIp, String rollNo, String prnNo, String name) throws RemoteException {
        System.out.println("Attendance Recorded from IP: " + serverIp);
        System.out.println("Roll No: " + rollNo);
        System.out.println("PRN No: " + prnNo);
        System.out.println("Name: " + name);
        return true;
    }

    public static void main(String[] args) {
        try {
            String serverIp = (args.length > 0) ? args[0] : "localhost";
            AttendanceServer server = new AttendanceServer();
            Naming.rebind("rmi://" + serverIp + "/AttendanceService", server);
            System.out.println("Attendance Service is running on " + serverIp);
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}