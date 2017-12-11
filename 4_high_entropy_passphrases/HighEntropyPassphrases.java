import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class HighEntropyPassphrases {

  public static void main(String args[]) throws Exception {

    // ArrayList to hold input
    ArrayList<String> rows = new ArrayList<>();

    // Read input
    FileInputStream inputFile = new FileInputStream("input.txt");
    Scanner sc = new Scanner(inputFile);

    while (sc.hasNextLine()) {
      rows.add(sc.nextLine());
    }

    int partOneValidPassphrases = 0;
    int partTwoValidPassphrases = 0;

    for (int i = 0; i < rows.size(); i++) {

      if (partOnePassphraseIsValid(rows.get(i))) {
        partOneValidPassphrases++;
      }

      if (partTwoPassphraseIsValid(rows.get(i))) {
        partTwoValidPassphrases++;
      }

    }

    System.out.println("Number of valid passphrases for part one: " + partOneValidPassphrases);
    System.out.println("Number of valid passphrases for part two: " + partTwoValidPassphrases);

  }

  /**
   * Check if a passphrase is valid or not by checking to see if all words in
   * the passphrase are unique. Do this by adding each word from the passphrase
   * into a Set, which is a collection that contains no duplicates
   */
  public static boolean partOnePassphraseIsValid(String passphrase) {

    String[] splitPassphrase = passphrase.split(" ");

    Set<String> uniqueWords = new HashSet<String>();

    for (String word : splitPassphrase) {
      uniqueWords.add(word);
    }

    return splitPassphrase.length == uniqueWords.size();

  }

  /**
   * Check if a passphrase is valid or not by making sure that no words in the
   * phrase are anagrams of each other
   */
  public static boolean partTwoPassphraseIsValid(String passphrase) {

    String[] splitPassphrase = passphrase.split(" ");

    Set<String> sortedWords = new HashSet<String>();

    // For each word in passphrase
    for (String word : splitPassphrase) {

      // Create array of chars from the word
      char[] letters = word.toCharArray();

      // Sort the chars
      Arrays.sort(letters);

      // Add the sorted string to the set
      sortedWords.add(new String(letters));

    }

    return splitPassphrase.length == sortedWords.size();

  }

}
