package in.ac.coep.utility;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
   public static void main(String[] args) {
      try {
         InetAddress address = InetAddress.getLocalHost();
         System.out.println("IP address: " + address.getHostAddress());
      } catch (UnknownHostException ex) {
         System.out.println("Could not find IP address for this host");
      }
   }
}