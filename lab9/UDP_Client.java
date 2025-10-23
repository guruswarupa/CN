import java.net.*;

class UDP_Client {
    public static void main(String args[]) throws Exception {
        DatagramSocket skt = new DatagramSocket(9999); 
        byte[] buffer = new byte[1024];
        System.out.println("Client listening on port 9999...");
        while (true) {
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            skt.receive(reply);
            System.out.println("Client received: " + new String(reply.getData()));
        }
    }
}
