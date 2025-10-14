import java.util.Scanner;
import java.util.Random;

public class SlidingWindowProtocol {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter number of frames to send:");
        int totalFrames = sc.nextInt();
        System.out.print("Enter the window size:");
        int windowSize = sc.nextInt();
        int framesSent = 0;

        while(framesSent < totalFrames){
            int framesToSend = Math.min(windowSize, totalFrames - framesSent);
            System.out.println("Sending frames:");
            for(int i=0;i<framesToSend;i++){
                System.out.println("Sent Frame #" + (framesSent + i));
            }
            
            boolean allAcked = true;
            for(int i=0;i<framesToSend;i++){
                boolean ackReceived = rand.nextInt(10) < 9; // 90% chance of ACK
                if(!ackReceived){
                    System.out.println("ACK received for Frame #" + (framesSent + i));
                }else{
                    System.out.println("ACK not received for Frame #" + (framesSent + i)+ " Retransmitting from here...");
                    framesSent += i;
                    allAcked = false;
                    break;
                }
            }
            if(allAcked){
                framesSent += framesToSend;
            }
        }
        System.out.println("All frames sent and acknowledged successfully.");
    }
}