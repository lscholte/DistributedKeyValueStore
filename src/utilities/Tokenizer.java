package utilities;

import java.util.Scanner;

/**
 * A utility class that provides methods for tokenizing strings.
 * @author lscholte
 *
 */
public final class Tokenizer {
  
  private Tokenizer() {}
  
  /**
   * Runs through a Scanner and tokenizes the strings into an array.
   * If the number of tokens does not exactly match the number of
   * expected tokens, then a null array is returned.
   * @param scanner the scanner to tokenize
   * @param expectedTokens the number of expected tokens
   * @return an array of {@code expectedTokens} strings or null
   *      if there are not exactly {@code expectedTokens} strings.
   */
  public static String[] tokenize(Scanner scanner, int expectedTokens) {
    String[] tokens = new String[expectedTokens];
    for (int i = 0; i < expectedTokens; ++i) {
      if (!scanner.hasNext()) {
        return null;
      }
      tokens[i] = scanner.next();
    }
    
    if (scanner.hasNext()) {
      return null;
    }
    
    return tokens;
  }

}
