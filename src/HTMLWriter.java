import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import utils.Pair;

public class HTMLWriter {

  enum Color {
    NONE("#FFFFFF"),
    RED("#FF8E8E"),
    GREEN("#B4FB95"),
    BLUE("#76DDFF");

    private String code;

    Color(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  private static final String HTML_BEGIN = "<!DOCTYPE html>\n"
      + "<html>\n"
      + "    <head>\n"
      + "        <meta charset=\"utf-8\">\n"
      + "        <title>TextDiff</title>\n"
      + "    </head>\n"
      + "    <body>\n"
      + "    \t<div align=\"right\"><small><i>JetBrains Interships: Улучшение поддержки Jupyter ноутбуков в PyCharm</i></small></div>\n"
      + "    </body>"
      + "    <body>\n"
      + "        <h2 align=\"center\" style=\"color:Black\">Text Diff</h2>\n"
      + "    </body>\n"
      + "    <body>\n"
      + "        <table border=\"1\" style=\"table-layout:fixed; border-collapse: collapse;\">";

  private static final String HTML_END = "        </table>\n"
      + "    </body>\n"
      + "</html>";

  private static String toTableColumn(int number1, String line1, Color color1) {
    String stringNumber = number1 == -1 ? "" : Integer.toString(number1);
    return "<td>" + stringNumber + "</td> <td style=\"white-space:pre-wrap; background-color:"
        + color1.getCode() + ";\">"
        + line1 + "</td> ";
  }

  private static String toTableLine(String line1, String line2) {
    return "            <tr>\n" + "                " + line1 + line2 + "            </tr>\n";
  }

  private static boolean realEquals(String line1, String line2) {
    if (line1 == line1)
      return true;
    if (line1.length() != line2.length())
      return false;
    for (int i = 0; i < line1.length(); i++) {
      if (line1.charAt(i) != line2.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public static void write(List<String> file1, List<String> file2, List<Pair> sequence,
      String fileName1, String fileName2)
      throws IOException {
    FileWriter writer = null;
    try {
      writer = new FileWriter("./diff.html");
    } catch (IOException e) {
      System.out.println("fileWriter(./diff.html) error");
      throw new IOException();
    }
    writer.write(HTML_BEGIN);
    writer.write("            <tr>\n"
        + "                <td><h4>№</h4></td> <td style=\"width:50%;white-space:pre-wrap;\"><h4> " + fileName1
        + "</h4></td> <td><h4>№</h4></td> <td style=\"width:50%;white-space:pre-wrap;\"><h4> " + fileName2
        + "</h4></td>\n" + "            </tr>\n");

    for (int i = 0; i <= sequence.size(); ++i) {
      int indexFile1 = i == sequence.size() ? file1.size() : sequence.get(i).getFirst();
      int indexFile2 = i == sequence.size() ? file2.size() : sequence.get(i).getSecond();
      int indexPrevFile1 = i == 0 ? 0 : sequence.get(i - 1).getFirst();
      int indexPrevFile2 = i == 0 ? 0 : sequence.get(i - 1).getSecond();
      int j;
      for (j = 1; j < Integer.min(indexFile1 - indexPrevFile1, indexFile2 - indexPrevFile2);
          j++) {
        writer.write(toTableLine(
            toTableColumn(indexPrevFile1 + j, file1.get(indexPrevFile1 + j), Color.BLUE),
            toTableColumn(indexPrevFile2 + j, file2.get(indexPrevFile2 + j), Color.BLUE)));
      }
      for (; j <= Integer.max(indexFile1 - indexPrevFile1, indexFile2 - indexPrevFile2)
          - Integer.min(indexFile1 - indexPrevFile1, indexFile2 - indexPrevFile2) + 1; j++) {
        if (indexPrevFile1 + j < indexFile1) {
          writer.write(toTableLine(
              toTableColumn(indexPrevFile1 + j, file1.get(indexPrevFile1 + j), Color.RED),
              toTableColumn(-1, "", Color.NONE)));
        }
        if (indexPrevFile2 + j < indexFile2) {
          writer.write(toTableLine(
              toTableColumn(-1, "", Color.NONE),
              toTableColumn(indexPrevFile2 + j, file2.get(indexPrevFile2 + j), Color.GREEN)));
        }
      }
      if (i != sequence.size()) {
        if (realEquals(file1.get(indexFile1), file2.get(indexFile2))) {
          writer.write(toTableLine(
              toTableColumn(indexFile1, file1.get(indexFile1), Color.NONE),
              toTableColumn(indexFile2, file2.get(indexFile2), Color.NONE)
          ));
        } else {
          writer.write(toTableLine(
              toTableColumn(indexFile1, file1.get(indexFile1), Color.BLUE),
              toTableColumn(indexFile2, file2.get(indexFile2), Color.BLUE)
          ));
        }

      }
    }

    writer.write(HTML_END);
    writer.close();
  }

}
