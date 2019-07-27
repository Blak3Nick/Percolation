import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {
    private int numberOfRows;
    // 0 = closed, 1 = open, 2 = full
    private int [][] grid;
    private int openSites = 0;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.numberOfRows = n;
        int sites = n*n;
        this.grid = new int[n][n];
        weightedQuickUnionUF = new WeightedQuickUnionUF(sites);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if(row == 0) {
            grid[row][col] = 2;
            int site1 = col + (numberOfRows * row);
            int site2 = col + (numberOfRows * (row +1));
            if (grid[row+1][col] == 1) {
                weightedQuickUnionUF.union(site1, site2);
                grid[row+1][col] = 2;
            }

        }
        else {
            if (!isOpen(row, col)) {
                grid[row][col] = 1;
                try {
                    grid[row+1][col] = 1;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // do nothing there are no legal open sites
                }
                try {
                    grid[row][col-1] = 1;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // do nothing there are no legal open sites
                }
                try {
                    grid[row][col+1] = 1;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // do nothing there are no legal open sites
                }

            }

        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        if (grid[row][col] == 1 || grid[row][col] == 2) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        int site1 = col + (row * numberOfRows );

        for (int i=0; i<numberOfRows; i++) {

            if(isOpen(row, col)) {
                if (weightedQuickUnionUF.connected(i, site1)) {
                    return true;
                }
            }

        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        int site1 = (numberOfRows* numberOfRows) -1;
        for (int i=0; i < numberOfRows; i++) {
            if(weightedQuickUnionUF.connected(i,site1)) {
                return true;
            }
            site1 --;
        }
        return false;
    }


    public static void main(String[] args) {


        // Percolation percolation = new Percolation(n);

    }
}
