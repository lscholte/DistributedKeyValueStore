package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import utilities.Logger;
import utilities.ThrowingRunnable;
import utilities.Tokenizer;

/**
 * Reads commands from an input stream and sends them to
 * a {@code Client}.
 * @author lscholte
 *
 */
public class CommandReader {
  
  private static final String UNRECOGNIZED_COMMAND = "Unrecognized Command. Enter 'help' for usage";
  private static final String USAGE =
      "Usage: " + System.lineSeparator() +
      "put <key> <value>" + System.lineSeparator() +
      "get <key>" + System.lineSeparator() +
      "delete <key>" + System.lineSeparator();
      
  
  private Client client;
  private Scanner scanner;
  private BufferedReader inputReader;
  private Map<String, ThrowingRunnable> commands;
  
  /**
   * Constructs a CommandReader with a specified client
   * to send commands to.
   */
  public CommandReader(Client client) {
    this.client = client;
    commands = registerCommands();
    inputReader = new BufferedReader(new InputStreamReader(System.in));
  }
  
  /**
   * Reads a line of user input and invokes the appropriate
   * command on the client.
   */
  public void readInput() throws Throwable {
    String inputLine = inputReader.readLine();
    if (inputLine == null) {
      Logger.logInfo("End of input reached");
      return;
    }
    scanner = new Scanner(inputLine);

    ThrowingRunnable runnable = null;
    try {
      runnable = commands.get(scanner.next());
    }
    catch (Exception e) {
      //Do nothing -- command will be null, so unrecognized
    }    
    
    if (runnable == null) {
      Logger.logError(UNRECOGNIZED_COMMAND);
      return;
    }
    
    runnable.run();
  }
  
  /**
   * Generates a map of actions to run when the client reads
   * certain keywords (case insensitive).
   * @return a map of actions that can be invoked.
   */
  private Map<String, ThrowingRunnable> registerCommands() {
    Map<String, ThrowingRunnable> commands = new TreeMap<String, ThrowingRunnable>(String.CASE_INSENSITIVE_ORDER);
    
    commands.put(
        "put",
        () -> {
          String[] tokens = Tokenizer.tokenize(scanner, 2);
          if (tokens == null) {
            Logger.logError(UNRECOGNIZED_COMMAND);
            return;
          }
          client.sendPut(tokens[0], tokens[1]);
        });
    commands.put(
        "get",
        () -> {
          String[] tokens = Tokenizer.tokenize(scanner, 1);
          if (tokens == null) {
            Logger.logError(UNRECOGNIZED_COMMAND);
            return;
          }
          client.sendGet(tokens[0]);
        });
    commands.put(
        "delete",
        () -> {
          String[] tokens = Tokenizer.tokenize(scanner, 1);
          if (tokens == null) {
            Logger.logError(UNRECOGNIZED_COMMAND);
            return;
          }
          client.sendDelete(tokens[0]);
        });
    commands.put(
        "help",
        () -> Logger.logInfo(USAGE));
    
    return commands;
  }
  
}
