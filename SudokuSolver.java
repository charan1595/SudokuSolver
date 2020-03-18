package personal.saicharan.practice;

public class SudokuSolver {

  int n;
  int totalSize;
  int blockSize;
  int[] r, b, c;

  public SudokuSolver(int n) {
    this.n = n;
    blockSize = n * n;
    this.totalSize = blockSize * blockSize;
    r = new int[blockSize];
    b = new int[blockSize];
    c = new int[blockSize];
  }


  public boolean solve(int[] a) {
    for (int i = 0; i < totalSize; i++) {
      if (a[i] != 0) {
        int rt, bt, ct, j = a[i];
        rt = i / blockSize;
        ct = i % blockSize;
        bt = (rt / n) * n + ct / n;
        r[rt] |= (1 << j);
        c[ct] |= (1 << j);
        b[bt] |= (1 << j);
      }
    }
    return solveHelper(a, 0);
  }

  private boolean solveHelper(int[] a, int i) {
    if (i == totalSize) {
      return true;
    }
    if (a[i] != 0) {
      return solveHelper(a, i + 1);
    }
    int rt, bt, ct;
    rt = i / blockSize;
    ct = i % blockSize;
    bt = (rt / n) * n + ct / n;
    for (int j = 1; j <= blockSize; j++) {
      if (((r[rt] | c[ct] | b[bt]) & (1 << j)) == 0) {
        a[i] = j;
        r[rt] |= (1 << j);
        c[ct] |= (1 << j);
        b[bt] |= (1 << j);
        if (solveHelper(a, i + 1)) {
          return true;
        } else {
          r[rt] ^= (1 << j);
          c[ct] ^= (1 << j);
          b[bt] ^= (1 << j);
        }
      }
    }
    a[i] = 0;
    return false;
  }

  public int solutionCount(int[] a) {
    for (int i = 0; i < totalSize; i++) {
      if (a[i] != 0) {
        int rt, bt, ct, j = a[i];
        rt = i / blockSize;
        ct = i % blockSize;
        bt = (rt / n) * n + ct / n;
        r[rt] |= (1 << j);
        c[ct] |= (1 << j);
        b[bt] |= (1 << j);
      }
    }
    return solutionCountHelper(a, 0);
  }

  private int solutionCountHelper(int[] a, int i) {
    if (i == totalSize) {
      return 1;
    }
    if (a[i] != 0) {
      return solutionCountHelper(a, i + 1);
    }
    int rt, bt, ct;
    rt = i / blockSize;
    ct = i % blockSize;
    bt = (rt / n) * n + ct / n;
    int count = 0;
    for (int j = 1; j <= blockSize; j++) {
      if (((r[rt] | c[ct] | b[bt]) & (1 << j)) == 0) {
        a[i] = j;
        r[rt] |= (1 << j);
        c[ct] |= (1 << j);
        b[bt] |= (1 << j);
        count += solutionCountHelper(a, i + 1);
        r[rt] ^= (1 << j);
        c[ct] ^= (1 << j);
        b[bt] ^= (1 << j);
        a[i] = 0;
      }
    }
    a[i] = 0;
    return count;
  }


  public static void main(String[] args) {
    int n = 3;
    int[] arr = new int
        []{
        0, 0, 0, 0, 0, 0, 8, 0, 0,
        0, 0, 0, 0, 1, 3, 4, 9, 0,
        0, 9, 0, 5, 0, 0, 0, 2, 7,

        5, 0, 0, 0, 4, 0, 0, 6, 0,
        0, 0, 9, 0, 0, 1, 0, 5, 0,
        2, 0, 0, 8, 0, 5, 9, 4, 0,

        8, 2, 7, 0, 9, 0, 5, 0, 0,
        3, 5, 4, 1, 0, 0, 6, 7, 9,
        9, 6, 1, 3, 5, 7, 2, 8, 4
    };
    SudokuSolver s = new SudokuSolver(n);
    System.out.println(s.solutionCount(arr));
    System.out.println(s.solve(arr));
    printSudoku(arr, s.blockSize, s.n);


  }

  private static void printSudoku(int[] arr, int blockSize, int n) {
    for (int i = 0; i < n * n; i++) {
      if (i % n == 0) {
        System.out.println();
      }
      for (int j = 0; j < n * n; j++) {
        if (j % n == 0) {
          System.out.printf(" ");
        }
        System.out.print(arr[i * blockSize + j]);
      }
      System.out.println();
    }
  }
}
