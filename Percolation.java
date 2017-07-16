import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
	private boolean open[];
	private final int n;
	private int noofopen=0;
	private final WeightedQuickUnionUF Q;
	
   public Percolation(int n) 
   {
	   if(n<=0)
		   throw new IllegalArgumentException("n or trials less than 1.");
	   this.n=n;
	   open=new boolean[n*n+2];
	   Q=new WeightedQuickUnionUF(n*n+2);
   }   // create n-by-n grid, with all sites blocked
   
   
   public void open(int row, int col)
   {
	   validate(row,col);
	   if(!isOpen(row,col))
	   {   
		open[twoToOne(row,col)]=true;
		++noofopen;
	   if(row==1)
	   {
		   Q.union(twoToOne(row,col),0);
	   }
	   if(row==n)
	   {
		   Q.union(twoToOne(row,col),n*n+1);
	   }
	       if(!(row+1 < 1 || row+1 > n || col<1 || col>n) && isOpen(row+1,col))
			   Q.union(twoToOne(row+1,col),twoToOne(row,col));
		   if(!(row < 1 || row > n || col+1<1 || col+1>n) && isOpen(row,col+1))
			   Q.union(twoToOne(row,col+1),twoToOne(row,col));
		   if(!(row < 1 || row > n || col-1<1 || col-1>n) && isOpen(row,col-1))
			   Q.union(twoToOne(row,col-1),twoToOne(row,col));
		   if(!(row-1 < 1 || row-1 > n || col<1 || col>n) && isOpen(row-1,col))
			   Q.union(twoToOne(row-1,col),twoToOne(row,col));
	   }
	   
   }   // open site (row, col) if it is not open already  
   public boolean isOpen(int row, int col)
   {
	   validate(row,col);
	   return open[twoToOne(row,col)];
   }   // is site (row, col) open?  
   public boolean isFull(int row, int col)
   {
	    validate(row,col);
		return (Q.connected(twoToOne(row,col),0));
   }   // is site (row, col) full?  
   public int numberOfOpenSites()
   {
	   return noofopen;
   }   // number of open sites
   public boolean percolates()
   {
	   return Q.connected(0,n*n+1);
   }   // does the system percolate?
     private int twoToOne(int a,int b)
   {
	   return (a-1)*n+b;
   }   
   private void validate(int row,int col)
   {
	     if (row < 1 || row > n || col<1 || col>n) {
            throw new IllegalArgumentException("row or col is not between 1 and " + n);  
        }
   }
 
   public static void main(String[] args)
   {
	   /*
	   Percolation P=new Percolation(4);
	   P.open(1,1);
	   P.open(2,1);
	   P.open(2,2);
	   P.open(3,2);
	   P.open(3,3);
	   P.open(4,3);
	   if(P.percolates())
		   StdOut.println("Yes" + P.numberOfOpenSites());
	   */
   }   //test client (optional)
 
}