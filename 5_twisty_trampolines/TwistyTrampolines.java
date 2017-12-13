import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class TwistyTrampolines {

  public static void main(String args[]) throws Exception {

    // ArrayList to hold input
    ArrayList<Integer> rowsPartOne = new ArrayList<>();
    ArrayList<Integer> rowsPartTwo = new ArrayList<>();

    // Read input
    FileInputStream inputFile = new FileInputStream("input.txt");
    Scanner sc = new Scanner(inputFile);

    while (sc.hasNextLine()) {
      rowsPartOne.add(Integer.parseInt(sc.nextLine()));
    }

    // Create copy of input
    for (int i = 0; i < rowsPartOne.size(); i++) {
      rowsPartTwo.add(rowsPartOne.get(i));
    }

    int partOneStepsTaken = jumpThroughList(rowsPartOne, true);
    int partTwoStepsTaken = jumpThroughList(rowsPartTwo, false);

    System.out.println("Steps taken: " + partOneStepsTaken);
    System.out.println("Steps taken: " + partTwoStepsTaken);

  }

  /**
   * Jump through a list of steps, and return the number of steps it takes to 
   * leave the list. If partOne; increment every step taken by 1, if !partOne;
   * increment every step taken by 1, unless the offset is greater than or equal
   * to 3; then decrement by 1
   */
  public static int jumpThroughList(ArrayList<Integer> steps, boolean partOne) {

    int numSteps = 0;
    int position = 0;

    while (position >= 0 && position < steps.size()) {

      int offset = steps.get(position);

      int change = (!partOne && steps.get(position) >= 3) ? -1 : 1;

      steps.set(position, steps.get(position) + change);

      position += offset;

      numSteps++;

    }

    return numSteps;

  }

}
