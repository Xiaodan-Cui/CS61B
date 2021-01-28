package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int[][] grid;
    public int R;
    public int C;
    public Percolation (int N){
        if (N<=0){
            throw new IllegalArgumentException("Not Valid N");
        }
        grid= new int[N][N];
        R=N;
        C=N;
    }
    public void open(int row, int col){
        if (row<0 || row>=R || col<0 || col >=C){
            throw new IndexOutOfBoundsException("Not Valid Row or Col");
        }
        if (!isOpen(row,col)){
            grid[row][col]=1;
        }
    }
    public boolean isOpen(int row,int col){
        if (row<0 || row>=R || col<0 || col >=C){
            throw new IndexOutOfBoundsException("Not Valid Row or Col");
        }
        return grid[row][col]==1;
    }
    public boolean isFull(int row,int col){
        if (row<0 || row>=R || col<0 || col >=C){
            throw new IndexOutOfBoundsException("Not Valid Row or Col");
        }
        return grid[row][col]==0;
    }
    public int numberOfOpenSites(){
        int NOO=0;
        for (int i=0;i<R;i++){
            for (int j=0;j<R;j++){
                if (isOpen(i,j)){
                    NOO+=1;
                }
            }
        }
        return NOO;
    }
    public boolean percolates(){
        int n=R*C+1;
        WeightedQuickUnionUF UF=new WeightedQuickUnionUF(n);
        for (int i=0;i<R;i++){
            for (int j=0;j<R;j++){
                if (isOpen(i, j)){
                    if (i==0){
                        UF.union(R*C,i*C+j);}
                    if (i-1>=0 && isOpen(i-1, j)){
                        UF.union((i-1)*C+j,i*C+j);}
                    if (j-1>=0 && isOpen(i, j-1)){
                        UF.union(i*C+j-1,i*C+j);}
                }
            }
        }
        for (int j=0;j<C;j++){
            if (UF.find((R-1)*C+j)==R*C){
                return true;
            }
        }
        return  false;
    }
    public static void main(String[] args){

    }


}
