import java.util.*;

public class LeakyBucket{

    static int min(int x,int y){
        if (x < y) 
            return x; 
        else
            return y;
    }

    public static void main(String args[]){
        int drop = 0,psent,nsec,cap,pleft=0,i,process;
        int inp[] = new int[25];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket size:");
        cap = sc.nextInt();
        System.out.print("Enter output rate (packets/second):");
        process = sc.nextInt();
        System.out.print("Enter number of seconds you want to simulate:");
        nsec = sc.nextInt();
        for(i=0;i<nsec;i++){
            System.out.print("Enter number of packets received at second " + (i+1) + " : ");
            inp[i] = sc.nextInt();
        }
        System.out.println("\nSecond | Packet Received | Packet Sent | Packet Left | Packet Dropped");
        System.out.println("-------------------------------------------------------------------------\n");
        for(i=0;i<nsec;i++){
            pleft+=inp[i];
            if(pleft > cap){
                drop = pleft - cap;
                pleft = cap;
            }
            System.out.print(i+1);
            System.out.print("\t\t" + inp[i]);
            psent = min(pleft,process);
            System.out.print("\t\t" + psent);
            pleft -= psent;
            System.out.print("\t\t" + pleft);
            System.out.print("\t\t" + drop + "\n");
            drop = 0;
        }
    for(;pleft!=0;i++){
        if(pleft > cap){
            drop = pleft - cap;
            pleft = cap;
        }
        System.out.print(i+1);
        System.out.print("\t\t" + 0);
        psent = min(pleft,process);
        System.out.print("\t\t" + psent);
        pleft -= psent;
        System.out.print("\t\t" + pleft);
        System.out.print("\t\t" + drop + "\n");
        }
    }
}