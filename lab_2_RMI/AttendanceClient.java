
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class AttendanceClient 
{
    public static void main(String[] args) 
    {
        try 
        {
            if (args.length < 4) {
                System.out.println("Usage: java AttendanceClient <server_ip> <roll_no> <prn_no> <name>");
                return;
            }
            
            String serverIp = args[0];
            String rollNo = args[1];
            String prnNo = args[2];
            String name = args[3];

            AttendanceRI attendanceService = (AttendanceRI) Naming.lookup("rmi://" + serverIp + "/AttendanceService");
            boolean success = attendanceService.recordAttendance(serverIp,rollNo, prnNo, name);

            if (success) {
                System.out.println("Attendance successfully recorded.");
            } else {
                System.out.println("Failed to record attendance.");
            }
        }
        catch (MalformedURLException murle) 
        {
            System.out.println("MalformedURLException");
            System.out.println(murle);
        }
        catch (RemoteException re) 
        {
            System.out.println("RemoteException");
            System.out.println(re);
        }
        catch (NotBoundException nbe) 
        {
            System.out.println("NotBoundException");
            System.out.println(nbe);
        }
    }
}
