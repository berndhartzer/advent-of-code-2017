import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class CorruptionChecksum {

  public static void main(String args[]) throws Exception {

    // ArrayList to hold input
    ArrayList<String> rows = new ArrayList<>();

    // Read input
    FileInputStream inputFile = new FileInputStream("input.txt");
    Scanner sc = new Scanner(inputFile);

    while (sc.hasNextLine()) {
      rows.add(sc.nextLine());
    }

    // Format input
    int formattedSpreadsheet[][] = formatSpreadsheet(rows);

    int partOneSolved = partOneChecksum(formattedSpreadsheet);
    int partTwoSolved = partTwoChecksum(formattedSpreadsheet);

    System.out.println("Part one solved checksum: " + partOneSolved);
    System.out.println("Part two solved checksum: " + partTwoSolved);

  }

  /**
   * Calculate checksum from a given spreadsheet for part one
   */
  public static int partOneChecksum(int[][] input) {

    int checksum = 0;

    // Go through each row of the spreadsheet
    for (int i = 0; i < input.length; i++){

      // Sort array, so the numbers are ascending
      Arrays.sort(input[i]);

      int min = input[i][0];
      int max = input[i][input.length - 1];

      int diff = max - min;

      checksum += diff;

    }

    return checksum;

  }

  /**
   * Calculator checksum from a given spreadsheet for part two
   */
  public static int partTwoChecksum(int[][] input) {

    int checksum = 0;

    // For each row
    for (int i = 0; i < input.length; i++){

      // For each number in the row
      for (int j = 0; j < input.length; j++){

        int thisNumber = input[i][j];

        // For each other number in the same row
        for (int k = 0; k < input[i].length; k++){

          int comparisonNumber = input[i][k];

          // If we're not comparing the same number to itself, and this number 
          // divided by the comparison number results in a whole number
          if (k != j && thisNumber % comparisonNumber == 0){

            checksum += thisNumber / comparisonNumber;

          }

        }

      }

    }

    return checksum;

  }

  /**
   * Helper function to format a spreadsheet, an array of strings, into a 2 dimensional int array
   */
  public static int[][] formatSpreadsheet(ArrayList<String> rows) {

    int numRows = rows.size();
    int numColumns = rows.get(0).split("\t").length;

    // Create 2 dimensional array with the necessary number of rows and columns
    int formattedSpreadsheet[][] = new int[numRows][numColumns];

    // From rows of strings, fill the 2d array with int values
    for (int i = 0; i < numRows; i++) {

      String splitRow[] = rows.get(i).split("\t");

      for (int j = 0; j < numColumns; j++) {

        formattedSpreadsheet[i][j] = Integer.parseInt(splitRow[j]);

      }

    }

    return formattedSpreadsheet;

  }

}
