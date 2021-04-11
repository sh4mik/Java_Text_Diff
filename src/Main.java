import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    if (args.length != 2) {
      System.out.println("Wrong input arguments!");
    }
    Path path1 = Paths.get(args[0]);
    Path path2 = Paths.get(args[1]);

    List<String> file1 = Reader.readFile(path1.toString());
    List<String> file2 = Reader.readFile(path2.toString());

    try {
      HTMLWriter.write(file1, file2, Diff.LCS(file1, file2), path1.toString(), path2.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
