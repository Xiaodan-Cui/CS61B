package hw2;

import java.util.Random;

public class PercolationStats {
    private Percolation A;
    public int number;
    public double [] PSRseries;
    public double mean;
    public double stddev;

    public PercolationStats(int N, int T,PercolationFactory pf){
        if (N<=0 || T<=0) {
            throw new IllegalArgumentException("Not Valid N");
        }
        A = pf.make(N);
        number=T;
        PSRseries= new double [T];
    }
    public double PSR (){
        Random random = new Random();
        while(!A.percolates()){
            int r= random.nextInt(A.R);
            int c= random.nextInt(A.C);
            if (!A.isOpen(r,c)){
                A.open (r,c); }
        }
        return (double)A.numberOfOpenSites()/(double) (A.R*A.C);
    }

    public double[] PSRseries(){
        for (int i=0; i<number; i++){
            double psr=PSR();
            PSRseries[i]=psr;
        }
        return PSRseries;
    }

    public double mean(){
        double sum =0.0;
        for (int i =0; i<number; i++){
            sum+=PSRseries[i];
        }
        mean=sum/number;
        return mean;
    }
    public double stddev(){
        double sum=0.0;
        for (int i =0; i<number; i++){
            sum+=Math.pow((PSRseries[i]-mean), 2);
        }
        stddev=sum/(number-1);
        return stddev;
    }
    public double confidenceLow(){
        return mean-1.96*stddev/Math.sqrt(number);

    }
    public double confidenceHigh(){
        return mean+1.96*stddev/Math.sqrt(number);
    }
    public static void main(String[] args){

    }
}
