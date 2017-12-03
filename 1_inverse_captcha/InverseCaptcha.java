import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class InverseCaptcha {

  public static void main(String args[]) throws Exception {

    // Main test input
    String mainInput = new String(readAllBytes(get("input.txt")));

    // Strip newline chars from main input
    mainInput = mainInput.replace("\n", "").replace("\r", "");

    // Arrays of inputs, including test inputs
    String[] partOneInputs = {"1122", "1111", "1234", "91212129", mainInput};
    String[] partTwoInputs = {"1212", "1221", "123425", "123123", "12131415", mainInput};

    // Part one
    for (int i = 0; i < partOneInputs.length; i++) {

      System.out.println("Part one solution for Captcha " + partOneInputs[i] + ": ");
      System.out.println(solveCaptchaPartOne(formatCaptcha(partOneInputs[i])));
    }

    // Part two
    for (int j = 0; j < partTwoInputs.length; j++) {

      System.out.println("Part two solution for Captcha " + partTwoInputs[j] + ": ");
      System.out.println(solveCaptchaPartTwo(formatCaptcha(partTwoInputs[j])));
    }

  }


  /**
   * Captcha solver for part one
   */
  public static int solveCaptchaPartOne(int[] captcha) {

    int total = 0;

    // Loop through ints
    for (int i = 0; i < captcha.length; i++) {

      // Get the next index, which will be 0 if this is the last number in the array
      int nextIndex = i + 1 > captcha.length - 1 ? 0 : i + 1;

      // If this number matches the next; add it to the total
      if (captcha[i] == captcha[nextIndex]) {
        total += captcha[i];
      }

    }

    return total;

  }

  /**
   * Captcha solver for part two
   */
  public static int solveCaptchaPartTwo(int[] captcha) {

    int total = 0;

    for (int i = 0; i < captcha.length; i++) {

      // Get the middle index of the array (as if it were circular)
      int middleIndex = i + captcha.length / 2;

      // If the middle index is too high, deduct the length of the array (as if it were circular)
      if (middleIndex > captcha.length - 1) {
        middleIndex -= captcha.length;
      }

      // If this number matches the middle number; add it to the total
      if (captcha[i] == captcha[middleIndex]) {
        total += captcha[i];
      }

    }

    return total;

  }

  /**
   * Helper function to convert a string into an array of ints
   */
  public static int[] formatCaptcha(String input) {

    int[] newArr = new int[input.length()];

    for (int i = 0; i < input.length(); i++) {
      newArr[i] = Character.getNumericValue(input.charAt(i));
    }

    return newArr;

  }

}
