package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  private int[] prior;
  private int index;
  private boolean [] num;
  private boolean over;

  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    // TODO
    prior = new int[20];
    guess = 1000;
    num = new boolean[9000];   
    over = false;
    index = 0;
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    // TODO
    guess = 1000;
    num = new boolean[9000];
    prior = new int[20];
    over = false;
    index = 0;
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    // TODO
    for (int i = 0; i < prior.length; i++) {
      if (n == prior[i]) {
        return true;
      }
    }
    return false;

  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    // TODO    
    return index;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    // TODO
    int num = 0;
    for (int i = 1; i <= a; i = i * 10) {
      if ((a / i) % 10 == (b / i) % 10) {
        num++;
      }
    }
    return num;
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    // TODO
    return over;
  }

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
    if (prior == null) {
      prior = new int[20];
    }
    prior[index] = guess;
    index++;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
    boolean tem = false;
    int [] arr = new int[9000];
    int test = 0;
    if (nmatches == 4) {
      over = true;
      tem = true;
    }
    else {
      for (int i = 0; i < 9000; i++) {        
        if (numMatches(i + 1000, guess) != nmatches) { 
          num[i] = true;
          tem = true; 
        }
      }
      for (int i = 0; i < 9000; i++) {
        if (!num[i]) {
          arr[test] = i + 1000;
          test++;
        }
      }
      if (test == 0) {
        tem = false;
      }
      for (int i = 0; i < 9000; i++) {
        if (num[i] == false) {
          guess = 1000 + i;
          break;
        }
      }   
    }
    return tem;
  }

  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    // TODO
    int [] fake = new int[index];
    if (index == 0) {
      return null;
    }
    else {
      for (int i = 0; i < index; i++) {
        fake[i] = prior[i];
      }
    }
    return fake;
  }
}
