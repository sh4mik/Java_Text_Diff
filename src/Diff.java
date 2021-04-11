import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Pair;

public class Diff {

  public static List<Pair> LCS(List<String> first, List<String> second) {
    int[][] length = new int[first.size() + 1][second.size() + 1];
    Pair[][] path = new Pair[first.size() + 1][second.size() + 1]; // because int[2][10] takes less memory than int[10][2]

    // build length dynamic matrix
    for (int i = 1; i < first.size() + 1; i++) {
      for (int j = 1; j < second.size() + 1; j++) {
        if (first.get(i - 1).equals(second.get(j - 1))) {
          length[i][j] = length[i - 1][j - 1] + 1;
          path[i][j] = new Pair(i - 1, j - 1);
        } else {
          if (length[i-1][j] > length[i][j-1]) {
            length[i][j] = length[i-1][j];
            path[i][j] = new Pair(i - 1, j);
          } else {
            length[i][j] = length[i][j - 1];
            path[i][j] = new Pair(i, j - 1);
          }
        }
      }
    }
    //pathDebug(path, first.size(), second.size());

    // find path (longest sequence)
    int i = first.size();
    int j = second.size();
    List<Pair> result = new ArrayList<>();
    while (length[i][j] != 0) {
      int prevI = path[i][j].getFirst();
      int prevJ = path[i][j].getSecond();
      if (prevI == i - 1 && prevJ == j - 1) {
        result.add(new Pair(prevI, prevJ));
      }
      i = prevI;
      j = prevJ;
    }

    Collections.reverse(result);
    return result;
  }

  private static void pathDebug(Pair[][] path, int first, int second) {
    System.out.print("    ");
    for (int i = 0; i < second + 1; ++i) {
      System.out.print("[" + i + "] ");
    }
    System.out.println();
    for (int i = 0; i < first + 1; ++i) {
      System.out.print("[" + i + "] ");
      for (int j = 0; j < second + 1; ++j) {
        if (path[i][j] == null) {
          System.out.print(0 + ":" + 0 + " ");
        } else {
          System.out.print((path[i][j].getFirst() +":" + path[i][j].getSecond() + " "));
        }
      }
      System.out.println();
    }
  }
}
