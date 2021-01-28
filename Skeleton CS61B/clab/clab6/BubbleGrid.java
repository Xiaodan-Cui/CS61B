public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int R=grid.length;
        int C=grid[0].length;
        int[][] C_grid = new int[R][C];
        int N = C*R;
        int D = darts.length;
        int[] result= new int[D];
        UnionFind UF = new UnionFind(N+1);
        for (int r=0;r<R;r++){
            for (int c=0; c<C; c++){
            C_grid[r][c]=grid[r][c];}
        }
        for (int d=0;d<D;d++){
            C_grid[darts[d][0]][darts[d][1]]=0;
        }
        for (int r=0;r<R;r++){
            for (int c=0; c<C; c++){
                if (r==0 && C_grid[r][c]==1){
                    UF.union(r*C+c,N);}
                else if (r!=0 && C_grid[r-1][c]==1 && C_grid[r][c]==1){
                    UF.union(C*r+c, C*(r-1)+c);
                }
                else if(c>=1 && C_grid[r][c-1]==1 && C_grid[r][c]==1){
                    UF.union(r*C+c,r*C+c-1);
                }
            }
        }

        int d=D-1;
        while (d>=0){
            int b= UF.sizeOf(N);
            int r=darts[d][0];
            int c=darts[d][1];
            if (grid[r][c]==0){
                d-=1;
            }
            else if (r==0){
                UF.union(c,N);
                d-=1;
            }
            else {
                if (grid[r - 1][c] == 1) {
                    UF.union(r* C+c, (r-1) *C+c);
                }
                if (r + 1 < R && grid[r + 1][c] == 1) {
                    UF.union(r*C+c,  (r+1) *C + c);
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    UF.union(r * C + c, r * C+c-1);
                }
                if (c + 1 < C && grid[r][c + 1] == 1) {
                    UF.union(r * C + c, r * C+1 + c);
                }
                d -= 1;
            }
            int f=UF.sizeOf(N);
            result[d+1]=Math.max(0,f-b-1);
            }

        return result;
    }
}
