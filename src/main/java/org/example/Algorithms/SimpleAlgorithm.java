
package org.example.Algorithms;
import org.example.PrimeAlgorithms;
import org.example.Range;

public class SimpleAlgorithm implements PrimeAlgorithms {


    @Override
    public void run(Range range) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i= range.getLow() ; i<= range.getHigh() ; i++){
            int sqrt = (int) Math.sqrt(i);
            int counter =0;

            for (int j=2 ; j<= sqrt ; j++){
                if (i % j == 0) counter++;
            }
            if ( i > 1 && counter == 0){
                System.out.print(i + " ");
            }
        }
    }
}
