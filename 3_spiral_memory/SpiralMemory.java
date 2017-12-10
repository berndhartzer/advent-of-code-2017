public class SpiralMemory {

  public static void main(String args[]) throws Exception {

    int mainInput = 277678;

    int[] inputs = {1, 12, 17, 23, 45, 1024, mainInput};
   
    for (int i = 0; i < inputs.length; i++) {

      System.out.println("location: " + inputs[i] + " manhattan distance: ");
      System.out.println(manhattanDistance(inputs[i]));

    }

  }

  /**
   * Calculate the manhattan distance of a given int from the centre of a 
   * number spiral
   */
  public static int manhattanDistance(int input) {

    // Get square root of input, to determine how many values are on the same row
    double sqRootOfInput = Math.sqrt(input);

    // Round up the square root, to determine how many values are on this row
    int layerSideSize = (int) Math.ceil(sqRootOfInput);

    // If the rounded up square root is even, we are on the top or right side of the square
    if (layerSideSize % 2 == 0) {

      // Correct the layerSideSize
      layerSideSize++;
    }

    // Determine the distance to the centre from the bottom right corner of the row
    int distanceFromBottomRightCornerOfLayer = layerSideSize - 1;

    // Determine how many layers our row is from the centre
    int layersFromCentre = distanceFromBottomRightCornerOfLayer / 2;

    // Determine the value on the bottom right corner, which will be a square number
    double numberOnBottomRightCornerOfLayer = Math.pow(layerSideSize, 2);

    // Determine the numbers that are on the middle of each side of the square
    double[] numbersOnCentresOfLayer = {
      numberOnBottomRightCornerOfLayer - layerSideSize / 2,
      (numberOnBottomRightCornerOfLayer - distanceFromBottomRightCornerOfLayer * 1) - layerSideSize / 2, 
      (numberOnBottomRightCornerOfLayer - distanceFromBottomRightCornerOfLayer * 2) - layerSideSize / 2, 
      (numberOnBottomRightCornerOfLayer - distanceFromBottomRightCornerOfLayer * 3) - layerSideSize / 2
    };

    // Value to track our inputs distance from the middle of the row its on
    double closestDistanceToNearestCentre = layerSideSize / 2;

    // Get the closest distance our input is from the middle of each row in the square
    for (int i = 0; i < numbersOnCentresOfLayer.length; i++) {

      double thisDistance = Math.abs(input - numbersOnCentresOfLayer[i]);

      if (thisDistance < closestDistanceToNearestCentre) {
        closestDistanceToNearestCentre = thisDistance;
      }

    }

    double distance = closestDistanceToNearestCentre + layersFromCentre;

    return (int) distance;

  }

}
