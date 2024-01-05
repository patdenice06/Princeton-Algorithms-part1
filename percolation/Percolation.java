/* *****************************************************************************
 *  Compilation:  javac-algs4 Percolation.java
 *  Execution:    java-algs4 Percolation
 *
 * Patrick GIRAUD
 * patrickgiraud@hotmail.com
 *
 * This program models a percolation system using an n-by-n grid of sites. Each
 * site  is either open or blocked. A full site is an open site that can be
 * connected to an open site in the top row via a chain of neighboring
 * (left, right, up, down) open  sites. We say the system percolates if there is
 * a full site in the bottom row. In other words, a system percolates if we fill
 * all open sites connected to the top row and that process fills some open site
 * on the bottom row. (For the insulating/metallic materials example, the open
 * sites correspond to metallic materials, so that a system that percolates has
 * a metallic path from top to bottom, with full sites conducting. For the
 * porous substance example, the open sites correspond to empty space through
 * which water might flow, so that a system that percolates lets water fill
 * open sites, flowing from top to bottom.)
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n, top; // bottom;
    private int nbOfOpenSites;
    private final boolean[][] sites; // Stores is the grid open or not at position i, j
    private WeightedQuickUnionUF wuf; // WQUF data type used
    private boolean isPercolate;
    // To avoid backwash
    private boolean isFirstBtmSiteFull;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException("n must be > 0");

        this.n = n;
        sites = new boolean[n][n];
        nbOfOpenSites = 0;
        top = n * n;
        isPercolate = false;
        isFirstBtmSiteFull = false;
        wuf = new WeightedQuickUnionUF(n * n + 1);
    }

    /**
     * @param row   row index
     * @param col   column index
     * @param value a boolean
     */
    /**
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return nbOfOpenSites;
    }

    /**
     * Open a blocked site and link it to its open neighbors
     *
     * @param row Row index of the site to open
     * @param col Col index of the site to open
     */
    public void open(int row, int col) {
        // valid values are in range [1, N])
        if (validate(row, col, true)) {
            if (!isOpen(row, col)) {
                sites[row - 1][col - 1] = true;
                nbOfOpenSites++;

                // Connect first row site to virtual TOP site
                if (row == 1)
                    wuf.union(top, encode(row, col));

                linkNeighbors(row - 1, col, row, col);
                linkNeighbors(row + 1, col, row, col);
                linkNeighbors(row, col + 1, row, col);
                linkNeighbors(row, col - 1, row, col);

            }
        }
    }

    /**
     * @param rowN row of the neighbor site
     * @param colN column of the neighbor site
     * @param rowO row of the open site
     * @param colO column of the open site
     */
    private void linkNeighbors(int rowN, int colN, int rowO, int colO) {
        if (validate(rowN, colN, false) && isOpen(rowN, colN)) {
            wuf.union(encode(rowO, colO), encode(rowN, colN));
            // As soon as the first bottom site becomes full the system percolates
            // and avoid backwash.
            // Check for the first full bottom site
            if (!isFirstBtmSiteFull) {
                for (int j = 1; j <= n; j++) {
                    if (isFull(n, j)) {
                        isFirstBtmSiteFull = true;
                        isPercolate = true;
                        return;
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return ((validate(row, col, true))
                && (sites[row - 1][col - 1]));
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return ((validate(row, col, true))
                && (isOpen(row, col)
                && wuf.find(encode(row, col)) == wuf.find(top)));
    }

    // does the system percolate?

    /**
     * The system percolates iff virtual top site is connected to virtual bottom site
     *
     * @return true if the system percolates
     */
    public boolean percolates() {
        return isPercolate;
    }

    /**
     * @param row in [1,n]
     * @param col in [1,n]
     */
    private boolean validate(int row, int col, boolean throwEx) {
        if (row < 1 || row > n || col < 1 || col > n) {
            if (throwEx)
                throw new IllegalArgumentException(
                        "row or column index must be between 1 and " + n);

            return false;
        }

        return true;
    }

    /**
     * Map from a 2-dimensional (row, column) pair to a 1-dimensional union find
     * object index.
     *
     * @param row row index
     * @param col col index
     * @return The 1-dimensional union find object index
     */
    private int encode(int row, int col) {
        row -= 1;
        col -= 1;
        return (row * n) + col;
    }


    // test client (optional)
    public static void main(String[] args) {

    }
}
