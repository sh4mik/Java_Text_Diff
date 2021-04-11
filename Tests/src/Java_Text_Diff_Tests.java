import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;


public class Java_Text_Diff_Tests {
  @Test
  public void diffTest(){
    List<String> first = List.of("hello", "my", "name", "is", "Shamil", "Mustafaev");
    List<String> second = List.of("hello", "my", "came", "is", "Great", "Vasya", "Mustafaev");

    List<Integer> expected = List.of(0, 1, 3, 5);
    assert expected.equals(Diff.LCS(first, second));
  }

  @Test
  public void diffTest2(){
    List<String> first = List.of("a", "b", "c", "d", "e", "f");
    List<String> second = List.of("c", "e", "f");

    List<Integer> expected = List.of(2, 4, 5);
    assert expected.equals(Diff.LCS(first, second));
  }

  @Test
  public void diffTest3(){
    List<String> first = List.of("a", "b", "c", "d", "e", "f");
    List<String> second = List.of("k", "l", "m");

    List<Integer> expected = List.of();
    assert expected.equals(Diff.LCS(first, second));
  }
}
