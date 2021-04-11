import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

  public static List<String> readFile(String path) throws FileNotFoundException {
    List<String> res = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(new FileInputStream(path));
      while (scanner.hasNextLine()) {
        res.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println(path + " was not found");
      throw new FileNotFoundException();
    }
    return res;
  }
}
