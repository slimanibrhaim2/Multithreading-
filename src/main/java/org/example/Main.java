package org.example;
import org.example.Algorithms.EratosthenesAlgorithm;
import org.example.Algorithms.SimpleAlgorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                
                To find the prime numbers in specific range press 1.
                to see some statistics about the simple algorithm press 2.
                
                """);
        int ch = scanner.nextInt();

        if (ch == 1){
            // Entering the Range:
            System.out.print("Enter the Low range: ");
            int low = scanner.nextInt();
            System.out.print("Enter the High range: ");
            int high = scanner.nextInt();
            Range range = new Range(low, high);

            // Entering the number of threads
            System.out.print("Enter the number of threads you want: ");
            int threadNumber = scanner.nextInt();

            //Choosing the Algorithms:
            System.out.print("""
                
                You have this algorithms:
                To choose a "SimpleAlgorithm" press 1.
                To choose a "EratosthenesAlgorithm" press 2.
                To Exit the program press 3.
                
                """);
            int algorithmNumber = scanner.nextInt();
            PrimeAlgorithms algorithm = null;
            if(algorithmNumber == 1){
                algorithm = new SimpleAlgorithm();
            }
            if(algorithmNumber == 2){
                algorithm = new EratosthenesAlgorithm();
            }

            // How to split the range
            System.out.print("""
                
                You have this options:
                To divide the range into n equal part press 1.
                To divide the range manually press 2.
                To Exit the program press 3.
                
                """);
            int choice = scanner.nextInt();
            List<AlgorithmsCalculater> threads = new ArrayList<>();

            if (choice == 1){
                int length = 1+ range.getHigh() - range.getLow();
                int partSize= length / threadNumber;

                for ( int i=0 ; i<threadNumber ; i++){
                    int start = range.getLow() + i * partSize;
                    int end = (i == threadNumber-1 ) ? range.getHigh() : start+partSize-1;
                    Range subRange = new Range(start, end);
                    threads.add(new AlgorithmsCalculater(subRange, algorithm));
                    threads.getLast().start();
                }
            }
            if (choice ==2 ){
                for(int i=0 ;i < threadNumber; i++){
                    System.out.print("Enter the low value for the range "+i+" : ");
                    int subLow = scanner.nextInt();
                    System.out.print("Enter the high value for the range "+i+" : ");
                    int subHigh = scanner.nextInt();
                    Range subRange = new Range(subLow, subHigh);
                    threads.add(new AlgorithmsCalculater(subRange, algorithm));
                    threads.getLast().start();
                }
            }
            for (int i=0; i<threadNumber ; i++){
                try{
                    threads.get(i).join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }

        if (ch ==2){
            PrimeAlgorithms algorithm = new SimpleAlgorithm();

            long[][] statiscs = new long[6][16];
            //Set the ranges for the testing
            List<Range> ranges = new ArrayList<>();
            for (int i=1 ; i<= 5; i++){
                //Start with [0 - 100] end with [0 - 1000000]
                int h = (int) Math.pow(10, (i+1) );
                Range range = new Range(0, h);
                ranges.add(range);
            }

            //Set the number of threads for the testing
            List<Integer> threadNumber= new ArrayList<>();
            for (int i=1 ; i<= 16; i++){
                threadNumber.add(i);
            }
            int ran=0;
            for (Range range : ranges){
                for (int threadNum : threadNumber){

                    int length = range.getHigh() - range.getLow();
                    int partSize = length / threadNum;
                    List<AlgorithmsCalculater> threads = new ArrayList<>();
                    long startTime = System.currentTimeMillis();

                    for(int thN=0 ;thN< threadNum ; thN ++){
                        int start = range.getLow() + thN * partSize;
                        int end = (thN == threadNum - 1) ? range.getHigh() : (start + partSize);
                        Range subRange = new Range(start, end);
                        threads.add(new AlgorithmsCalculater(subRange, algorithm));
                        threads.getLast().start();
                    }
                    for(int thN=0 ;thN< threadNum ; thN ++){
                        try{
                            threads.get(thN).join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    long endTime = System.currentTimeMillis();
                    statiscs[ran][threadNum-1]= endTime- startTime;
                }
                ran++;
            }
            System.out.println("\n\n\n\n\n\n\n");
            String[] headers= {"" , "1 TH", "2 TH", "3 TH", "4 TH", "5 TH", "6 TH", "7 TH", "8 TH", "9 TH", "10 TH", "11 TH", "12 TH", "13 TH", "14 TH", "15 TH", "16 TH"};
            String[] ragneRow={"[0-1e2]","[0-1e3]","[0-1e4]","[0-1e5]","[0-1e6]"};

            for (String header : headers) {
                System.out.printf("%8s", header);
            }
            System.out.println();

            for (int i =0 ;i< 5 ; i++){
                System.out.printf("%8s" ,ragneRow[i]);
                for (int j=0 ; j< 16; j++){
                    System.out.printf("%8s", statiscs[i][j]);
                }
                System.out.println();
            }
        }
    }
}