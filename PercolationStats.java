/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    int n;
    int trials;
    int[] all_sites;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if( n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.all_sites = new int[trials];
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i;
        }
        for (int i =0; i < trials; i++) {
            boolean percs = false;
            Percolation percolation = new Percolation(n);
            while (!percs) {
                int row = StdRandom.discrete(numbers);
                int col = StdRandom.discrete(numbers);
                percolation.open(row, col);
                percs = percolation.percolates();
            }
            int openSites = percolation.numberOfOpenSites();
            all_sites[i] = openSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(all_sites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(all_sites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1.1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1.1;
    }
    public static void main(String[] args) {
        int n =  Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[2]);
    }
}
