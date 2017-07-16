import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private final double x[];
	private final int trials;	
	private final double mean,stdev,conlo,conhi;
   public PercolationStats(int n, int trials)
   {	
	   if(n<1 || trials<1)
		   throw new IllegalArgumentException("n or trials less than 1.");
	   this.trials=trials;
		x=new double[trials];
		Percolation P;
		for(int i=0;i<trials;++i)
		{	
			P=new Percolation(n);
			int row,col;
			while(!P.percolates())
			{
				row=StdRandom.uniform(n)+1;
				col=StdRandom.uniform(n)+1;
				P.open(row,col);
			}
			x[i]=(double)P.numberOfOpenSites()/(n*n);
		}
		mean=mean();
		stdev=stddev();
		conlo=confidenceLo();
		conhi=confidenceHi();
   }   // perform trials independent experiments on an n-by-n grid
   
   public double mean()
   {
	   return StdStats.mean(x);
   }   // sample mean of percolation threshold
   public double stddev()
   {
	   return StdStats.stddev(x);
   }   // sample standard deviation of percolation threshold
   public double confidenceLo()
   {
	   return mean-(1.96*stdev)/Math.sqrt(trials);
   }   // low  endpoint of 95% confidence interval
   public double confidenceHi()
   {
	   return mean+(1.96*stdev)/Math.sqrt(trials);
   }   // high endpoint of 95% confidence interval

   public static void main(String[] args)
   {	
	   int n,trials;
	   n=Integer.parseInt(args[0]);
	   trials=Integer.parseInt(args[1]);
	   PercolationStats P=new PercolationStats(n,trials);
	   StdOut.println("mean                    = " + P.mean);
	   StdOut.println("stddev                  = " + P.stdev);
	   StdOut.println("95% Confidence Interval = [" + P.conlo + ", " + P.conhi + "]");
   }   // test client (described below)
}