import java.io.*;
import java.net.*;

class UDP_Server {
    public static void main(String args[]) throws Exception {
        DatagramSocket sock = new DatagramSocket();
        String s;
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));  
        InetAddress host = InetAddress.getByName("localhost");
        while(true){
            System.out.print("Enter message: ");
            s = cin.readLine();
            byte[] b = s.getBytes();
            DatagramPacket dp = new DatagramPacket(b, b.length, host, 9999);
            sock.send(dp); 
        }     
    }
}
