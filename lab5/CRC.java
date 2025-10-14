import java.util.Scanner;

public class CRC {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of data bits:");
        int dataBits = sc.nextInt();
        int[] data = new int[dataBits];
        System.out.println("Enter data bits:");
        for(int i=0;i<dataBits;i++){
            data[i] = sc.nextInt();
        }

        System.out.println("Enter number of divisor bits:");
        int divisorBits = sc.nextInt();
        int[] divisor = new int[divisorBits];
        System.out.println("Enter divisor bits:");
        for(int i=0;i<divisorBits;i++){
            divisor[i] = sc.nextInt();
        }

        int totalBits = dataBits + divisorBits - 1;
        int[] dividend = new int[totalBits];
        System.arraycopy(data,0,dividend,0,dataBits);
        System.out.print("Dividend(after appending 0's):");
        printArray(dividend);

        int[] remainder = divide(dividend.clone(),divisor);
        int[] crcCode = new int[totalBits];
        for(int i=0;i<totalBits;i++){
            crcCode[i] = dividend[i] ^ remainder[i];
        }
        System.out.print("CRC Code:");
        printArray(crcCode);

        System.out.println("\n Enter received code(" + totalBits +"bits):");
        int[] received = new int[totalBits];
        for(int i=0;i<totalBits;i++){
            received[i] = sc.nextInt();
        }

        int[] checkRem = divide(received.clone(),divisor);
        boolean error = false;
        for(int bit: checkRem){
            if(bit!=0){
                error = true;
                break;
            }
        }

        if(error){
            System.out.println("Error detected in the received code.");
        }else{
            System.out.println("No error detected.");
        }
        sc.close();
    }

    static int[] divide(int[] dividend,int[] divisor){
        int cur = 0;
        while((dividend.length - cur) >= divisor.length){
            if(dividend[cur] == 1){
                for(int i=0;i<divisor.length;i++){
                    dividend[cur+i] ^= divisor[i];
                }
            }
            cur++;
        }
        return dividend;
    }

    static void printArray(int[] arr){
        for(int bit: arr){
            System.out.print(bit);
        }
        System.out.println();
    }
}

